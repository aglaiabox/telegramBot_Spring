package aglaia.telegramBot.service.menu;

import aglaia.telegramBot.database.Database;
import aglaia.telegramBot.service.RegistrationService;
import org.springframework.stereotype.Component;

@Component
public class MenuServiceStart extends AbstractMenuService {

    RegistrationService rs;

    public MenuServiceStart(Database database, RegistrationService registrationService) {
        super(database);
        this.rs = registrationService;
    }

}
