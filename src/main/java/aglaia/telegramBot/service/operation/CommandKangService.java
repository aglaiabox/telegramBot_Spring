package aglaia.telegramBot.service.operation;

import aglaia.telegramBot.database.Database;
import aglaia.telegramBot.model.AbstractTask;
import aglaia.telegramBot.model.*;
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
