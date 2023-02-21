package com.example.bot.bot;

import com.example.bot.utils.SiteParser;
import com.example.bot.config.BotConfig;
import com.example.bot.repositories.ZodiacSignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class TelegramBot extends TelegramLongPollingBot {
    @Autowired
    private SiteParser siteParser;
    @Autowired
    private ZodiacSignRepository zodiacSignQuery;
    private BotConfig config;

    public TelegramBot(BotConfig config) {
        this.config = config;
        List<BotCommand> commands = new ArrayList<>();
        commands.add(new BotCommand("/start", "Restart bot"));
        try {
            this.execute(new SetMyCommands(commands, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        if(zodiacSignQuery.checkTableCount() == 0){
            try {
                siteParser.setInfo();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if(update.hasMessage() && update.getMessage().hasText()){
            Message messageText = update.getMessage();
            String chatId = messageText.getChatId().toString();
            String text = messageText.getText();
            if(text.equals("/start")){
                startCommand(chatId);
            } else if(text.equals(zodiacSignQuery.findSign(text))){
                sendMessage(chatId, zodiacSignQuery.getForecast(text));
            } else sendMessage(chatId, "Что-что? Попробуй еще раз!");
        }
    }

    @Override
    public String getBotUsername() {
        return config.getName();
    }
    @Override
    public String getBotToken(){
        return config.getToken();
    }

    public void startCommand(String chatId){
        String answer = "Тоже зашел посмотреть гороскопчики?" +
                "\n" + "Так уж и быть, выбирай знак!";
        sendMessage(chatId, answer);
    }
    public void sendMessage(String chatId, String textToSend){
        SendMessage message = new SendMessage();
        KeyBoard keyBoard = new KeyBoard();
        message.setChatId(chatId);
        message.setText(textToSend);
        message.setReplyMarkup(keyBoard.getKeyboardMarkup());
        try {
            execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

}
