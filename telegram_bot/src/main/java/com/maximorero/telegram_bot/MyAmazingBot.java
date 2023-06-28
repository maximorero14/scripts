package com.maximorero.telegram_bot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.maximorero.telegram_bot.services.ChatGPTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Arrays;
public class MyAmazingBot extends TelegramLongPollingBot {

    private static final String COMMAND_AI = "/ai";
    private static final String COMMAND_IA = "/ia";

    private ChatGPTService chatGPTService = new ChatGPTService();

    @Override
    public void onUpdateReceived(Update update) {

        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {
            // Set variables
            String message_text = update.getMessage().getText();
            Message reciveMessage = update.getMessage();
            long chat_id = update.getMessage().getChatId();

            if(reciveMessage.isCommand()){
                try {
                    executeCommand(reciveMessage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            }else{
                executeEcho(reciveMessage);
            }
        }
    }

    private void executeCommand(Message reciveMessage) throws Exception {
        String message_text = reciveMessage.getText();
        long chat_id = reciveMessage.getChatId();
        String result = "";
        switch (getCommand(reciveMessage)){
            case COMMAND_IA:
            case COMMAND_AI:
                result = chatGPTService.executeQuery(getQuery(reciveMessage));
                break ;
        }

        SendMessage message = new SendMessage();
        message.setChatId(chat_id);
        message.setText(result);
        try {
            execute(message); // Sending our message object to user
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void executeEcho(Message reciveMessage){
        String message_text = reciveMessage.getText();
        long chat_id = reciveMessage.getChatId();
        SendMessage message = new SendMessage();
        message.setChatId(chat_id);
        message.setText(message_text);
        try {
            execute(message); // Sending our message object to user
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private String getCommand(Message reciveMessage){
        String[] results = reciveMessage.getText().split(" ");
        return results[0];
    }

    private String getQuery(Message reciveMessage){
        String[] results = reciveMessage.getText().split(" ");
        String result = String.join(" ", Arrays.copyOfRange(results, 1, results.length));
        return result;
    }

    @Override
    public String getBotUsername() {
        // Return bot username
        // If bot username is @MyAmazingBot, it must return 'MyAmazingBot'
        return "maximorero14_bot";
    }

    @Override
    public String getBotToken() {
        // Return bot token from BotFather
        return "6269714196:AAGNyiTpf5O7IXQqGS2zUVFJf0BPCX2TVV4";
    }

}