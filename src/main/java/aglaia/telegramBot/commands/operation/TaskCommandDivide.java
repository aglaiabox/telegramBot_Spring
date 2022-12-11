package aglaia.telegramBot.commands.operation;

import aglaia.telegramBot.service.operation.DivideCommandServiceAbstract;
import org.springframework.stereotype.Component;

@Component
public class TaskCommandDivide extends AbstractTaskCommand {
    public TaskCommandDivide(DivideCommandServiceAbstract divideCommandService) {
        super("division", "Задания на деление", divideCommandService);
    }

}