package aglaia.telegramBot.repository;

import aglaia.telegramBot.model.entity.UserBot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface UserBotRepository extends JpaRepository<UserBot, Long> {

    Optional<UserBot> findByChatId(Long chatId);

    boolean existsByChatId(Long chatId);
}
