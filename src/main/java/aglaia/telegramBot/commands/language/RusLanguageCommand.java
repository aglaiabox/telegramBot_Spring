package aglaia.telegramBot.commands.language;

import aglaia.telegramBot.service.language.LanguageServiceRus;
import org.springframework.stereotype.Component;

@Component
public class RusLanguageCommand extends AbstractLanguageCommand{
    RusLanguageCommand(LanguageServiceRus languageServiceRus) {
        super("rus", "меняем язык на русский", languageServiceRus);
    }
}
