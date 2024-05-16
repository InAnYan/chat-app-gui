package org.example.chatappgui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import org.example.chatappgui.langchain4j.AiMessage;
import org.example.chatappgui.langchain4j.Chat;
import org.example.chatappgui.langchain4j.ChatMessage;
import org.example.chatappgui.langchain4j.UserMessage;

public class ChatApplication extends Application {
    public static final String USER_MESSAGE_COLOR = "#7ee3fb";
    public static final String AI_MESSAGE_COLOR = "#bac8cb";

    private VBox chatVBox;

    private Chat aiChat = new Chat();

    private Parent createAiChatUI() {
        VBox aiChatBox = new VBox(10);
        aiChatBox.setPadding(new Insets(10));

        aiChatBox.getChildren().add(constructChatScrollPane());
        aiChatBox.getChildren().add(constructUserPromptBox());

        return aiChatBox;
    }

    private Node constructChatScrollPane() {
        ScrollPane chatScrollPane = new ScrollPane();
        chatScrollPane.setFitToWidth(true);
        chatScrollPane.setStyle("-fx-border-color: black;");
        VBox.setVgrow(chatScrollPane, Priority.ALWAYS);

        chatVBox = new VBox(10);
        chatVBox.setPadding(new Insets(10, 10, 0, 10));
        chatScrollPane.setContent(chatVBox);

        return chatScrollPane;
    }

    private Node constructUserPromptBox() {
        HBox userPromptHBox = new HBox(10);
        userPromptHBox.setAlignment(Pos.CENTER);

        TextField userPromptTextField = new TextField();
        HBox.setHgrow(userPromptTextField, Priority.ALWAYS);

        userPromptHBox.getChildren().add(userPromptTextField);

        Button userPromptSubmitButton = new Button("Submit");
        userPromptSubmitButton.setOnAction(e -> {
            String userPrompt = userPromptTextField.getText();
            userPromptTextField.setText("");

            addMessage(new UserMessage(userPrompt));

            String aiMessage = aiChat.execute(userPrompt);

            addMessage(new AiMessage(aiMessage));
        });

        userPromptHBox.getChildren().add(userPromptSubmitButton);

        return userPromptHBox;
    }

    private void addMessage(ChatMessage chatMessage) {
        Node messageNode = constructMessageNode(chatMessage);
        chatVBox.getChildren().add(messageNode);
    }

    private static Node constructMessageNode(ChatMessage chatMessage) {
        boolean isUser = chatMessage instanceof UserMessage;

        Pane pane = new Pane();

        if (isUser) {
            pane.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        }

        VBox paneVBox = new VBox(10);
        paneVBox.setMaxWidth(500);

        paneVBox.setStyle("-fx-background-color: " + (isUser ? USER_MESSAGE_COLOR : AI_MESSAGE_COLOR) + ";");
        paneVBox.setPadding(new Insets(10));

        Label authorLabel = new Label(isUser ? "User" : "AI");
        authorLabel.setStyle("-fx-font-weight: bold");
        paneVBox.getChildren().add(authorLabel);

        Label messageLabel = new Label(chatMessage.getText());
        paneVBox.getChildren().add(messageLabel);

        pane.getChildren().add(paneVBox);

        return pane;
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("AI Chat");
        stage.setScene(new Scene(createAiChatUI(), 500, 500));
        stage.show();
    }
}