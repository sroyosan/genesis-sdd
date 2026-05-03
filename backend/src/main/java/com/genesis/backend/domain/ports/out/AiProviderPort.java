package com.genesis.backend.domain.ports.out;

import com.genesis.backend.domain.model.AiConfig;
import com.genesis.backend.domain.model.AiResponse;
import com.genesis.backend.domain.model.Message;

import java.util.List;

public interface AiProviderPort {
    AiResponse generateResponse(List<Message> messages, AiConfig config);
}
