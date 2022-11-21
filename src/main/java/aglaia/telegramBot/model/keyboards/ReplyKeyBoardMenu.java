package aglaia.telegramBot.model.keyboards;

import aglaia.telegramBot.Bot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;

public class ReplyKeyBoardMenu {


    public static final String MULTIPLY = "Тренеруем умножение";
    public static final String DIVISION = "Тренеруем деление";
    public static final String KANG_TASK = "Текстовая задача";

    public static SendMessage getMainMenuKeyboard(long chatId) {

        ArrayList<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow keyboardRow1 = new KeyboardRow();
        keyboardRow1.add(new KeyboardButton(Bot.MULTIPLY));
        keyboardRow1.add(new KeyboardButton(Bot.DIVISION));
        KeyboardRow keyboardRow2 = new KeyboardRow();
        keyboardRow2.add(new KeyboardButton(Bot.KANG_TASK));
        keyboardRows.add(keyboardRow1);
        keyboardRows.add(keyboardRow2);

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(keyboardRows);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText("Chouse the option");
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        sendMessage.setChatId(chatId);
        return sendMessage;
    }
}
