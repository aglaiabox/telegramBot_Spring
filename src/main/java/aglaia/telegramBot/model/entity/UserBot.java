package aglaia.telegramBot.model.entity;

import aglaia.telegramBot.model.entity.tasks.AbstractTask;
import aglaia.telegramBot.model.entity.tasks.GeneratedTask;
import aglaia.telegramBot.model.entity.tasks.KangTask;
import aglaia.telegramBot.model.entity.tasks.TypesOfTasks;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class UserBot {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private Long chatId;
    private String name;
    int age;
    @ManyToOne(cascade = CascadeType.ALL)
    private KangTask actualKangTask;
    private boolean actualKangTaskDone;

    //todo здесь надо прописать логику чтобы тип таска менялся вместе с назначением нового актуального таска

    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "generated_task_id")
    private GeneratedTask actualGeneratedTask;

    // должен хранить последний активный таск - любой. Теперь хранит тип актуального таска
    private TypesOfTasks typeOfActualTask;



//    private int numberOfAttempts4ActualTask;
//    @Transient
//    ArrayList<KangTask> listOfDoneTasks;
//
//    int scoreOfDoneKangTasks;
//
    int countOfGeneratedTasks;
//
//    int rating = scoreOfDoneKangTasks + countOfGeneratedTasks;

    public UserBot(Long chatId) {
        this.chatId = chatId;
    }

    public AbstractTask getActualTask() {
        if (typeOfActualTask == TypesOfTasks.KANG) return actualKangTask;
        else if (typeOfActualTask == TypesOfTasks.GENERATED) return  actualGeneratedTask;
        else if (typeOfActualTask == TypesOfTasks.NOTASK) return null;
        else return actualKangTask;
    }

    public void setActualTask(AbstractTask actualTask) {
        if (actualTask == null){
            typeOfActualTask = TypesOfTasks.NOTASK;
            this.actualGeneratedTask = null;
        }
        else if(actualTask instanceof KangTask) {
            this.actualKangTask = (KangTask) actualTask;
            typeOfActualTask = TypesOfTasks.KANG;
        }
        else if (actualTask instanceof GeneratedTask) {
//            countOfGeneratedTasks = 0;
            this.actualGeneratedTask = (GeneratedTask) actualTask;
            typeOfActualTask = TypesOfTasks.GENERATED;
        }
    }

    public void addOneDoneGeneratedTask(){
        countOfGeneratedTasks++;
    }

//    public void addKangTaskToListOfDone(KangTask kangTask, boolean isTaskDoneRight) {
//
//        listOfDoneTasks.add(kangTask);
//        if (isTaskDoneRight) {
//            int scoreFor4 = kangTask.getScoreFor4();
//            int scoreFor5 = kangTask.getScoreFor5();
//            switch (age) {
//                case 6:
//                case 7:
//                case 8:
//                    scoreOfDoneKangTasks += scoreFor4;
//                    break;
//                case 9:
//                    int i = (scoreFor4 + scoreFor5) / 2;
//                    scoreOfDoneKangTasks += i;
//                    break;
//                case 10:
//                case 11:
//                    scoreOfDoneKangTasks += scoreFor5;
//                    break;
//                case 12:
//                case 13:
//                case 14:
//                case 15:
//                case 16:
//                case 17:
//                    i = scoreFor5 - (scoreFor5 / 4);
//                    scoreOfDoneKangTasks += i;
//                    break;
//                default:
//                    i = scoreFor5 / 2;
//                    scoreOfDoneKangTasks += i;
//            }
//
//        }
//    }
//
//    public void oneMoreDoneGeneratedTaskDone() {
//        if (countOfGeneratedTasks > 0) scoreOfDoneKangTasks = -4;
//        if (countOfGeneratedTasks > 1) scoreOfDoneKangTasks = -5;
//        if (countOfGeneratedTasks > 2) scoreOfDoneKangTasks = -9;
//        countOfGeneratedTasks++;
//    }
//
//    public boolean isUserHaveMoreAttentionForThisTask() {
//        numberOfAttempts4ActualTask++;
//        if (numberOfAttempts4ActualTask < 3) return true;
//        return false;
//    }
//
//    public boolean isThisKangTaskDoneByThisUser (KangTask kangTask){
//        return listOfDoneTasks.contains(kangTask);
//    }
}
