package aglaia.telegramBot.commands.menu;

import aglaia.telegramBot.Bot;
import aglaia.telegramBot.service.menu.AbstractMenuService;
import aglaia.telegramBot.service.menu.MenuServiceStart;
import org.springframework.stereotype.Component;

@Component
public class MenuCommandHello extends AbstractMenuCommand {
    protected AbstractMenuService abstractMenuService;

    public static final String TEXT_TO_SEND_COM_HELLO = "Привет, добро пожаловать в наш бот." + System.lineSeparator() +
            "Если вы хотите тренировать умножение отправьте команду " + Bot.MULTIPLY + " ," + System.lineSeparator() +
            "если деление, то команду " + Bot.DIVISION + " ." + System.lineSeparator() +
            "если текстовую задачу из сборника Кенгуру, то команду " + Bot.KANG_TASK + " ." + System.lineSeparator() +
            "Хорошей тренировки!";

    public MenuCommandHello(MenuServiceStart menuServiceStart) {
        super("hello", "Начинаем решать примеры на умножение", menuServiceStart, false);
        super.textToSend = TEXT_TO_SEND_COM_HELLO;
    }
}