package aglaia.telegramBot;

import aglaia.telegramBot.commands.menu.MenuCommandStart;
import aglaia.telegramBot.service.ActiveTaskService;
import aglaia.telegramBot.commands.menu.MenuCommandHello;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.IBotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;



@Component
public class Bot extends TelegramLongPollingCommandBot {
    public static final String MULTIPLY = "/multiply";
    public static final String DIVISION = "/division";
    public static final String KANG_TASK = "/kangaroo";
    public static final String TRY_AGAIN = "Try again";
    public static final String RIGHT_ANSWER = "Great job! You are right!" + System.lineSeparator() +
            " What do you want next:" + System.lineSeparator() +
            MULTIPLY + System.lineSeparator() + DIVISION + System.lineSeparator() + KANG_TASK;
    public static final String DON_T_HAVE_AN_ACTIVE_TASK = "You don't have an active task. PLease tell me /hello for getting one";
    public static final String VERONICA_HI = "Veronica, hi! ";
    public static final String VERONICA_I_LOVE_U = "Post Scriptum: I LOVE U";
    public static final String VERONICAs_ID = "5654062987";
    public static final String HI_THERE = ", hi there and welcome! my name is Math bot and I'm ready to give your new interesting math tasks!";


    String BOT_NAME = System.getenv("BOT_NAME");
    String BOT_USERNAME = System.getenv("BOT_USERNAME");
    String BOT_TOKEN = System.getenv("BOT_TOKEN");
    ActiveTaskService ats; 

    public Bot(List<IBotCommand> commandList, ActiveTaskService ats) {
        super();



        commandList.forEach(command -> register(command));
        this.ats = ats;

    }

    @Override
    public String getBotUsername() {
        return BOT_USERNAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    @Override
    public void processNonCommandUpdate(Update update) {

        SendMessage sendMessage = ats.handleUserMessage(update);

        try {
            execute(sendMessage);

        } catch (TelegramApiException e) {
            //логируем сбой Telegram Bot API, используя userName
            System.out.println("TelegramApiException e: " + e.getMessage());
        }

    }

    // TODO: 21.11.2022 database postgress
    // TODO: 21.11.2022 make rest and point - сделать так чтобы через рест и поинт можно было бы сохдавать новые таски
    // TODO: 21.11.2022 картинку в ответ на правильно решенный сгенерированный таск
    // TODO: 21.11.2022 систему баллов за решенные текстовые задачи, статистика и бонусы
    // todo если два или три раза неправильно ответил - все, задача отмечается как решенная не правильно, показывает правильный ответ
    // TODO: 21.11.2022 возможность пропустить задачу и возможность вернуться к нерешенным задачам





}