package aglaia.telegramBot.commands.menu;

import aglaia.telegramBot.Bot;
import aglaia.telegramBot.service.RegistrationAndSettingService;
import aglaia.telegramBot.service.menu.AbstractMenuService;
import aglaia.telegramBot.service.menu.MenuServiceStart;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
public class MenuCommandHello extends AbstractMenuCommand {
    protected AbstractMenuService abstractMenuService;

    public static final String TEXT_TO_SEND_COM_HELLO = "Привет, добро пожаловать в наш бот." + System.lineSeparator() +
            "Если вы хотите тренировать умножение отправьте команду " + Bot.MULTIPLY + " ," + System.lineSeparator() +
            "если деление, то команду " + Bot.DIVISION + " ." + System.lineSeparator() +
            "если текстовую задачу из сборника Кенгуру, то команду " + Bot.KANG_TASK + " ." + System.lineSeparator() +
            "Хорошей тренировки!";

    public MenuCommandHello(MenuServiceStart menuServiceStart, RegistrationAndSettingService registrationAndSettingService) {
        super("hello", "Начинаем решать примеры на умножение", menuServiceStart, registrationAndSettingService,false);

    }

    @Override
    SendMessage startCommandLogic(Long chatId) {
        SendMessage answer = new SendMessage();
        answer.setText(TEXT_TO_SEND_COM_HELLO);
        answer.setChatId(chatId);
        return answer;
    }
}