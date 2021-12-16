package ru.netology.test;

import com.codeborne.selenide.Condition;
import lombok.val;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.LoginPage;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class MoneyTransferPage {
    @Test
    public void shouldTransferMoneyBetweenFirstAndSecondCard() {
        val transferAmount = "100";

        open("http://0.0.0.0:9999");
        val loginPage = new LoginPage();
        val validCredential = DataHelper.getValidCredential();
        val verificationPage = loginPage.validLogin(validCredential);

        val verificationCode = DataHelper.getVerificationCode();
        val dashboardPage = verificationPage.enterVerificationCode(verificationCode);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        val balanceFirstCard = (dashboardPage.getCardBalance(DataHelper.FIRST_CARD.getDataTestId()));
        val balanceSecondCard = (dashboardPage.getCardBalance(DataHelper.SECOND_CARD.getDataTestId()));
        val moneyTransferPage = dashboardPage.pressTopUpByCardId(DataHelper.FIRST_CARD.getDataTestId());

        moneyTransferPage.transferMoney(transferAmount, DataHelper.SECOND_CARD.getNumber());

        val balanceFirstCardForTest = balanceFirstCard + Integer.valueOf(transferAmount);
        $("[data-test-id='" + DataHelper.FIRST_CARD.getDataTestId() + "']")
                .shouldHave(Condition.exactText("**** **** **** 0001, баланс: " + balanceFirstCardForTest + " р.\n Пополнить"), Duration.ofSeconds(10));
        val balanceSecondCardForTest = balanceSecondCard - Integer.valueOf(transferAmount);
        $("[data-test-id='" + DataHelper.SECOND_CARD.getDataTestId() + "']")
                .shouldHave(Condition.text("**** **** **** 0002, баланс: " + balanceSecondCardForTest + " р.\n Пополнить"), Duration.ofSeconds(10));
    }
}
