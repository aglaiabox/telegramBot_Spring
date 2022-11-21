package aglaia.telegramBot.database;

import aglaia.telegramBot.model.tasks.GeneratedTask;
import aglaia.telegramBot.model.tasks.KangTask;
import aglaia.telegramBot.model.UserBot;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class Database {
    private static volatile Database database;
    Map<Integer, KangTask> kangTaskMapa;
    Map<Long, UserBot> usersMapa;


    private Database() {
        if (database != null) {
            throw new RuntimeException("Use getInstance() method to get the single instance of this class.");
        }
        this.kangTaskMapa = new HashMap<>();
        this.usersMapa = new HashMap<>();
        addKangTaskToKangTaskMapa();
    }

    public boolean isUserExist(long msgChatId) {
        if (usersMapa.containsKey(msgChatId)) return true;
        return false;
    }

    public void addNewUserToDatabasa(UserBot userBot) {
        usersMapa.put(userBot.getChatId(), userBot);
    }

    public UserBot getUserFromDatabase(long chatId) {
        if (!isUserExist(chatId)) {
            addNewUserToDatabasa(new UserBot(chatId));
        }
        return usersMapa.get(chatId);
    }

    public void addGeneratedTaskToUser(GeneratedTask generatedTask, long chatId) {
        usersMapa.get(chatId).setActualTask(generatedTask);
    }

    public boolean isUserHaveActualGeneratedTask(long chatId) {
        if (usersMapa.get(chatId).getActualTask() != null)
            return true;
        return false;
    }

    private Map<Integer, KangTask> getKangTaskMapa() {
        return kangTaskMapa;
    }

    public KangTask getKangTaskByInd(int indexOfKangTask) {
        if (!kangTaskMapa.containsKey(indexOfKangTask)) {
            return null;
        }
        return kangTaskMapa.get(indexOfKangTask);
    }

    public void setKangTaskMapa(Map<Integer, KangTask> kangTaskMapa) {
        this.kangTaskMapa = kangTaskMapa;
    }


    public Map<Long, UserBot> getUsersMapa() {
        return usersMapa;
    }

    public void setUsersMapa(Map<Long, UserBot> usersMapa) {
        this.usersMapa = usersMapa;
    }

    private void addKangTaskToKangTaskMapa() {

        KangTask kangTask = new KangTask("У Маши 3 брата и 2 сестры. Сколько братьев и сестер у ее брата Миши?",
                "3 брата и 2 сестры", "2 брата и 3 сестры",
                "2 сестры и 2 брата", "3 брата и 3 сестры",
                "невозможно определить", "b");
        // B 74 77

        KangTask kangTask2 = new KangTask("Сумма восьми чисел равна 1997. Число 997 — одно из них. Если его " +
                "заменить на 799, то новая сумма будет равна", "2195", "1799", "1899", "1979", "1998",
                "b");
        // B 83 81

        KangTask kangTask3 = new KangTask("У игрального кубика на всех парах противоположных граней сумма" +
                " очков одна и та же. Найдите эту сумму", "6", "7", "8", "9", "10",
                "b");
        // B 44 59

        kangTaskMapa.put(kangTask.getIndex(), kangTask);
        kangTaskMapa.put(kangTask2.getIndex(), kangTask2);
        kangTaskMapa.put(kangTask3.getIndex(), kangTask3);

    }


}
