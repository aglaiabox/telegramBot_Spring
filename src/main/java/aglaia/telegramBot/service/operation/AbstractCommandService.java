package aglaia.telegramBot.service.operation;

import aglaia.telegramBot.entity.AbstractTask;
import aglaia.telegramBot.database.Database;
import aglaia.telegramBot.entity.GeneratedTask;
import aglaia.telegramBot.entity.KangTask;

public abstract class AbstractCommandService {
    Database database;

    public AbstractCommandService(Database database) {
        this.database = database;
    }

// отвечает за выдачу задания
    public abstract AbstractTask giveMeATask(Long chatId);

}
