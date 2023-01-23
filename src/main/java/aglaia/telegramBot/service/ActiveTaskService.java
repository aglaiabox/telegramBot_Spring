package aglaia.telegramBot.service;

import aglaia.telegramBot.Bot;
import aglaia.telegramBot.model.entity.tasks.*;
import aglaia.telegramBot.model.entity.UserBot;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendSticker;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Getter
@Setter
@Component
// хождение в базу данных и доставание активного текущего таска
public class ActiveTaskService {

    private final RegistrationAndSettingService rs;
    private final UserBotService userBotService;
    private final ConstantMessagesService cms;


    public ActiveTaskService(RegistrationAndSettingService rs, UserBotService userBotService, ConstantMessagesService cms) {
        this.rs = rs;
        this.userBotService = userBotService;
        this.cms = cms;
    }

    public SendSticker getRandomSticker(long msgChatId) {
        return cms.getRandomSticker(msgChatId);
    }

    public boolean isUserHaveAnActiveTask(long msgChatId) {
        if (userBotService.findByChatId(msgChatId).isEmpty()) throw new IllegalArgumentException();
        return userBotService.findByChatId(msgChatId).get().getActualTask() != null;
    }


    public boolean isAnswerCorrect(String incommingAnswer, long msgChatId) {
        if (userBotService.findByChatId(msgChatId).isEmpty()) throw new IllegalArgumentException();
        UserBot userBot = userBotService.findByChatId(msgChatId).get();
        AbstractTask actualTask = userBot.getActualTask();

        if (actualTask.getCorrectAnswer().equalsIgnoreCase(incommingAnswer)) {
            if (actualTask instanceof KangTask) {
                userBot.setActualKangTaskDone(true);
            } else if (actualTask instanceof GeneratedTask) {
                userBot.addOneDoneGeneratedTask();
            }
            userBot.setActualTask(null);
            userBotService.save(userBot);
            return true;
        }
        return false;

    }


    public String getAnswerString(Update update) {
        if (update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            return callbackQuery.getData();
        } else {
            Message msg = update.getMessage();
            return msg.getText();
        }
    }

    public Long getMsgChatId(Update update) {
        if (update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            return callbackQuery.getMessage().getChatId();
        } else {
            Message msg = update.getMessage();
            return msg.getChatId();
        }
    }

    public boolean isRegistrationComplete(Long chartId) {
        return rs.isRegistrationComplete(chartId);
    }


    public String setDataOfUserToDatabaseAddGetAnswer(Long msgChatId, String incommingAnswer) {
        return rs.setDataOfUserToDatabaseAddGetAnswer(msgChatId, incommingAnswer);
    }


    public String getText(Long msgChatId, boolean isAnswerCorrect) {
        String text;
        LanguageType languageType = userBotService.getLanguage(msgChatId);
        if (isAnswerCorrect) {
            text = cms.getRandomStringForRight(languageType);
        } else {
            text = cms.getMsgText(languageType, "WRONG");
        }
        return text;
    }

    public String getTextIfNoActualTask(Long msgChatId) {
        LanguageType languageType = userBotService.getLanguage(msgChatId);
        return cms.getMsgText(languageType, "YOU_DONT_HAVE_AN_ACTIVE_TASK") + System.lineSeparator() +
                Bot.MULTIPLY + System.lineSeparator() + Bot.DIVISION + System.lineSeparator() + Bot.KANG_TASK;
    }

    public String getTextByNameOfString(Long msgChatId, String nameOfStrng) {
        LanguageType languageType = userBotService.getLanguage(msgChatId);
        return cms.getMsgText(languageType, nameOfStrng);
    }
}
