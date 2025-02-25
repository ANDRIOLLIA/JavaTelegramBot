import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

public class ButtonsAndMarkupsForDota extends Bot {
    // игры
    public InlineKeyboardButton dotaSkinsButton = InlineKeyboardButton.builder()
            .text("Скины Dota2")
            .callbackData("dota_skins")
            .build();

    public InlineKeyboardButton csSkinsButton = InlineKeyboardButton.builder()
            .text("Скины CS2")
            .callbackData("cs_skins")
            .build();

    private InlineKeyboardMarkup sendGames = InlineKeyboardMarkup.builder()
            .keyboardRow(List.of(csSkinsButton))
            .keyboardRow(List.of(dotaSkinsButton))
            .build();

    // торговые площадки для доты
    private InlineKeyboardButton lisSkinsDotaButton = InlineKeyboardButton.builder()
            .text("Lis-skins")
            .callbackData("lis_skins")
            .build();

    private InlineKeyboardButton avanMarketDotaButton = InlineKeyboardButton.builder()
            .text("Avan Market")
            .callbackData("avan_market")
            .build();

    private InlineKeyboardButton marketDotaButton = InlineKeyboardButton.builder()
            .text("Market Dota2")
            .callbackData("dota_market")
            .build();

    private InlineKeyboardMarkup sendMarketsForDota = InlineKeyboardMarkup.builder()
            .keyboardRow(List.of(lisSkinsDotaButton))
            .keyboardRow(List.of(avanMarketDotaButton))
            .keyboardRow(List.of(marketDotaButton))
            .build();

    // торговые площадки для кс
    private InlineKeyboardButton lisSkinsCSButton = InlineKeyboardButton.builder()
            .text("Lis-skins")
            .callbackData("lis_skins")
            .build();

    private InlineKeyboardButton avanMarketCSButton = InlineKeyboardButton.builder()
            .text("Avan Market")
            .callbackData("avan_market")
            .build();

    private InlineKeyboardButton marketCSButton = InlineKeyboardButton.builder()
            .text("Market Dota2")
            .callbackData("dota_market")
            .build();

    private InlineKeyboardMarkup sendMarketsForCS = InlineKeyboardMarkup.builder()
            .keyboardRow(List.of(lisSkinsCSButton))
            .keyboardRow(List.of(avanMarketCSButton))
            .keyboardRow(List.of(marketCSButton))
            .build();

    public InlineKeyboardMarkup getSendGames() {
        return sendGames;
    }

    public InlineKeyboardMarkup getSendMarketsForDota() {
        return sendMarketsForDota;
    }

    public InlineKeyboardMarkup getSendMarketsForCS(){
        return sendMarketsForCS;
    }
}
