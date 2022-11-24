package aglaia.telegramBot.service.menu;

import aglaia.telegramBot.database.Database;

public abstract class AbstractMenuService {
    Database database;

    public AbstractMenuService (Database database) {
        this.database = database;
    }


}
