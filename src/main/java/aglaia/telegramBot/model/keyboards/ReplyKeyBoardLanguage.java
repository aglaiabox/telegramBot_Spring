package aglaia.telegramBot.model.keyboards;

import aglaia.telegramBot.Bot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class ReplyKeyBoardLanguage {

    public static KeyboardRow getLanguageBottoms (){

        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.add(new KeyboardButton(Bot.RUS));
        keyboardRow.add(new KeyboardButton(Bot.ENG));

        return keyboardRow;
    }

    public static SendMessage getLanguageKeyboard(long chatId, String textToSend) {
        ArrayList<KeyboardRow> keyboardRows = new ArrayList<>();
        keyboardRows.add(getLanguageBottoms());
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(keyboardRows);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(textToSend);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        sendMessage.setChatId(chatId);
        return sendMessage;
    }
}
