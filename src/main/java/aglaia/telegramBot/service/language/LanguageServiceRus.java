package aglaia.telegramBot.service.language;

import aglaia.telegramBot.model.entity.tasks.LanguageType;
import aglaia.telegramBot.service.RegistrationAndSettingService;
import aglaia.telegramBot.service.UserBotService;
import org.springframework.stereotype.Component;

@Component
public class LanguageServiceRus extends AbstractLanguageService{

    public LanguageServiceRus(UserBotService userBotService, RegistrationAndSettingService rass) {
        super(userBotService, rass);
    }

    @Override
    public LanguageType setLanguage(Long chatId) {
        return userBotService.setLanguage(chatId, LanguageType.RUS);
    }
}
