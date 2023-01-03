package aglaia.telegramBot;

import aglaia.telegramBot.model.entity.tasks.KangTask;
import aglaia.telegramBot.model.entity.tasks.LanguageType;
import aglaia.telegramBot.service.ConstantMessagesService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.checkerframework.checker.units.qual.K;
import org.telegram.telegrambots.meta.api.methods.send.SendSticker;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Sandbox {
//    String path = "src/main/resources/messages_eng.txt";
//    public static void main(String[] args)  {
//        ArrayList <KangTask> list = addKangTaskToKangTaskMapa();
//        KangTask kangTask = list.get(0);
//
//        String path2 = "src/main/resources/kang_tasks.txt";
//
//
//        for (KangTask k:
//             list) {
//            saveKangTAskToFile(k, path2);
//        }
//
////        getYamlToObjectsFromFile(path2);
//
//
//
//    }
//
//    private static void saveKangTAskToFile(KangTask kangTask, String path2) {
//        // ObjectMapper создается так же, как и раньше
//        ObjectMapper om = new ObjectMapper(new YAMLFactory());
//
//        // Мы пишем `employee` в` person2.yaml`
//        try {
//            om.writeValue(new File(path2), kangTask);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    private static KangTask getYamlToObjectsFromFile(String path2){
//        // Загрузка файла YAML из папки
//        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
//        try {
//            File file = new File(classLoader.getResource("kang_tasks.txt").getFile());
//        } catch (NullPointerException e) {
//
//        }
//
//
//        // Создание нового ObjectMapper как YAMLFactory
//        ObjectMapper om = new ObjectMapper(new YAMLFactory());
//
//        // Отображение сотрудника из файла YAML в класс Employee
//        KangTask kangTask = null;
//        try {
//            kangTask = om.readValue(file, KangTask.class);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        // Распечатка информации
//        System.out.println("Kang task info " + kangTask.toString());
//
//        // Получите доступ к первому элементу списка и распечатайте его
////        System.out.println("Accessing first element: " + kangTask.getColleagues().get(0).toString());
//    return kangTask;
//    }
//
//    private static Map<String, String> getFromFileToMapa(String path) throws FileNotFoundException {
//        InputStream inputStream = new FileInputStream(path);
//        Yaml yaml = new Yaml();
//        Map<String, String> data = yaml.load(inputStream);
//        System.out.println(data);
//        return data;
//    }
//
//
//    private static ArrayList <KangTask> addKangTaskToKangTaskMapa() {
//
//        ArrayList <KangTask> list = new ArrayList<>();
//
//        list.add(new KangTask("У Маши 3 брата и 2 сестры. Сколько братьев и сестер у ее брата Миши?",
//                "3 брата и 2 сестры", "2 брата и 3 сестры",
//                "2 сестры и 2 брата", "3 брата и 3 сестры",
//                "невозможно определить", "b", 74, 77, 3));
//
//        list.add(new KangTask("Сумма восьми чисел равна 1997. Число 997 — одно из них. Если его " +
//                "заменить на 799, то новая сумма будет равна", "2195", "1799", "1899", "1979", "1998",
//                "b", 83, 81, 3));
//        // B 83 81
//
//        list.add(new KangTask("У игрального кубика на всех парах противоположных граней сумма" +
//                " очков одна и та же. Найдите эту сумму", "6", "7", "8", "9", "10",
//                "b", 44, 59, 3));
//        // B 44 59
//
//        return list;
//
//    }
}
