package aglaia.telegramBot;

import aglaia.telegramBot.model.keyboards.ReplyKeyBoardMenu;
import aglaia.telegramBot.service.ActiveTaskService;
import aglaia.telegramBot.service.ConstantMessagesService;
import aglaia.telegramBot.service.RegistrationAndSettingService;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.IBotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;


@Log4j2
@Component
public class Bot extends TelegramLongPollingCommandBot {
    //static final Logger log = LoggerFactory.getLogger(TelegramLongPollingCommandBot.class);
    public static final String MULTIPLY = "/multiply";
    public static final String DIVISION = "/division";
    public static final String KANG_TASK = "/kangaroo";
    public static final String RUS = "/rus";
    public static final String ENG = "/eng";

    String BOT_NAME = System.getenv("BOT_NAME");
    String BOT_USERNAME = System.getenv("BOT_USERNAME");
    String BOT_TOKEN = System.getenv("BOT_TOKEN");
    ActiveTaskService ats;




    public Bot(List<IBotCommand> commandList, ActiveTaskService ats) {
        super();
        commandList.forEach(this::register);
        this.ats = ats;

        log.info("Hello, Bot");

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
        //1 нет активного таска - тогда отпраавляем сообщение и предлагаем выбрать
        //2 ответ правильный - тогда отправляем и текст и стикер, плюс зачисляем баллы и сохраняем таск в решенный
        //3 ответ не правильный - тогда проверяем счетчик неправильных ответов по этому заданию.
        //      Если их становится три - показываем правильный ответ и сохраняем таск в сделанные, с пометной "не правильно" и баллы не зачисляем
        //4 пропустить задачу
        //5 показать правильный ответ
        //6 попробовать решить пропущенные задачи

        boolean isAnswerCorrect = false;

        // если регистрация не завершена - то идем регистрироваться
        if (!ats.isRegistrationComplete(msgChatId)) {
            text = ats.setDataOfUserToDatabaseAddGetAnswer(msgChatId, incommingAnswer);
        // в другом случае, это должен быть ответ на задание. Проверяем есть ли активный такс
        } else if (ats.isUserHaveAnActiveTask(msgChatId)) {
            isAnswerCorrect = ats.isAnswerCorrect(incommingAnswer, msgChatId);
            text = ats.getText(msgChatId, isAnswerCorrect);
            // иначе предлагаем выбрать таск и решать его
        } else {
            text = ats.getTextIfNoActualTask(msgChatId);
        }
        sendMessage.setText(text);
        sendMessage.setChatId(msgChatId.toString());

        try {
            if (isAnswerCorrect) execute(ats.getRandomSticker(msgChatId));
            if (text.equals(ats.getTextByNameOfString(msgChatId, "TASKS_WHAT_DO_YOU_WANT_TO_SOLVE"))) {
                execute(ReplyKeyBoardMenu.getMainMenuKeyboard(msgChatId, text));
            } else {
                execute(sendMessage);
            }
        } catch (TelegramApiException e) {
            //логируем сбой Telegram Bot API, используя userName
            log.error("TelegramApiException e: " + e.getMessage());
        }
    }

    // DONE: 21.11.2022 database postgress
    // TODO: 21.11.2022 make rest and point - сделать так чтобы через рест и поинт можно было бы сохдавать новые таски
    // todo если два или три раза неправильно ответил - все, задача отмечается как решенная не правильно, показывает правильный ответ
    // TODO: 21.11.2022 возможность пропустить задачу и возможность вернуться к нерешенным задачам
    // DONE: 24.11.2022 Реализация регистрации. Если новый пользователь - то включить свою программу по обмену сообщениями.

}

//todo  логирование. Куда пишет? в файл. Писать время и место. Лог формат, логоформаттер, лог аппенд  - определяет куда он добавляет(добавлять).
// Лог левел - зависит от того, насколько важное сообщение, и есть ирархия

//todo Задиплоидить