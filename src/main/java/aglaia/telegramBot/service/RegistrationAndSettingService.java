package aglaia.telegramBot.service;

import aglaia.telegramBot.model.entity.UserBot;
import aglaia.telegramBot.model.entity.tasks.LanguageType;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class RegistrationAndSettingService {
    UserBotService userBotService;
    ConstantMessagesService cms;

    public RegistrationAndSettingService(UserBotService userBotService, ConstantMessagesService cms) {
        this.userBotService = userBotService;
        this.cms = cms;
    }

    public boolean isThisUserExist(Long chatId) {
        log.info("check if user {} exist", chatId);
        return userBotService.existsByChatId(chatId);
    }

    public boolean isThisUserHasName(Long chatId) {
        if (userBotService.findByChatId(chatId).isPresent()){
            log.info("check if user {} has name", chatId);
            return userBotService.findByChatId(chatId).get().getName() != null;
        } else {
            log.error("ERROR!!! RegistrationAndSettingService check if user has name but user does not exist");
            throw new IllegalArgumentException();
        }

    }

    public boolean isThisUserHasAge(Long chatId) {
        if (userBotService.findByChatId(chatId).isPresent()){
            log.info("check if user {} has name", chatId);
            return userBotService.findByChatId(chatId).get().getAge() != 0;
        } else {
            log.error("ERROR!!! RegistrationAndSettingService check if user has age but user does not exist");
            throw new IllegalArgumentException();
        }

    }

    public boolean isRegistrationComplete(Long chatId) {
        return isThisUserExist(chatId) && isThisUserHasName(chatId) && isThisUserHasAge(chatId);
    }


    public String setDataOfUserToDatabaseAddGetAnswer(Long chatId, String incommingAnswer) {
        LanguageType languageType = userBotService.getLanguage(chatId);
        if (!userBotService.existsByChatId(chatId) && languageType == null) {
            userBotService.save(new UserBot(chatId));
            return "/rus /eng";
        } else if (!isThisUserHasName(chatId)) {
            UserBot userBot = userBotService.findByChatId(chatId).get();
            // todo тут надо добавить проверку имени
            userBot.setName(incommingAnswer);
            userBotService.save(userBot);
            return String.format(cms.getMsgText(languageType, "NICE_TO_MEET_YOU_S_HOW_OLD_ARE_YOU"), incommingAnswer);
        } else if (!isThisUserHasAge(chatId)) {
            try {
                int age = Integer.parseInt(incommingAnswer.trim());
                UserBot userBot = userBotService.findByChatId(chatId).get();
                userBot.setAge(age);
                userBotService.save(userBot);
                return cms.getMsgText(languageType, "TASKS_WHAT_DO_YOU_WANT_TO_SOLVE");
            } catch (Exception e) {
                return cms.getMsgText(languageType, "I_CAN_T_UNDERSTAND_YOU_PLEASE_DIVE_ME_A_DIGIT_HOW_OLD_ARE_YOU");
            }
        }
        return "не понял";
    }

    public String getMsgTextWhenChangeLanguage(Long chatId, LanguageType languageType) {
        String text = cms.getMsgText(languageType, "LANGUAGE");
        String text2 = "";
        if (isThisUserExist(chatId) && !isThisUserHasName(chatId)) {
            text2 = cms.getMsgText(languageType, "HI_MY_NAME_IS_MATH_ROBOT_WHAT_IS_YOUR_NAME");
        }
        return text + "\n" + text2;
    }
}
