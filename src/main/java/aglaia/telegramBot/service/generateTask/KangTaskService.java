package aglaia.telegramBot.service.generateTask;

import aglaia.telegramBot.model.AbstractTask;
import aglaia.telegramBot.model.*;

public class KangTaskService extends AbstractTaskService {
    public KangTaskService() {
        super();
    }

    @Override
    public AbstractTask giveMeATask(Long chatId) {

        UserBot userBot = database.getUserFromDatabase(chatId);
        KangTask kangTask = database.getKangTaskByInd(userBot.getIndexOfCurrentKangTask());
        userBot.setActualTask(kangTask);

        return kangTask;
    }

}
