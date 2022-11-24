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

    public boolean isRegistrationComplete(Long chartId) {
        if (rs.isThisUserExist(chartId) && rs.isThisUserHasName(chartId) && rs.isThisUserHasAge(chartId)) return true;
        return false;
    }


    public String getTextToSend(Long chatId) {
        return rs.getTextToSend(chatId);
    }
}
