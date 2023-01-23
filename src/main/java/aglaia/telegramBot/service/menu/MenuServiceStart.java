package aglaia.telegramBot.service.menu;

import aglaia.telegramBot.service.RegistrationAndSettingService;
import org.springframework.stereotype.Component;

@Component
public class MenuServiceStart extends AbstractMenuService {

    private final RegistrationAndSettingService rs;

    public MenuServiceStart(RegistrationAndSettingService registrationAndSettingService) {
        this.rs = registrationAndSettingService;
    }

}
