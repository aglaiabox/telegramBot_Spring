package aglaia.telegramBot.service.language;

import aglaia.telegramBot.model.entity.tasks.AbstractTask;
import aglaia.telegramBot.model.entity.tasks.LanguageType;
import aglaia.telegramBot.service.ActiveTaskService;
import aglaia.telegramBot.service.ConstantMessagesService;
import aglaia.telegramBot.service.RegistrationAndSettingService;
import aglaia.telegramBot.service.UserBotService;

public abstract class AbstractLanguageService {

    UserBotService userBotService;
    RegistrationAndSettingService rass;

    public AbstractLanguageService(UserBotService userBotService, RegistrationAndSettingService rass) {
        this.userBotService = userBotService;
        this.rass = rass;
    }

    public String setLanguageAndGetAnswer (Long chatId){
        return rass.getMsgTextWhenChangeLanguage(chatId, setLanguage(chatId));
    }

    public abstract LanguageType setLanguage(Long chatId);
}
