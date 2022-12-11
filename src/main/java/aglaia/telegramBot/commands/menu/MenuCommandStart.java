package aglaia.telegramBot.commands.menu;

import aglaia.telegramBot.model.keyboards.ReplyKeyBoardMenu;
import aglaia.telegramBot.service.RegistrationService;
import aglaia.telegramBot.service.menu.MenuServiceStart;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class MenuCommandStart extends AbstractMenuCommand {
    public static final String WHAT_DO_YOU_WANT_TO_SOLVE = "There are many interesting tasks. What do you want to solve?";
    MenuServiceStart menuServiceStart;

    public MenuCommandStart(MenuServiceStart menuServiceStart, RegistrationService registrationService) {
        super("start", "Стартуем знакомство и потом клавиатуру с меню", menuServiceStart, registrationService, false);
        this.menuServiceStart = (MenuServiceStart) abstractMenuService;
    }

    @Override
    SendMessage startCommandLogic(Long chatId) {
        String textToSend = WHAT_DO_YOU_WANT_TO_SOLVE;
        return ReplyKeyBoardMenu.getMainMenuKeyboard(chatId, textToSend);
    }
}
