package aglaia.telegramBot.model.keyboards;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class KeyBoardABCDE {


    public static final String A = "a";
    public static final String B = "b";
    public static final String C = "c";
    public static final String D = "d";
    public static final String E = "e";
    static List<String> listABCDE = new ArrayList<>();

    static {
        listABCDE.add(A);
        listABCDE.add(B);
        listABCDE.add(C);
        listABCDE.add(D);
        listABCDE.add(E);
    }

    public static InlineKeyboardMarkup getABCDEAnswers() {

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        List<InlineKeyboardButton>keyboardButtonList = new ArrayList<>();
        for (int i = 0; i < listABCDE.size(); i++) {
            InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
            inlineKeyboardButton.setText(listABCDE.get(i));
            inlineKeyboardButton.setCallbackData(listABCDE.get(i));
            keyboardButtonList.add(inlineKeyboardButton);
        }
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonList);
        inlineKeyboardMarkup.setKeyboard(rowList);

        return inlineKeyboardMarkup;


//        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
//        replyKeyboardMarkup.setKeyboard(keyboardRows);
//        return replyKeyboardMarkup;
    }
}
