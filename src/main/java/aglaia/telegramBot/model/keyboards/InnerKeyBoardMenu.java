package aglaia.telegramBot.model.keyboards;

import aglaia.telegramBot.Bot;
import org.checkerframework.checker.units.qual.K;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class InnerKeyBoardMenu {

    public static final String MULTIPLY = "умножение";
    public static final String DIVISION = "деление";
    public static final String KANG_TASK = "Текстовая задача";


    public static SendMessage getMenu(long chatId) {
        InlineKeyboardMarkup inlineKeyboard = new InlineKeyboardMarkup();

        List<InlineKeyboardButton> rowList1 = new ArrayList<>();
        List<InlineKeyboardButton> rowList2 = new ArrayList<>();

        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText(MULTIPLY);
        inlineKeyboardButton1.setCallbackData(Bot.MULTIPLY);
        rowList1.add(inlineKeyboardButton1);

        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        inlineKeyboardButton2.setText(DIVISION);
        inlineKeyboardButton2.setCallbackData(Bot.DIVISION);
        rowList1.add(inlineKeyboardButton2);

        InlineKeyboardButton inlineKeyboardButton3 = new InlineKeyboardButton();
        inlineKeyboardButton3.setText(KANG_TASK);
        inlineKeyboardButton3.setCallbackData(Bot.KANG_TASK);
        rowList2.add(inlineKeyboardButton3);

        List<List<InlineKeyboardButton>> rowsList = new ArrayList<>();
        rowsList.add(rowList1);
        rowsList.add(rowList2);
        inlineKeyboard.setKeyboard(rowsList);

        SendMessage sendMessage = new SendMessage();
        sendMessage.setText("Chouse the option");
        sendMessage.setReplyMarkup(inlineKeyboard);
        sendMessage.setChatId(chatId);
        return sendMessage;
    }
}
