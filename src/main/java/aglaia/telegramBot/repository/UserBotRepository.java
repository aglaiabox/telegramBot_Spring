package aglaia.telegramBot.repository;

import aglaia.telegramBot.entity.UserBot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserBotRepository extends JpaRepository <UserBot, Long>
{

//    List<UserBot> findUserByUserId(Long userId);
    //просто правильное название метода даст возможность
    //избежать запросов на SQL

//    @Query("select u from Users u where u.email like '%@gmail.com%'")
//        //если этого мало можно написать
//        //собственный запрос на языке похожем на SQL
//    List<UserBot> findWhereEmailIsGmail();
//
//    @Query(value = "select * from users where name like '%smith%'", nativeQuery = true)
//        //если и этого мало - можно написать запрос на чистом SQL и все это будет работать
//    List<Users> findWhereNameStartsFromSmith();
}
