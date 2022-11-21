package aglaia.telegramBot.service.operation;

import aglaia.telegramBot.model.tasks.AbstractTask;
import aglaia.telegramBot.database.Database;

public abstract class AbstractCommandService {
    Database database;

    public AbstractCommandService(Database database) {
        this.database = database;
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
