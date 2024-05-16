package org.example.chatappgui.langchain4j;

public class UserMessage implements ChatMessage {
    private final String message;

    public UserMessage(String message) {
        this.message = message;
    }

    @Override
    public String getText() {
        return message;
    }
}
