package ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
        val balanceFirstCard = (dashboardPage.getCardBalance(DataHelper.getFirstCard().getDataTestId()));
        val balanceSecondCard = (dashboardPage.getCardBalance(DataHelper.getSecondCard().getDataTestId()));
        val moneyTransferPage = dashboardPage.pressTopUpByCardId(DataHelper.getFirstCard().getDataTestId());

        moneyTransferPage.transferMoney(transferAmount, DataHelper.getSecondCard().getNumber());

        val expectedBalanceFirstCard = (dashboardPage.getCardBalance(DataHelper.getFirstCard().getDataTestId()));
        val actualBalanceFirstCard = balanceFirstCard + Integer.valueOf(transferAmount);
        assertEquals(actualBalanceFirstCard, expectedBalanceFirstCard);

        val expectedBalanceSecondCard = (dashboardPage.getCardBalance(DataHelper.getSecondCard().getDataTestId()));
        val actualBalanceSecondCard = balanceSecondCard - Integer.valueOf(transferAmount);
        assertEquals(actualBalanceSecondCard, expectedBalanceSecondCard);

    }
}
