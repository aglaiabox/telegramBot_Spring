package aglaia.telegramBot.service;

import aglaia.telegramBot.model.entity.UserBot;
import aglaia.telegramBot.model.entity.tasks.KangTask;
import aglaia.telegramBot.repository.UserBotRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserBotService {

    private final UserBotRepository userBotRepository;

    public UserBotService(UserBotRepository userBotRepository) {
        this.userBotRepository = userBotRepository;
    }

    public UserBot save (UserBot userBot){
        return userBotRepository.save(userBot);
    }

    public Optional<UserBot> get (Long id) {
        return userBotRepository.findById(id);
    }

    public boolean existsByChatId (Long chatId){
        return userBotRepository.existsByChatId(chatId);
    }

    public Optional<UserBot> findByChatId(Long chatId){
        return userBotRepository.findByChatId(chatId);
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
