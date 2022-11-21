package aglaia.telegramBot.commands.operation;

import aglaia.telegramBot.database.Database;
import aglaia.telegramBot.service.operation.AbstractCommandService;
import aglaia.telegramBot.service.operation.CommandDivideService;
import org.springframework.stereotype.Component;

@Component
public class CommandDivide extends AbstractCommand {
    public CommandDivide(CommandDivideService commandDivideService) {
        super("division", "Задания на деление", commandDivideService);
    }

}