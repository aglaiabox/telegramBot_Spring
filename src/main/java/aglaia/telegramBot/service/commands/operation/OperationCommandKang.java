package aglaia.telegramBot.service.commands.operation;


import aglaia.telegramBot.service.generateTask.KangTaskService;

public class OperationCommandKang extends AbstractOperationCommand {

    public OperationCommandKang(String identifier, String description) {
        super(identifier, description);
        super.abstractTaskService = new KangTaskService();
    }
}
