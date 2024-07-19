package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class Bot extends TelegramLongPollingBot {
    private API apiChatGpt ;

    public Bot(){
        apiChatGpt= new API();

        //apiChatGpt.clearHistory("315379206");
        //apiChatGpt.checkBalance("315379206");
    }

    @Override
    public void onUpdateReceived(Update update) {
        try {
            String from = update.getMessage().getFrom().getFirstName();
            System.out.println("messge from : "+ from);
            String text = update.getMessage().getText();
            long chatId = update.getMessage().getChat().getId();
            SendMessage sendMessage;
            String resopnse = apiChatGpt.sendMessege("315379206",text);
            String strIsReminder=
            apiChatGpt.sendMessege
                    ("315379206","respond with yes or no  do you this messege wants you to set a reminder? "+text);

            if (strIsReminder.toLowerCase().contains("yes")){
                sendMessage = new SendMessage(String.valueOf(chatId), "reminder is set");
                String strTimeReminder= apiChatGpt.sendMessege
                        ("315379206", "resopond with only a number output the extact time in seconds to wait to remimd from this messege "+text);
                execute(sendMessage);
                System.out.println("time: "+ strTimeReminder);
                Integer timeToWait;
                try {
                    timeToWait= Integer.parseInt(strTimeReminder);
                } catch (NumberFormatException e) {
                    throw new RuntimeException(e);
                }
                String strTopicToRemind= apiChatGpt.sendMessege
                        ("315379206", "resopond with short text output the topic to remimd about from this messege "+text);
                System.out.println("starting to wait");
                Thread.sleep(timeToWait*1000);
                System.out.println("done waiting");
                sendMessage = new SendMessage(String.valueOf(chatId), "reminder is done\n"+strTopicToRemind);
                execute(sendMessage);
            }
            else {
                sendMessage = new SendMessage(String.valueOf(chatId), resopnse);
                execute(sendMessage);
            }

            Thread.sleep(1);
       } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getBotUsername() {
        return "03072024";
    }


    @Override
    public String getBotToken(){
        return "7030224392:AAEvCL6YnsBe2T1ozUKLpJhxo13Kbef4PvI";
    }

}
