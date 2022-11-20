package aglaia.telegramBot.model;

// TODO тут должна храниться инфа об актуальной задаче каждого типа. + ,возможно, статистика
public class UserBot {
    String name;
    Long chatId;
    int IndexOfCurrentKangTask;
    // должен хранить последний активный таск - любой
    AbstractTask actualTask;

    public UserBot(Long chatId) {
        this.chatId = chatId;
        IndexOfCurrentKangTask = 1;

    }

    public UserBot(Long chatId, String name) {
        this.chatId = chatId;
        IndexOfCurrentKangTask = 1;
        this.name = name;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public int getIndexOfCurrentKangTask() {
        return IndexOfCurrentKangTask;
    }

    public void setIndexOfCurrentKangTask(int indexOfCurrentKangTask) {
        IndexOfCurrentKangTask = indexOfCurrentKangTask;
    }

    public AbstractTask getActualTask() {
        return actualTask;
    }

    public void setActualTask(AbstractTask actualTask) {
        this.actualTask = actualTask;
    }
}
