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

    RegistrationAndSettingService rs;
    UserBotService userBotService;
    ConstantMessagesService cms;


//    private static final ArrayList<String> listStikersId;

//    static {
//        listStikersId = new ArrayList<>();
//        listStikersId.add("CAACAgIAAxkBAAEGflNje5VhaHQ5Hln4bQFKFnETijrqggACFQADwDZPE81WpjthnmTnKwQ");
//        listStikersId.add("CAACAgIAAxkBAAEGfl5je5ZHgIOPUgOUFdC65EZoXl1mOQACGgEAAladvQpSdFwZ2gAB4YwrBA");
//        listStikersId.add("CAACAgIAAxkBAAEGfmJje5ZzLeaIA3uG-NeHY99Axf1sJgACZQADmL-ADY4txY9SwuLhKwQ");
//        listStikersId.add("CAACAgIAAxkBAAEGfmRje5aZhEkFmwj3O_sefvx88w7_mgACEQMAAm2wQgPX27SqjlRtPysE");
//        listStikersId.add("CAACAgIAAxkBAAEGfnJje5d1qs-ix9ygXFp4lZhFKyh4TAACGwADJHFiGn2Usm5BdDAcKwQ");
//        listStikersId.add("CAACAgIAAxkBAAEGfnVje5ehf087ZdSI2dLOMVzthcpSPAACugAD9wLID_0MODzqmFe5KwQ");
//        listStikersId.add("CAACAgIAAxkBAAEGgSVjfMjmTIbpwNcHFWsvvXXGIg9CRQACGQADwDZPE9BDgPYgVxRLKwQ");
//        listStikersId.add("CAACAgIAAxkBAAEGgSdjfMkIrKvWBvxLEvhsA5Nuta494wACYgIAAladvQrfUNgPvAABLqwrBA");
//        listStikersId.add("CAACAgIAAxkBAAEGgSljfMkvRxG43pBd7ZLvoCWkfTLCUAACuAkAAs0hOEupTsJmAAHi-SwrBA");
//        listStikersId.add("CAACAgIAAxkBAAEGgStjfMlLTe5XNXlJGgs9q4qQFU49kgACUQEAAjDUnRFgiXnyUbaU0ysE");
//        listStikersId.add("CAACAgIAAxkBAAEGgS1jfMlbdJPnZuhJpLWw9xYt0vJc3AACMQEAAjDUnREb_jFE4pMwhisE");
//        listStikersId.add("CAACAgIAAxkBAAEGgS9jfMlvYD0GkulJ8Eeh4KEFEcFiFQACTAEAAjDUnRH33m9fN4M5HCsE");
//        listStikersId.add("CAACAgIAAxkBAAEGgTFjfMmL3eYYPsAmPNe4FRaAZ60SQQACUgEAAjDUnRERwgZS_w81pCsE");
//        listStikersId.add("CAACAgEAAxkBAAEGgTNjfMmpmensLtkif7EjXHPoxhfE8AAC_wADOA6CEct71ndrpd51KwQ");
//        listStikersId.add("CAACAgIAAxkBAAEGgTVjfMnPp5mZTlOUwM4xgoFzyNnVdQACXgIAAladvQr-LQK1VqHnEysE");
//        listStikersId.add("CAACAgIAAxkBAAEGgTxjfMnvPDMvw5_U8ePtVLawHbNcTgACCwMAAvPjvgsFXH0ETQnCsCsE");
//        listStikersId.add("CAACAgIAAxkBAAEGgT5jfMoLHhpKghRQTMWPU4R3Tn3AjwACEgMAAvPjvgt8mpuMvGvuAAErBA");
//        listStikersId.add("CAACAgIAAxkBAAEGgVFjfM0uhDQY1cbT4_vAjlyASg0SyAAC-gAD9wLID3IDdK6wvGIyKwQ");
//        listStikersId.add("CAACAgIAAxkBAAEGgVNjfM1P8NxPONEaodn2iJPzT2ilewACCwEAAvcCyA_F9DuYlapx2ysE");
//        listStikersId.add("CAACAgIAAxkBAAEGgVVjfM1rgQ7xGDyzzjgTTFYF2EOgIQACTQADJHFiGj9If4Mjut6dKwQ");
//        listStikersId.add("CAACAgIAAxkBAAEGgVdjfM2DfbZl5zDKZVFHMQ2YS-3v6AACdwUAAj-VzApljNMsSkHZTisE");
//        listStikersId.add("CAACAgIAAxkBAAEGgVljfM2XWtWHZBmIMGTp9Ti88S_RswACXgUAAj-VzAqq1ncTLO-MOSsE");
//        listStikersId.add("CAACAgIAAxkBAAEGgVtjfM2xJNODb_FmQbbVIz7WzHoO9QACcwUAAj-VzAo3ePzsWWk9MysE");
//        listStikersId.add("CAACAgIAAxkBAAEGgV1jfM35StFrc8x-s5eNYYrs0vI8hwAC3QADMNSdEY1VJRWnGm6vKwQ");
//        listStikersId.add("CAACAgIAAxkBAAEGgV9jfM4WmboD1plLZwXyoU6sL8xdXwACbAAD5KDOB25x51qyj2UFKwQ");
//        listStikersId.add("CAACAgIAAxkBAAEGgWFjfM46S3NJDKeu8c3NkyiYgZQ_eQACKQADJHFiGiKockiM5SMwKwQ");
//        listStikersId.add("CAACAgEAAxkBAAEGgWNjfM5tGlRnManyRJSJ7QdNyYkgSgAC-AEAAjgOghFb9OZ62rUyZCsE");
//        listStikersId.add("CAACAgIAAxkBAAEGgWVjfM7ccTEN_mRDhoj82IA0Y7rpNAACtQAD9wLIDzOvqyodfzZvKwQ");
//    }


    public ActiveTaskService(RegistrationAndSettingService rs, UserBotService userBotService, ConstantMessagesService cms) {
        this.rs = rs;
        this.userBotService = userBotService;
        this.cms = cms;
//        listRightComments = new ArrayList<>();
//
//        for (int i = 0; i < 5; i++) {
//            String nameWithPostfix = name+i;
//            listRightComments.add(cms.getMsgText())
//        }
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
//                userBot.addKangTaskToListOfDone((KangTask) actualTask, true);
//            } else {
//                userBot.oneMoreDoneGeneratedTaskDone();
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
