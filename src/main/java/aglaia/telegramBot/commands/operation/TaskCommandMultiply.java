package aglaia.telegramBot.commands.operation;

import aglaia.telegramBot.service.operation.MultiplyCommandService;
import org.springframework.stereotype.Component;

@Component
public class TaskCommandMultiply extends AbstractTaskCommand {

    public TaskCommandMultiply(MultiplyCommandService multiplyCommandService) {
        super("multiply", "Задания на умножение", multiplyCommandService);
    }

}
