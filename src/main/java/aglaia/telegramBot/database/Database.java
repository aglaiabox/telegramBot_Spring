package aglaia.telegramBot.database;

import aglaia.telegramBot.model.entity.tasks.GeneratedTask;
import aglaia.telegramBot.model.entity.tasks.KangTask;
import aglaia.telegramBot.model.entity.UserBot;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.bots.AbsSender;

import java.util.HashMap;
import java.util.Map;

@Component
public class Database {
    private static volatile Database database;
//    Map<Integer, KangTask> kangTaskMapa;
//    Map<Long, UserBot> usersMapa;


    private Database() {
        if (database != null) {
            throw new RuntimeException("Use getInstance() method to get the single instance of this class.");
        }
//        this.kangTaskMapa = new HashMap<>();
//        this.usersMapa = new HashMap<>();
        addKangTaskToKangTaskMapa();
    }

//    public boolean isUserExist(long msgChatId) {
//        if (usersMapa.containsKey(msgChatId)) return true;
//        return false;
//    }

//    public void addNewUserToDatabasa(UserBot userBot) {
//        usersMapa.put(userBot.getChatId(), userBot);
//    }



    private void addKangTaskToKangTaskMapa() {

        KangTask kangTask = new KangTask("У Маши 3 брата и 2 сестры. Сколько братьев и сестер у ее брата Миши?",
                "3 брата и 2 сестры", "2 брата и 3 сестры",
                "2 сестры и 2 брата", "3 брата и 3 сестры",
                "невозможно определить", "b");
        kangTask.setScoreFor4(74);
        kangTask.setScoreFor5(77);
        kangTask.setDifficultyLevel(3);

        KangTask kangTask2 = new KangTask("Сумма восьми чисел равна 1997. Число 997 — одно из них. Если его " +
                "заменить на 799, то новая сумма будет равна", "2195", "1799", "1899", "1979", "1998",
                "b");
        kangTask2.setScoreFor4(83);
        kangTask2.setScoreFor5(81);
        kangTask2.setDifficultyLevel(3);
        // B 83 81

        KangTask kangTask3 = new KangTask("У игрального кубика на всех парах противоположных граней сумма" +
                " очков одна и та же. Найдите эту сумму", "6", "7", "8", "9", "10",
                "b");
        kangTask3.setScoreFor4(44);
        kangTask3.setScoreFor5(59);
        kangTask3.setDifficultyLevel(3);
        // B 44 59


    }


}
