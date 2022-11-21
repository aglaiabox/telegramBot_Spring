package aglaia.telegramBot.commands.menu;

import aglaia.telegramBot.model.keyboards.InnerKeyBoardMenu;
import aglaia.telegramBot.model.keyboards.ReplyKeyBoardMenu;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class MenuCommandStart  extends AbstractMenuCommand {

    public MenuCommandStart() {
        super("start", "Стартуем клавиатуру", false);
    }

    @Override
    public void processMessage(AbsSender absSender, Message message, String[] strings) {


        try {
            absSender.execute(ReplyKeyBoardMenu.getMainMenuKeyboard(message.getChatId()));
//            absSender.execute(InnerKeyBoardMenu.getMenu(message.getChatId()));
        } catch (TelegramApiException e) {
            System.out.println("Exception: " + e.getMessage());
            //логируем сбой Telegram Bot API, используя commandName и userName
        }


    }
}
