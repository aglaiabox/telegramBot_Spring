package aglaia.telegramBot.commands.language;

import aglaia.telegramBot.service.language.LanguageServiceEng;
import org.springframework.stereotype.Component;

@Component
public class EngLanguageCommand extends AbstractLanguageCommand{
    EngLanguageCommand(LanguageServiceEng languageServiceEng) {
        super("eng", "меняем язык на английский", languageServiceEng);
    }
}
