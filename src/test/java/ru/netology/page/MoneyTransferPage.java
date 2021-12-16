package ru.netology.page;


import static com.codeborne.selenide.Selenide.$;

public class MoneyTransferPage {

    public void transferMoney(String amount, String cardFrom){
       $("[data-test-id=amount] input").setValue(amount);
       $("[data-test-id=from] input").setValue(cardFrom);
       $("[data-test-id=action-transfer]").click();

   }
}
