package aglaia.telegramBot;

import aglaia.telegramBot.model.entity.tasks.LanguageType;
import aglaia.telegramBot.service.ConstantMessagesService;
import org.telegram.telegrambots.meta.api.methods.send.SendSticker;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Sandbox {
    public static void main(String[] args) throws FileNotFoundException {
        String path = "src/main/resources/stickers_list.txt";
        InputStream inputStream = new FileInputStream(path);
        Yaml yaml = new Yaml();
        Map<String, String> data = yaml.load(inputStream);
        System.out.println(data);

        int i = (int) (Math.random() * 27 +1);
        String key = "st"+i;
        String stickerId = data.get(key);
        System.out.println(i + ": " + stickerId);

        stickerId = data.get("st1");
        System.out.println("st1: "+stickerId);

//
//        Map<String, Map<LanguageType, String>> mapOfMaps= new HashMap<>();
//
//        for (Map.Entry<String, String> stringStringEntry : data.entrySet()) {
//            Map.Entry<String, String> entry = (Map.Entry) stringStringEntry;
//            System.out.println("Key: " + entry.getKey());
//            System.out.println("Value: " + entry.getValue());
//            Map<LanguageType, String> m;
//            if (mapOfMaps.containsKey(entry.getKey())){
//                m = mapOfMaps.get(entry.getKey());
//            } else {
//                m = new HashMap<>();
//            }
//            m.put(languageType, entry.getValue());
//            mapOfMaps.put(entry.getKey(), m);
//        }
//
//        ConstantMessagesService constantMessagesService = new ConstantMessagesService();
//
//
//        System.out.println("print it");
//        System.out.println(constantMessagesService.getMapOfMapsConstants());
//
//        System.out.println();
//        System.out.println(constantMessagesService.getMsgText(LanguageType.RUS, "YOU_DONT_HAVE_AN_ACTIVE_TASK"));
//        System.out.println(constantMessagesService.getMsgText(LanguageType.ENG, "YOU_DONT_HAVE_AN_ACTIVE_TASK"));
//


    }
}
