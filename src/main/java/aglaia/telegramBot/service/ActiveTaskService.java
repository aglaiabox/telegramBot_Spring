package aglaia.telegramBot.service;

import aglaia.telegramBot.database.Database;
import aglaia.telegramBot.entity.AbstractTask;
import aglaia.telegramBot.entity.KangTask;
import aglaia.telegramBot.entity.UserBot;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendSticker;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;

@Getter @Setter
@Component
// хождение в базу данных и доставание активного текущего таска
public class ActiveTaskService {

    final Database database;
    RegistrationService rs;

    private static final ArrayList <String> listStikersId;
    static {
        listStikersId = new ArrayList<>();
        listStikersId.add("CAACAgIAAxkBAAEGflNje5VhaHQ5Hln4bQFKFnETijrqggACFQADwDZPE81WpjthnmTnKwQ");
        listStikersId.add("CAACAgIAAxkBAAEGfl5je5ZHgIOPUgOUFdC65EZoXl1mOQACGgEAAladvQpSdFwZ2gAB4YwrBA");
        listStikersId.add("CAACAgIAAxkBAAEGfmJje5ZzLeaIA3uG-NeHY99Axf1sJgACZQADmL-ADY4txY9SwuLhKwQ");
        listStikersId.add("CAACAgIAAxkBAAEGfmRje5aZhEkFmwj3O_sefvx88w7_mgACEQMAAm2wQgPX27SqjlRtPysE");
        listStikersId.add("CAACAgIAAxkBAAEGfnJje5d1qs-ix9ygXFp4lZhFKyh4TAACGwADJHFiGn2Usm5BdDAcKwQ");
        listStikersId.add("CAACAgIAAxkBAAEGfnVje5ehf087ZdSI2dLOMVzthcpSPAACugAD9wLID_0MODzqmFe5KwQ");
        listStikersId.add("CAACAgIAAxkBAAEGgSVjfMjmTIbpwNcHFWsvvXXGIg9CRQACGQADwDZPE9BDgPYgVxRLKwQ");
        listStikersId.add("CAACAgIAAxkBAAEGgSdjfMkIrKvWBvxLEvhsA5Nuta494wACYgIAAladvQrfUNgPvAABLqwrBA");
        listStikersId.add("CAACAgIAAxkBAAEGgSljfMkvRxG43pBd7ZLvoCWkfTLCUAACuAkAAs0hOEupTsJmAAHi-SwrBA");
        listStikersId.add("CAACAgIAAxkBAAEGgStjfMlLTe5XNXlJGgs9q4qQFU49kgACUQEAAjDUnRFgiXnyUbaU0ysE");
        listStikersId.add("CAACAgIAAxkBAAEGgS1jfMlbdJPnZuhJpLWw9xYt0vJc3AACMQEAAjDUnREb_jFE4pMwhisE");
        listStikersId.add("CAACAgIAAxkBAAEGgS9jfMlvYD0GkulJ8Eeh4KEFEcFiFQACTAEAAjDUnRH33m9fN4M5HCsE");
        listStikersId.add("CAACAgIAAxkBAAEGgTFjfMmL3eYYPsAmPNe4FRaAZ60SQQACUgEAAjDUnRERwgZS_w81pCsE");
        listStikersId.add("CAACAgEAAxkBAAEGgTNjfMmpmensLtkif7EjXHPoxhfE8AAC_wADOA6CEct71ndrpd51KwQ");
        listStikersId.add("CAACAgIAAxkBAAEGgTVjfMnPp5mZTlOUwM4xgoFzyNnVdQACXgIAAladvQr-LQK1VqHnEysE");
        listStikersId.add("CAACAgIAAxkBAAEGgTxjfMnvPDMvw5_U8ePtVLawHbNcTgACCwMAAvPjvgsFXH0ETQnCsCsE");
        listStikersId.add("CAACAgIAAxkBAAEGgT5jfMoLHhpKghRQTMWPU4R3Tn3AjwACEgMAAvPjvgt8mpuMvGvuAAErBA");
        listStikersId.add("CAACAgIAAxkBAAEGgVFjfM0uhDQY1cbT4_vAjlyASg0SyAAC-gAD9wLID3IDdK6wvGIyKwQ");
        listStikersId.add("CAACAgIAAxkBAAEGgVNjfM1P8NxPONEaodn2iJPzT2ilewACCwEAAvcCyA_F9DuYlapx2ysE");
        listStikersId.add("CAACAgIAAxkBAAEGgVVjfM1rgQ7xGDyzzjgTTFYF2EOgIQACTQADJHFiGj9If4Mjut6dKwQ");
        listStikersId.add("CAACAgIAAxkBAAEGgVdjfM2DfbZl5zDKZVFHMQ2YS-3v6AACdwUAAj-VzApljNMsSkHZTisE");
        listStikersId.add("CAACAgIAAxkBAAEGgVljfM2XWtWHZBmIMGTp9Ti88S_RswACXgUAAj-VzAqq1ncTLO-MOSsE");
        listStikersId.add("CAACAgIAAxkBAAEGgVtjfM2xJNODb_FmQbbVIz7WzHoO9QACcwUAAj-VzAo3ePzsWWk9MysE");
        listStikersId.add("CAACAgIAAxkBAAEGgV1jfM35StFrc8x-s5eNYYrs0vI8hwAC3QADMNSdEY1VJRWnGm6vKwQ");
        listStikersId.add("CAACAgIAAxkBAAEGgV9jfM4WmboD1plLZwXyoU6sL8xdXwACbAAD5KDOB25x51qyj2UFKwQ");
        listStikersId.add("CAACAgIAAxkBAAEGgWFjfM46S3NJDKeu8c3NkyiYgZQ_eQACKQADJHFiGiKockiM5SMwKwQ");
        listStikersId.add("CAACAgEAAxkBAAEGgWNjfM5tGlRnManyRJSJ7QdNyYkgSgAC-AEAAjgOghFb9OZ62rUyZCsE");
        listStikersId.add("CAACAgIAAxkBAAEGgWVjfM7ccTEN_mRDhoj82IA0Y7rpNAACtQAD9wLIDzOvqyodfzZvKwQ");
    }

