package aglaia.telegramBot.service.operation;

import aglaia.telegramBot.model.entity.tasks.AbstractTask;
import aglaia.telegramBot.service.UserBotService;

public abstract class AbstractCommandService {
    UserBotService userBotService;

    public AbstractCommandService(UserBotService userBotService) {
        this.userBotService = userBotService;
    }

    // отвечает за выдачу задания
    public abstract AbstractTask giveMeATask(Long chatId);

}
