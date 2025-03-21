package com.example.dorm_bot;

import com.example.dorm_bot.comands.Command;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

@Component
public class Bot extends TelegramLongPollingBot {

    @Value("${bot.username}")
    private String botUsername;

    private final List<Command> commands;

    public Bot(@Value("${bot.token}") String token, List<Command> commands) {
        super(token);
        this.commands = commands;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            commands.stream()
                    .filter(command -> command.isApplicable(update))
                    .findFirst()
                    .ifPresent( command -> {
                        Message message = update.getMessage();
                        String answer = command.process(update, this);
                        SendMessage response = new SendMessage();
                        response.setChatId(message.getChatId().toString());
                        response.setText(answer);
                        try {
                            execute(response);
                        } catch (TelegramApiException e) {
                            throw new RuntimeException(e);
                        }
                    });
        }
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }
}
