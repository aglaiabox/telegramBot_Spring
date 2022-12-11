package aglaia.telegramBot.service;

import aglaia.telegramBot.model.entity.UserBot;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RegistrationService {
    public static final String LET_S_START = "Let's /start";
    public static final String HI_MY_NAME_IS_MATH_ROBOT_WHAT_IS_YOUR_NAME = "Hi! My name is Math Robot. I'm robot. I'm so glad to see you! What is your name?";
    public static final String NICE_TO_MEET_YOU_S_HOW_OLD_ARE_YOU = "Nice to meet you, %s. How old are you?";
    public static final String I_CAN_T_UNDERSTAND_YOU_PLEASE_DIVE_ME_A_DIGIT_HOW_OLD_ARE_YOU = "I can't understand you. Please dive me a digit. How old are you?";
    //    Database database;
    UserBotService userBotService;

    public RegistrationService(UserBotService userBotService) {
        this.userBotService = userBotService;
    }

    public boolean isThisUserExist(Long chatId) {
        return userBotService.existsByChatId(chatId);
    }

    public boolean isThisUserHasName(Long chatId) {
        if (userBotService.findByChatId(chatId).isPresent()){
            return userBotService.findByChatId(chatId).get().getName() != null;
        } else {
            throw new IllegalArgumentException();
        }

    }

    public boolean isThisUserHasAge(Long chatId) {
        if (userBotService.findByChatId(chatId).isPresent()){
            return userBotService.findByChatId(chatId).get().getAge() != 0;
        } else {
            throw new IllegalArgumentException();
        }

    }

    public boolean isRegistrationComplete(Long chatId) {
        return isThisUserExist(chatId) && isThisUserHasName(chatId) && isThisUserHasAge(chatId);
    }


    public String setDataOfUserToDatabaseAddGetAnswer(Long chatId, String incommingAnswer) {
        if (!userBotService.existsByChatId(chatId)) {
            userBotService.save(new UserBot(chatId));
            return HI_MY_NAME_IS_MATH_ROBOT_WHAT_IS_YOUR_NAME;
        } else if (isThisUserExist(chatId) && !isThisUserHasName(chatId)) {
            UserBot userBot = userBotService.findByChatId(chatId).get();
            userBot.setName(incommingAnswer);
            userBotService.save(userBot);
            return String.format(NICE_TO_MEET_YOU_S_HOW_OLD_ARE_YOU, incommingAnswer);
        } else if (isThisUserHasName(chatId) && !isThisUserHasAge(chatId)) {
            try {
                int age = Integer.parseInt(incommingAnswer.trim());
                UserBot userBot = userBotService.findByChatId(chatId).get();
                userBot.setAge(age);
                userBotService.save(userBot);
            } catch (Exception e) {
                return I_CAN_T_UNDERSTAND_YOU_PLEASE_DIVE_ME_A_DIGIT_HOW_OLD_ARE_YOU;
            }
        }
        return null;
    }
}
