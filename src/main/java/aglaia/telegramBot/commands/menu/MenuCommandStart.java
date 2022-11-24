package aglaia.telegramBot.commands.menu;

import aglaia.telegramBot.entity.UserBot;
import aglaia.telegramBot.model.keyboards.InnerKeyBoardMenu;
import aglaia.telegramBot.model.keyboards.ReplyKeyBoardMenu;
import aglaia.telegramBot.service.menu.MenuServiceStart;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class MenuCommandStart extends AbstractMenuCommand {
    MenuServiceStart menuServiceStart;

    public MenuCommandStart(MenuServiceStart menuServiceStart) {
        super("start", "Стартуем знакомство и потом клавиатуру с меню", menuServiceStart, false);
        this.menuServiceStart = (MenuServiceStart) abstractMenuService;
    }

    @Override
    public void processMessage(AbsSender absSender, Message message, String[] strings) {
        Long chatId = message.getChatId();
        String textToSend;


        if (!menuServiceStart.isRegistrationComplete(chatId)) {
            textToSend = menuServiceStart.getTextToSend(chatId);
            SendMessage answer = new SendMessage();
            answer.setText(textToSend);
            answer.setChatId(message.getChatId());
            try {
                absSender.execute(answer);
            } catch (TelegramApiException e) {
                System.out.println("Exception: " + e.getMessage());
                //логируем сбой Telegram Bot API, используя commandName и userName
            }
        } else {
            try {
                textToSend = "There are many interesting tasks. What do you want to solve?";
                absSender.execute(ReplyKeyBoardMenu.getMainMenuKeyboard(message.getChatId(), textToSend));
//            absSender.execute(InnerKeyBoardMenu.getMenu(message.getChatId()));
            } catch (TelegramApiException e) {
                System.out.println("Exception: " + e.getMessage());
                //логируем сбой Telegram Bot API, используя commandName и userName
            }
        }
    }
}
