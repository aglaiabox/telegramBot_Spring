package aglaia.telegramBot.commands.menu;

import aglaia.telegramBot.model.keyboards.ReplyKeyBoardMenu;
import aglaia.telegramBot.service.RegistrationAndSettingService;
import aglaia.telegramBot.service.menu.MenuServiceStart;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
public class MenuCommandStart extends AbstractMenuCommand {
    public static final String WHAT_DO_YOU_WANT_TO_SOLVE = "There are many interesting tasks. What do you want to solve?";
    MenuServiceStart menuServiceStart;

    public MenuCommandStart(MenuServiceStart menuServiceStart, RegistrationAndSettingService registrationAndSettingService) {
        super("start", "Стартуем сначала выбор языка, потом выборанный язык стартанет знакомство и последний вопрос про возраст стартанет потом клавиатуру с меню", menuServiceStart, registrationAndSettingService, false);
        this.menuServiceStart = (MenuServiceStart) abstractMenuService;
    }

    @Override
    SendMessage startCommandLogic(Long chatId) {
        return ReplyKeyBoardMenu.getMainMenuKeyboard(chatId, WHAT_DO_YOU_WANT_TO_SOLVE);
    }
}
