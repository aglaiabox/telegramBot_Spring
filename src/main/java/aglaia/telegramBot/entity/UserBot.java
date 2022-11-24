package aglaia.telegramBot.entity;

import aglaia.telegramBot.commands.menu.MenuCommandHello;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.persistence.*;
import java.util.Map;

@Entity
@Getter @Setter
@NoArgsConstructor
public class UserBot {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatId;
    @Column
    private String name;
    @Column int age;
    @Column
    private int IndexOfCurrentKangTask;
    boolean isItFirstContact;

    // должен хранить последний активный таск - любой
//    @Column
//    @OneToMany
    @Transient
    private AbstractTask actualTask;

    private int numberOfAttempts4ActualTask;

//    @Column
    @Transient
    Map<KangTask, Boolean> mapaOfDoneTasks;

    @Column
    int scoreOfDoneKangTasks;

    @Column
    int countOfGeneratedTasks;

    @Column
    int rating = scoreOfDoneKangTasks+countOfGeneratedTasks;

    public UserBot(Long chatId) {
        this.chatId = chatId;
        IndexOfCurrentKangTask = 1;
        isItFirstContact = true;
    }

    public void setActualTask(AbstractTask actualTask) {
        countOfGeneratedTasks = 0;
        this.actualTask = actualTask;
    }

    public void addKangTaskToListOfDone (KangTask kangTask, boolean isTaskDoneRight){

        mapaOfDoneTasks.put(kangTask, isTaskDoneRight);
        if(isTaskDoneRight){
            if (age<9) scoreOfDoneKangTasks +=kangTask.scoreFor4;
            if (age>9 && age <12) scoreOfDoneKangTasks +=kangTask.scoreFor5;
            if (age==9) {
                int i = (kangTask.scoreFor4+kangTask.scoreFor5)/2;
                scoreOfDoneKangTasks +=i;
            }
            if (age>=12 && age<18){
                int i = kangTask.scoreFor5 - (kangTask.scoreFor5/4);
                scoreOfDoneKangTasks +=i;
            }
            if (age>=18){
                int i = kangTask.scoreFor5 /2;
                scoreOfDoneKangTasks +=i;
            }

            if (countOfGeneratedTasks>0) scoreOfDoneKangTasks=-4;
            if (countOfGeneratedTasks>1) scoreOfDoneKangTasks=-5;
            if (countOfGeneratedTasks>2) scoreOfDoneKangTasks=-9;
        }
    }

    public void addOneMoreDoneGeneratedTask (){
        countOfGeneratedTasks++;
    }

    public boolean isUserHaveMoreAttentionForThisTask() {
        numberOfAttempts4ActualTask++;
        if (numberOfAttempts4ActualTask<3) return true;
        return false;
    }

    public boolean isItFirstContact() {
        return isItFirstContact;
    }
}
