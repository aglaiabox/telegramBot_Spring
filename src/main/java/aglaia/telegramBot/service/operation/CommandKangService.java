package aglaia.telegramBot.service.operation;

import aglaia.telegramBot.database.Database;
import aglaia.telegramBot.entity.AbstractTask;
import aglaia.telegramBot.entity.UserBot;
import aglaia.telegramBot.entity.KangTask;
import org.springframework.stereotype.Component;

@Component
public class CommandKangService extends AbstractCommandService {
    public CommandKangService(Database database) {
        super(database);
    }

    @Override
    public AbstractTask giveMeATask(Long chatId) {

        UserBot userBot = database.getUserFromDatabase(chatId);
        KangTask kangTask = database.getKangTaskByInd(userBot.getIndexOfCurrentKangTask());
        userBot.setActualTask(kangTask);

        return kangTask;
    }

}
