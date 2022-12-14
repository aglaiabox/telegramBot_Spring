package aglaia.telegramBot.service;

import aglaia.telegramBot.model.entity.tasks.LanguageType;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendSticker;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@Component
@Log4j2

public class ConstantMessagesService {
    private final String PATH = "src/main/resources/";
    private Map<LanguageType, String> namesOfFiles;
    private Map<String, Map<LanguageType, String>> mapOfMapsConstants;
    private Map<String, String> namesOfStickers;

    String nameOfRightString = "RIGHT_COMMENT_";
    int howManyCounsOfRightStringIsHere = 5;
    int howManyStickersIsHere = 33;

    public ConstantMessagesService() {
        namesOfFiles = new HashMap<>();
        namesOfFiles.put(LanguageType.RUS, "messages_rus.txt");
        namesOfFiles.put(LanguageType.ENG, "messages_eng.txt");
        mapOfMapsConstants = new HashMap<>();

        for (Map.Entry<LanguageType, String> mapEntery : namesOfFiles.entrySet()) {
            addDataToMapOfMapsConstants(mapEntery.getKey(), mapEntery.getValue());
        }

        namesOfStickers = new HashMap<>();
        namesOfStickers = getDataFromFile("stickers_list.txt");
    }

    public String getMsgText(LanguageType languageType, String key) {
        log.info(String.format("Get msg \"%s\" in language %s", key, languageType.toString()));
        return mapOfMapsConstants.get(key).get(languageType);
    }

    public String getRandomStringForRight(LanguageType languageType) {
        int i = (int) (Math.random() * howManyCounsOfRightStringIsHere + 1);
        String name = nameOfRightString + i;
        log.info(String.format("Get msg \"%s\" in language %s", nameOfRightString, languageType.toString()));
        return getMsgText(languageType, name);
    }

    public SendSticker getRandomSticker(long msgChatId) {
        int i = (int) (Math.random() * namesOfStickers.size() + 1);
        String stickerId = namesOfStickers.get("st" + i);
        SendSticker sendSticker = new SendSticker();
        InputFile inputFile = new InputFile(stickerId);
        sendSticker.setSticker(inputFile);
        sendSticker.setChatId(msgChatId);
        log.info(String.format("Get sticker N %d", i));
        return sendSticker;
    }

    public Map<String, Map<LanguageType, String>> addDataToMapOfMapsConstants(LanguageType languageType, String namesOfFiles) {
        Map<String, String> data = getDataFromFile(namesOfFiles);
        for (Map.Entry<String, String> entry : data.entrySet()) {
            log.info("addDataToMapOfMapsConstants Key: " + entry.getKey() + "Value: " + entry.getValue());
            Map<LanguageType, String> m;
            if (mapOfMapsConstants.containsKey(entry.getKey())) {
                m = mapOfMapsConstants.get(entry.getKey());
                log.info("addDataToMapOfMapsConstants: add to HashMap");
            } else {
                m = new HashMap<>();
                log.info("addDataToMapOfMapsConstants: created new HashMap");
            }
            m.put(languageType, entry.getValue());
            mapOfMapsConstants.put(entry.getKey(), m);
        }
        log.info("addDataToMapOfMapsConstants: mapa done");
        return mapOfMapsConstants;
    }

    private Map<String, String> getDataFromFile(String nameOfFile) {
        Map<String, String> data;
        log.info("getDataFromFile");
        try (InputStream inputStream = new FileInputStream(PATH + nameOfFile)) {
            Yaml yaml = new Yaml();
            data = yaml.load(inputStream);
            log.info("getDataFromFile: successed: ");
        } catch (IOException e) {
            log.error("getDataFromFile: error: "+e.getMessage());
            throw new RuntimeException(e);
        }
        log.info("getDataFromFile data: " +data);
        return data;
    }


}
