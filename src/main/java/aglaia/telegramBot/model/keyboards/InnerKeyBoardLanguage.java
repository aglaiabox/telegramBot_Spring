package aglaia.telegramBot.model.keyboards;

import aglaia.telegramBot.Bot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class InnerKeyBoardLanguage {

    public static final String RUS = "/rus";
    public static final String ENG = "/eng";




    public static List<InlineKeyboardButton> getLanguageBottoms (){
        List<InlineKeyboardButton> rowList3 = new ArrayList<>();

        InlineKeyboardButton inlineKeyboardButton4 = new InlineKeyboardButton();
        inlineKeyboardButton4.setText(RUS);
        inlineKeyboardButton4.setCallbackData(Bot.RUS);
        rowList3.add(inlineKeyboardButton4);

        InlineKeyboardButton inlineKeyboardButton5 = new InlineKeyboardButton();
        inlineKeyboardButton5.setText(ENG);
        inlineKeyboardButton5.setCallbackData(Bot.ENG);
        rowList3.add(inlineKeyboardButton5);

        return rowList3;
    }

    public static SendMessage getLanguageMenu(long chatId) {
        InlineKeyboardMarkup inlineKeyboard = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> rowsList = new ArrayList<>();
        rowsList.add(InnerKeyBoardLanguage.getLanguageBottoms());
        inlineKeyboard.setKeyboard(rowsList);

        SendMessage sendMessage = new SendMessage();
        sendMessage.setText("Language");
        sendMessage.setReplyMarkup(inlineKeyboard);
        sendMessage.setChatId(chatId);
        return sendMessage;
    }
}
