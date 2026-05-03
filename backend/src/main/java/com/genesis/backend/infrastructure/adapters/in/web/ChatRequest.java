package com.genesis.backend.infrastructure.adapters.in.web;

import java.util.List;

public record ChatRequest(List<MessageDto> messages) {}
