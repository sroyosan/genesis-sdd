package com.genesis.backend.infrastructure.adapters.out.ollama;

import com.genesis.backend.domain.model.AiConfig;
import com.genesis.backend.domain.model.AiResponse;
import com.genesis.backend.domain.model.Message;
import com.genesis.backend.domain.model.ToolCall;
import com.genesis.backend.domain.ports.out.AiProviderPort;
import com.genesis.backend.infrastructure.adapters.out.ollama.dto.OllamaRequest;
import com.genesis.backend.infrastructure.adapters.out.ollama.dto.OllamaResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OllamaAiProviderAdapter implements AiProviderPort {

    private final RestTemplate restTemplate;
    private final String baseUrl;
    private final String defaultModel;

    public OllamaAiProviderAdapter(
            RestTemplate restTemplate,
            @Value("${ollama.base-url:http://localhost:11434}") String baseUrl,
            @Value("${ollama.model:llama3.1}") String defaultModel) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
        this.defaultModel = defaultModel;
    }

    @Override
    public AiResponse generateResponse(List<Message> messages, AiConfig config) {
        // Build Messages
        List<OllamaRequest.OllamaMessage> ollamaMessages = messages.stream()
                .map(m -> new OllamaRequest.OllamaMessage(
                        m.role().name().toLowerCase(),
                        m.content()
                )).collect(Collectors.toList());

        // Build Tools
        List<OllamaRequest.OllamaTool> ollamaTools = buildTools();

        // Use requested model or default
        String model = config != null && config.modelName() != null && !config.modelName().isEmpty() 
            ? config.modelName() 
            : defaultModel;

        OllamaRequest request = new OllamaRequest(
                model,
                ollamaMessages,
                ollamaTools,
                false
        );

        String url = baseUrl + "/api/chat";
        OllamaResponse response = restTemplate.postForObject(url, request, OllamaResponse.class);

        if (response == null || response.message() == null) {
            return new AiResponse("", List.of());
        }

        List<ToolCall> toolCalls = new ArrayList<>();
        if (response.message().tool_calls() != null) {
            for (OllamaResponse.OllamaToolCall otc : response.message().tool_calls()) {
                var function = otc.function();
                if ("update_spec_section".equals(function.name())) {
                    Map<String, Object> args = function.arguments();
                    toolCalls.add(new ToolCall.UpdateSpecSection(
                            UUID.randomUUID().toString(),
                            (String) args.get("sectionId"),
                            (String) args.get("content"),
                            (String) args.get("status")
                    ));
                } else if ("update_project_name".equals(function.name())) {
                    Map<String, Object> args = function.arguments();
                    toolCalls.add(new ToolCall.UpdateProjectName(
                            UUID.randomUUID().toString(),
                            (String) args.get("name")
                    ));
                }
            }
        }

        return new AiResponse(response.message().content(), toolCalls);
    }

    private List<OllamaRequest.OllamaTool> buildTools() {
        OllamaRequest.OllamaTool updateSpecSection = new OllamaRequest.OllamaTool(
                "function",
                new OllamaRequest.OllamaFunction(
                        "update_spec_section",
                        "Updates a specific section of the SDD.",
                        new OllamaRequest.OllamaParameters(
                                "object",
                                Map.of(
                                        "sectionId", new OllamaRequest.OllamaProperty("string", "The ID of the section to update. E.g. VISION, REQUIREMENTS, TECH_STACK, DATA_MODEL, ENDPOINTS, UX_FLOW, COMPONENTS"),
                                        "content", new OllamaRequest.OllamaProperty("string", "The detailed content for this section in Markdown."),
                                        "status", new OllamaRequest.OllamaProperty("string", "The completion status: EMPTY, DRAFT, COMPLETE")
                                ),
                                List.of("sectionId", "content", "status")
                        )
                )
        );

        OllamaRequest.OllamaTool updateProjectName = new OllamaRequest.OllamaTool(
                "function",
                new OllamaRequest.OllamaFunction(
                        "update_project_name",
                        "Updates the project name based on user ideas.",
                        new OllamaRequest.OllamaParameters(
                                "object",
                                Map.of(
                                        "name", new OllamaRequest.OllamaProperty("string", "The new project name.")
                                ),
                                List.of("name")
                        )
                )
        );

        return List.of(updateSpecSection, updateProjectName);
    }
}
