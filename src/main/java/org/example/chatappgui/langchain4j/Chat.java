package org.example.chatappgui.langchain4j;

public class Chat {
    public String execute(String userMessage) {
        try {
            // Calls to langchain4j methods are blocking, so I simulate there a delay.
            Thread.sleep(3000);
        } catch (InterruptedException ignored) {}

        return "I can't find an answer for your question: \"" + userMessage + "\"";
    }
}
