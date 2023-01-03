package aglaia.telegramBot.service;

import aglaia.telegramBot.model.entity.UserBot;
import aglaia.telegramBot.model.entity.tasks.LanguageType;
import aglaia.telegramBot.repository.UserBotRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
public class UserBotService {

    private final UserBotRepository userBotRepository;

    public UserBotService(UserBotRepository userBotRepository) {
        this.userBotRepository = userBotRepository;
    }

    public UserBot save (UserBot userBot){
        log.info(String.format("New user: id %d, name %s", userBot.getId(), userBot.getName()));
        return userBotRepository.save(userBot);
    }

    public LanguageType getLanguage (Long chatId){
        Optional<UserBot> optionalUserBot = findByChatId(chatId);
        log.info(String.format("Get language for user id %d", chatId));
        return optionalUserBot.map(UserBot::getLanguageType).orElse(null);
    }

    public Optional<UserBot> get (Long id) {
        log.info(String.format("Get user id %d", id));
        return userBotRepository.findById(id);
    }

    public boolean existsByChatId (Long chatId){
        log.info(String.format("Check if exist user id %d ", chatId));
        return userBotRepository.existsByChatId(chatId);
    }

    public Optional<UserBot> findByChatId(Long chatId){
        log.info(String.format("Get user fy chat id %d", chatId));
        return userBotRepository.findByChatId(chatId);
    }

    public LanguageType setLanguage (Long chatId, LanguageType languageType){
        Optional<UserBot> optionalUserBot = findByChatId(chatId);
        if (optionalUserBot.isEmpty()) {
            log.error(String.format("No user id %d in the database", chatId));
            throw new NullPointerException();
        }
        UserBot userBot = optionalUserBot.get();
        userBot.setLanguageType(languageType);
        log.info(String.format("Set language %s to user id %d name %s", languageType.toString(), userBot.getId(), userBot.getName()));
        save(userBot);
        return userBot.getLanguageType();
    }

    // TODO: 11.12.2022 РАЗОБРАТЬСЯ С ЭТИМ
//    public KangTask getActualKangTask (Long chatId) throws Exception {
//        Optional<UserBot> optionalUserBot = findByChatId(chatId);
//        if (optionalUserBot.isEmpty()) throw new IllegalArgumentException();
//
//        UserBot userBot = optionalUserBot.get();
//        KangTask kt = userBot.getActualKangTask();
////        if (userBot.isThisKangTaskDoneByThisUser(kt)){    //todo это надо вернуть или поменять механизм смены задания
////            KangTask ktNew = kt.getNext();
////            userBot.setActualKangTask(ktNew);
////        }
//        save(userBot);
//        return userBot.getActualKangTask();
//    }

}
