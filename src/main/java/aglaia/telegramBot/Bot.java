package aglaia.telegramBot;

import aglaia.telegramBot.model.keyboards.ReplyKeyBoardMenu;
import aglaia.telegramBot.service.ActiveTaskService;
import aglaia.telegramBot.service.RegistrationService;
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

    public static final String YOU_DONT_HAVE_AN_ACTIVE_TASK = "You don't have an active task. Please chose one of the options: " + System.lineSeparator() +
            Bot.MULTIPLY + System.lineSeparator() + Bot.DIVISION + System.lineSeparator() + Bot.KANG_TASK;
    public static final String RIGHT = "That's right!";

    public static final String WRONG = "Looks like you make some mistake. Try again";

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

        // обработка ответов от пользователя
        SendMessage sendMessage = new SendMessage();
        String incommingAnswer = ats.getAnswerString(update);
        Long msgChatId = ats.getMsgChatId(update);

        String text;

        //тут у нас несколько кейсов:
        // 1 нет активного таска - тогда отпраавляем сообщение и предлагаем выбрать
        //2 ответ правильный - тогда отправляем и текст и стикер, плюс зачисляем баллы и сохраняем таск в решенный
        //3 ответ не правильный - тогда проверяем счетчик неправильных ответов по этому заданию.
        //      Если их становится три - показываем правильный ответ и сохраняем таск в сделанные, с пометной "не правильно" и баллы не зачисляем
        // 4 пропустить задачу
        // 5 показать правильный ответ
        //6 попробовать решить пропущенные задачи

        boolean isAnswerCorrect = false;

        // если регистрация не завершена - то идем регистрироваться
        if (!ats.isRegistrationComplete(msgChatId)) {
            text = ats.setDataOfUserToDatabaseAddGetAnswer(msgChatId, incommingAnswer);
        // в другом случае, это должен быть ответ на задание
        } else {
            if (!ats.isUserHaveAnActiveTask(msgChatId)) {
                text = YOU_DONT_HAVE_AN_ACTIVE_TASK;
            } else {
                isAnswerCorrect = ats.isAnswerCorrect(incommingAnswer, msgChatId);
                if (isAnswerCorrect) {
                    text = ats.getRandomStringForRight();
                } else {
                    text = WRONG;
                }
            }

        }
        sendMessage.setText(text);
        sendMessage.setChatId(msgChatId.toString());

        try {
            if (isAnswerCorrect) execute(ats.getRandomSticker(msgChatId));
            if (text.equals(RegistrationService.LET_S_START)) {
                text = "There are many interesting tasks. What do you want to solve?";
                execute(ReplyKeyBoardMenu.getMainMenuKeyboard(msgChatId, text));
            } else {
                execute(sendMessage);
            }
        } catch (TelegramApiException e) {
            //логируем сбой Telegram Bot API, используя userName
            System.out.println("TelegramApiException e: " + e.getMessage());
        }
    }

    // DONE: 21.11.2022 database postgress
    // TODO: 21.11.2022 make rest and point - сделать так чтобы через рест и поинт можно было бы сохдавать новые таски
    // todo если два или три раза неправильно ответил - все, задача отмечается как решенная не правильно, показывает правильный ответ
    // TODO: 21.11.2022 возможность пропустить задачу и возможность вернуться к нерешенным задачам
    // DONE: 24.11.2022 Реализация регистрации. Если новый пользователь - то включить свою программу по обмену сообщениями.

}