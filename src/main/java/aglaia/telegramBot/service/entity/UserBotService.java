package aglaia.telegramBot.service.entity;

import aglaia.telegramBot.repository.UserBotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserBotService {

    @Autowired
    private final UserBotRepository userBotRepository;

    public UserBotService(UserBotRepository userBotRepository) {
        this.userBotRepository = userBotRepository;
    }
}
