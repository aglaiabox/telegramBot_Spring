package aglaia.telegramBot.service;

import aglaia.telegramBot.Bot;
import aglaia.telegramBot.database.Database;
import aglaia.telegramBot.model.AbstractTask;
import aglaia.telegramBot.model.KangTask;
import aglaia.telegramBot.model.UserBot;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
// хождение в базу данных и доставание активного текущего таска
public class ActiveTaskService {
    public static final String YOU_DONT_HAVE_AN_ACTIVE_TASK = "You don't have an active task. Please chose one of the options: "  + System.lineSeparator() +
            Bot.MULTIPLY + System.lineSeparator() + Bot.DIVISION + System.lineSeparator() + Bot.KANG_TASK;
    public static final String RIGHT = "That's right";
    public static final String WRONG = "Looks like you make some mistake. Try again";
    final Database database;

    public ActiveTaskService(Database database) {
        this.database = database;
    }

    public String checkAnAnswer (Update update) {
        Message msg = update.getMessage();
        Long msgChatId = msg.getChatId();
        UserBot userBot = database.getUserFromDatabase(msgChatId);
        AbstractTask actualTask = userBot.getActualTask();

        if (actualTask == null) return YOU_DONT_HAVE_AN_ACTIVE_TASK;

        if (actualTask.getCorrectAnswer().equalsIgnoreCase(msg.getText())) {
            if (actualTask instanceof KangTask){
                userBot.setIndexOfCurrentKangTask(userBot.getIndexOfCurrentKangTask()+1);
            }
            userBot.setActualTask(null);

            return RIGHT;
        }

        else {
            return WRONG;
        }

    }
}
