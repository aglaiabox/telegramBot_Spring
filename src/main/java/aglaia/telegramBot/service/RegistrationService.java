package aglaia.telegramBot.service;

import aglaia.telegramBot.database.Database;
import org.springframework.stereotype.Component;

@Component
public class RegistrationService {
    public static final String LET_S_START = "Let's /start";
    Database database;

    public RegistrationService (Database database) {
        this.database = database;
    }

    public boolean isThisUserExist (Long chartId){
        if (database.getUserFromDatabase(chartId) != null) return true;
        return false;
    }

    public boolean isThisUserHasName (Long chartId){
        if (database.getUserFromDatabase(chartId).getName() != null) return true;
        return false;
    }

    public boolean isThisUserHasAge (Long chartId){
        if (database.getUserFromDatabase(chartId).getAge() != 0) return true;
        return false;
    }

    public boolean isRegistrationComplete (Long chartId){
        if (isThisUserExist(chartId) && isThisUserHasName(chartId) && isThisUserHasAge(chartId)) return true;
        return false;
    }



    public String getTextToSend(Long chatId) {
        if (database.getUserFromDatabase(chatId).isItFirstContact()) {
            database.getUserFromDatabase(chatId).setItFirstContact(false);
            return "Hi! My name is Math Robot. I'm robot. I'm so glad to see you! What is your name?";
        } else if (!isThisUserHasName(chatId)) {
            return "I still don't know your name! How can I call you?";
        } else if (!isThisUserHasAge(chatId)) {
            return "How old are you?";
        } else {
            return "Nice to meet you!";
        }
    }


    public String setDataOfUserToDatabaseAddGetAnswer(Long chatId, String incommingAnswer) {
        if (isThisUserExist(chatId) && !isThisUserHasName(chatId)){
            database.getUserFromDatabase(chatId).setName(incommingAnswer);
            return "Nice to meet you, "+incommingAnswer+" How old are you?";
        } else if (isThisUserHasName(chatId) && !isThisUserHasAge(chatId)){
            try {
                int age = Integer.parseInt(incommingAnswer.trim());
                database.getUserFromDatabase(chatId).setAge(age);
            } catch (Exception e){
                return "I can't understand you. Please dive me a digital number. I should know how old are you?";
            }
        }
        return LET_S_START;
    }
}
