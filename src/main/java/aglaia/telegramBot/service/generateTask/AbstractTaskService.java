package aglaia.telegramBot.service.generateTask;

import aglaia.telegramBot.model.AbstractTask;
import aglaia.telegramBot.database.Database;
import aglaia.telegramBot.model.AbstractTask;

public abstract class AbstractTaskService {
    Database database = Database.getInstance();

    public AbstractTaskService() {
    }

// отвечает за выдачу задания
    public abstract AbstractTask giveMeATask(Long chatId);

    // отвечает за проверку ответа
    public boolean checkAnswer(Long chatId, String answer) {
        AbstractTask abstractTask = database.getUserFromDatabase(chatId).getActualTask();
        if (answer.equalsIgnoreCase(abstractTask.getCorrectAnswer())) return true;
        return false;
    }
}
