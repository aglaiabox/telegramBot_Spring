package aglaia.telegramBot.service;

import aglaia.telegramBot.Bot;
import aglaia.telegramBot.database.Database;
import aglaia.telegramBot.model.tasks.AbstractTask;
import aglaia.telegramBot.model.tasks.KangTask;
import aglaia.telegramBot.model.UserBot;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
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


    public SendMessage handleUserMessage(Update update){


        SendMessage answer = new SendMessage();
        String incommingAnswer;
        Long msgChatId;

        if (update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            incommingAnswer = callbackQuery.getData();
            msgChatId = callbackQuery.getMessage().getChatId();
        } else {
            Message msg = update.getMessage();
            msgChatId = msg.getChatId();
            incommingAnswer = msg.getText();
        }

        String text;

        if (database.getUserFromDatabase(msgChatId).getActualTask() == null) {
            text = YOU_DONT_HAVE_AN_ACTIVE_TASK;
        }


        if (isAnswerCorrect(incommingAnswer, msgChatId)){
            text = RIGHT;
        } else {
            text = WRONG;
        }

        answer.setText(text);
        answer.setChatId(msgChatId.toString());
        return answer;
    }



    public boolean isAnswerCorrect (String incommingAnswer, Long msgChatId) {
        UserBot userBot = database.getUserFromDatabase(msgChatId);
        AbstractTask actualTask = userBot.getActualTask();

        if (actualTask.getCorrectAnswer().equalsIgnoreCase(incommingAnswer)) {
            if (actualTask instanceof KangTask){
                userBot.setIndexOfCurrentKangTask(userBot.getIndexOfCurrentKangTask()+1);
            }
            userBot.setActualTask(null);

            return true;
        }

        else {
            return false;
        }
    }
}