    private static final ArrayList<String> listRightComments;
    static {
        listRightComments = new ArrayList<>();
        listRightComments.add("Nice!");
        listRightComments.add("Exactly!!!");
        listRightComments.add("That's right");
        listRightComments.add("So good!");
        listRightComments.add("I'm proud for you!");
    }

    public ActiveTaskService(Database database, RegistrationService rs) {
        this.database = database;
        this.rs = rs;
    }

    public SendSticker getRandomSticker (long msgChatId){
        int i = (int) (Math.random() * listStikersId.size());
        String stickerId = listStikersId.get(i);
        SendSticker sendSticker = new SendSticker();
        InputFile inputFile = new InputFile(stickerId);
        sendSticker.setSticker(inputFile);
        sendSticker.setChatId(msgChatId);
        return sendSticker;
    }

    public boolean isUserHaveAnActiveTask (long msgChatId){
        if (database.getUserFromDatabase(msgChatId).getActualTask() == null) return false;
        return true;
    }


    public boolean isAnswerCorrect (String incommingAnswer, long msgChatId) {
        UserBot userBot = database.getUserFromDatabase(msgChatId);
        AbstractTask actualTask = userBot.getActualTask();

        if (actualTask.getCorrectAnswer().equalsIgnoreCase(incommingAnswer)) {
            if (actualTask instanceof KangTask){
                userBot.setIndexOfCurrentKangTask(userBot.getIndexOfCurrentKangTask()+1);
            }
            userBot.setActualTask(null);

            return true;
        }

        else {
            return false;
        }
    }

    public String getRandomStringForRight () {
        int i = (int) (Math.random() * listRightComments.size());
        return listRightComments.get(i);
    }

    public boolean isUserHaveMoreAttentionForThisTask(Long msgChatId){
        return database.getUserFromDatabase(msgChatId).isUserHaveMoreAttentionForThisTask();
    }

    public String getAnswerString (Update update) {
        if (update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            return callbackQuery.getData();
        } else {
            Message msg = update.getMessage();
            return msg.getText();
        }
    }

    public Long getMsgChatId (Update update) {
        if (update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            return callbackQuery.getMessage().getChatId();
        } else {
            Message msg = update.getMessage();
            return msg.getChatId();
        }
    }

    public boolean isRegistrationComplete(Long chartId) {
        if (rs.isRegistrationComplete(chartId)) return true;
        return false;
    }


    public String setDataOfUserToDatabaseAddGetAnswer(Long msgChatId, String incommingAnswer) {
        return rs.setDataOfUserToDatabaseAddGetAnswer(msgChatId, incommingAnswer);
    }

//    public String getCorrectAnswer (long msgChatId){
//        UserBot userBot = database.getUserFromDatabase(msgChatId);
//        AbstractTask actualTask = userBot.getActualTask();
//        String correctAnswer = actualTask.getCorrectAnswer();
//        actualTask = null;
//        userBot.
//        return
//
//    }
}
