package aglaia.telegramBot.service.commands.operation;

import aglaia.telegramBot.service.generateTask.MultiplyTaskService;

public class OperationCommandMultiply extends AbstractOperationCommand {

    public OperationCommandMultiply(String identifier, String description) {
        super(identifier, description);
        super.abstractTaskService = new MultiplyTaskService();

    }

}
