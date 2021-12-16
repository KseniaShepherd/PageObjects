package ru.netology.page;

import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    public VerificationPage validLogin(DataHelper.Credential autoInfo) {
        $("[data-test-id=login] input").setValue(autoInfo.getLogin());
        $("[data-test-id=password] input").setValue(autoInfo.getPassword());
        $("[data-test-id=action-login]").click();
        return new VerificationPage();
    }


}
