package ru.netology.page;

import com.codeborne.selenide.ElementsCollection;
import lombok.val;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private ElementsCollection cards = $$(".list__item");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    public DashboardPage() {
    }

    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    public int getCardBalance(String cardId) {
        val cardText = $("[data-test-id='" + cardId + "']").getText();
        return extractBalance(cardText);
    }

    public MoneyTransferPage pressTopUpByCardId(String cardId) {
        $("[data-test-id='" + cardId + "']").find("button").click();
        return new MoneyTransferPage();
    }

}


