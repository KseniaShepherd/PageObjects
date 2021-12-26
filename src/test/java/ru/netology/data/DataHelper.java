package ru.netology.data;

import lombok.Value;


public class DataHelper {

    private static final Card FIRST_CARD = new Card("92df3f1c-a033-48e6-8390-206f6b1f56c0", "5559 0000 0000 0001");
    private static final Card SECOND_CARD = new Card("0f3f5c2a-249e-4c3d-8287-09f7a039391d", "5559 0000 0000 0002");

    private DataHelper() {
    }

    public static Card getFirstCard() {
        return FIRST_CARD;
    }

    public static Card getSecondCard() {
        return SECOND_CARD;
    }

    @Value
    public static class Credential {
        private String login;
        private String password;
    }

    public static Credential getValidCredential() {
        return new Credential("vasya", "qwerty123");
    }

    @Value
    public static class VerificationCode {
        private String code;

    }

    public static VerificationCode getVerificationCode() {
        return new VerificationCode("12345");
    }

    @Value
    public static class Card {
        private String dataTestId;
        private String number;

    }

}

