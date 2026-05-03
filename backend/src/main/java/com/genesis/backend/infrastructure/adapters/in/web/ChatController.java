package com.genesis.backend.infrastructure.adapters.in.web;

import com.genesis.backend.domain.model.Message;
import com.genesis.backend.domain.model.MessageRole;
import com.genesis.backend.domain.model.ToolCall;
import com.genesis.backend.domain.ports.in.ChatUseCasePort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin(origins = "*")
public class ChatController {

    private final ChatUseCasePort chatUseCase;

    public ChatController(ChatUseCasePort chatUseCase) {
        this.chatUseCase = chatUseCase;
    }

    @PostMapping
    public AiResponseDto processChat(@RequestBody ChatRequest request) {
        List<Message> domainMessages = request.messages().stream().map(dto -> {
            MessageRole role;
            try {
                role = MessageRole.valueOf(dto.role().toUpperCase());
            } catch (IllegalArgumentException e) {
                role = MessageRole.USER;
            }
            return new Message(role, dto.content());
        }).toList();

        var aiResponse = chatUseCase.processChat(domainMessages);

        List<ToolCallDto> toolCallDtos = aiResponse.toolCalls().stream().map(tc -> {
            if (tc instanceof ToolCall.UpdateSpecSection uss) {
                return new ToolCallDto("update_spec_section", uss.id(), uss.sectionId(), uss.content(), uss.status(), null);
            } else if (tc instanceof ToolCall.UpdateProjectName upn) {
                return new ToolCallDto("update_project_name", upn.id(), null, null, null, upn.name());
            }
            return null;
        }).toList();

        return new AiResponseDto(aiResponse.textContent(), toolCallDtos);
    }
}
