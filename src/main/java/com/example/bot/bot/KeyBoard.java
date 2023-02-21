package com.example.bot.bot;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import java.util.ArrayList;
import java.util.List;

public class KeyBoard {
    private ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
    private List<KeyboardRow> keyboardRowList = new ArrayList<>();
    public ReplyKeyboardMarkup getKeyboardMarkup(){
        keyboardMarkup.setResizeKeyboard(true);
        KeyboardRow row = new KeyboardRow();
        row.add("Овен");
        row.add("Телец");
        row.add("Близнецы");
        row.add("Рак");
        keyboardRowList.add(row);

        row = new KeyboardRow();
        row.add("Лев");
        row.add("Дева");
        row.add("Весы");
        row.add("Скорпион");
        keyboardRowList.add(row);

        row = new KeyboardRow();
        row.add("Стрелец");
        row.add("Козерог");
        row.add("Водолей");
        row.add("Рыбы");
        keyboardRowList.add(row);

        keyboardMarkup.setKeyboard(keyboardRowList);

        return keyboardMarkup;
    }
}
