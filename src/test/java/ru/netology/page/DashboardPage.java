package ru.netology.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;

import java.util.Objects;

import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private ElementsCollection cards = $$(".list__item");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    public DashboardPage() {
    }

    public int getCardBalance(String cardId) {
        val card = searchById(cardId);
        return extractBalance(card.text());
    }

    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    public MoneyTransferPage pressTopUpByCardId(String cardId) {
        val card = searchById(cardId);
        MoneyTransferPage moneyTransferPage = new MoneyTransferPage();
        card.$("[data-test-id=action-deposit]").click();
        return moneyTransferPage;

    }

    public SelenideElement searchById(String cardId) {
        for (SelenideElement card : cards) {
            val currentCard = card.$("[data-test-id]");
            val currentCardId = currentCard.getAttribute("data-test-id");
            if (Objects.equals(currentCardId, cardId)) {
                return card;
            }
        }
        throw new IllegalArgumentException("карты c id " + cardId + " нет");
    }

}

