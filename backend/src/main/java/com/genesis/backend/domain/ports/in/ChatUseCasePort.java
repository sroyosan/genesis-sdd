package com.genesis.backend.domain.ports.in;

import com.genesis.backend.domain.model.AiResponse;
import com.genesis.backend.domain.model.Message;

import java.util.List;

public interface ChatUseCasePort {
    AiResponse processChat(List<Message> messages);
}
