package org.example.chatappgui.langchain4j;

public class AiMessage implements ChatMessage {
    private final String message;

    public AiMessage(String message) {
        this.message = message;
    }

    @Override
    public String getText() {
        return message;
    }
}
