package aglaia.telegramBot.service.commands.operation;

import aglaia.telegramBot.service.generateTask.DivideTaskService;

public class OperationCommandDivide extends AbstractOperationCommand {
    public OperationCommandDivide(String identifier, String description) {
        super(identifier, description);
        super.abstractTaskService = new DivideTaskService();
    }

}