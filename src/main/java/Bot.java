import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.CopyMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

public class Bot extends TelegramLongPollingBot {


    @Override
    public void onUpdateReceived(Update update) {
        buttonTab(update);
        isCommand(update.getMessage());
    }

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


    public void buttonTab(Update update) {
        System.out.println("Update: " + update);
        if (update.hasCallbackQuery()) {
            String idUser = update.getCallbackQuery().getMessage().getChatId().toString();
            int idMessage = update.getCallbackQuery().getMessage().getMessageId();
            String data = update.getCallbackQuery().getData();
            String queryId = update.getCallbackQuery().getId();
            EditMessageText editMessageText = EditMessageText.builder()
                    .chatId(idUser).messageId(idMessage)
                    .text("").build();
            EditMessageReplyMarkup editMessageReplyMarkup = EditMessageReplyMarkup.builder()
                    .chatId(idUser.toString()).messageId(idMessage)
                    .build();

            switch (data) {
                case "dota_skins": {
                    editMessageText.setText("Выберите торговую площадку");
                    editMessageReplyMarkup.setReplyMarkup(sendMarketsForDota);
                }
            }

            AnswerCallbackQuery answerCallbackQuery = AnswerCallbackQuery.builder()
                    .callbackQueryId(queryId)
                    .build();

            try {
                execute(answerCallbackQuery);
                execute(editMessageReplyMarkup);
            } catch (Exception ex) {
                ex.getMessage();
            }

        }
    }

    public void isCommand(Message message) {
        String text = message.getText();
        if (text.equals("/start")) {
            sendMenu(message.getFrom().getId(), "<b>Выберите игру</b>", sendGames);
        }
    }

    public void sendMenu(Long who, String txt, InlineKeyboardMarkup km) {
        SendMessage sm = SendMessage.builder()
                .chatId(who.toString()).parseMode("HTML")
                .text(txt).replyMarkup(km)
                .build();
        try {
            execute(sm);
        } catch (TelegramApiException tae) {
            throw new RuntimeException(tae);
        }
    }

    public void sendText(Long who, String what) {
        SendMessage sm = SendMessage.builder().chatId(who.toString()).text(what).build();
        try {
            execute(sm);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    public void copyMessage(Long who, Integer msgId) {
        CopyMessage cm = CopyMessage.builder().fromChatId(who.toString()).chatId(who.toString()).messageId(msgId).build();
        try {
            execute(cm);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private void scream(Long id, Message msg) {
        if (msg.hasText()) sendText(id, msg.getText().toUpperCase());
        else copyMessage(id, msg.getMessageId());
    }

    @Override
    public String getBotUsername() {
        return "@AndreyTestTelegramBot";
    }

    @Override
    public String getBotToken() {
        return "7674116155:AAHi_bfHnnmjho00x_Df1LsU3kMNu-kdTeE";
    }
}