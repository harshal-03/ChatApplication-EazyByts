package com.example.Chatapp.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage {
    private String content;
    private String sender;
    private MessageType type;

    public String getSender() {
        return sender;
    }


    public static class Builder {
        private String content;
        private String sender;
        private MessageType type;

        public Builder content(String content) {
            this.content = content;
            return this;
        }

        public Builder sender(String sender) {
            this.sender = sender;
            return this;
        }

        public Builder type(MessageType type) {
            this.type = type;
            return this;
        }

        public ChatMessage build() {
            ChatMessage chatMessage = new ChatMessage();
            chatMessage.content = this.content;
            chatMessage.sender = this.sender;
            chatMessage.type = this.type;
            return chatMessage;
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
