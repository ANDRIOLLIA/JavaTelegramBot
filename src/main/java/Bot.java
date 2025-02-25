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

    public InlineKeyboardButton sendForStartTest = InlineKeyboardButton.builder()
            .text("Send for start test")
            .callbackData("start")
            .build();

    public InlineKeyboardButton thereIsNoDifference = InlineKeyboardButton.builder()
            .text("Нет разницы")
            .callbackData("нет разницы")
            .build();

    public InlineKeyboardButton typeUsingData = InlineKeyboardButton.builder()
            .text("Разница в типе используемых данных")
            .callbackData("разница в типе используемых данных")
            .build();

    public InlineKeyboardButton createCopy = InlineKeyboardButton.builder().
            text("Разница в создании копий")
            .callbackData("разница в создании копий")
            .build();

    public InlineKeyboardButton classOrPrimitive = InlineKeyboardButton.builder()
            .text("Один класс, а другой примитив")
            .callbackData("один класс, а другой примитив")
            .build();

    private InlineKeyboardMarkup keyboardM1 = InlineKeyboardMarkup.builder()
            .keyboardRow(List.of(sendForStartTest)).build();

    private InlineKeyboardMarkup sendQuestionOne = InlineKeyboardMarkup.builder()
            .keyboardRow(List.of(sendForStartTest))
            .keyboardRow(List.of(thereIsNoDifference))
            .keyboardRow(List.of(typeUsingData))
            .keyboardRow(List.of(createCopy))
            .build();


    @Override
    public void onUpdateReceived(Update update) {
        buttonTab(update);
        isCommand(update.getMessage());
    }

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



            if (data.equals("start")) {
                editMessageText.setText("Какая разница между классами String и StringBuilder?");
                editMessageReplyMarkup.setReplyMarkup(sendQuestionOne);
            } else if (data.equals("нет разницы")) {
                editMessageText.setText("Выберите только примитивы");
            }

            AnswerCallbackQuery answerCallbackQuery = AnswerCallbackQuery.builder()
                    .callbackQueryId(queryId)
                    .build();

            try {
                execute(answerCallbackQuery);
                execute(editMessageReplyMarkup);
            }catch (Exception ex){
                ex.getMessage();
            }

        }
    }

    public void isCommand(Message message) {
        String text = message.getText();
        if (text.equals("/start_test")) {
            sendMenu(message.getFrom().getId(), "<b>Go to the start test</b>", keyboardM1);
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