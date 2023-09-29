package com.netdeal.fullstacktask.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class PasswordHandlerTest {

    @ParameterizedTest
    @ValueSource(strings = {"AbcdereefgS#1@", "Abcefg1@"})
    void test_valid_password(String password) {
        PasswordHandler passwordHandler = new PasswordHandler();
        assertTrue(passwordHandler.isValidPassword(password));
    }

    @ParameterizedTest
    @ValueSource(strings = {"ABCDEFG", "", "      ", "Abc123@", "!@#$%^&^^&@"})
    void test_invalid_password(String password) {
        PasswordHandler passwordHandler = new PasswordHandler();
        assertFalse(passwordHandler.isValidPassword(password));
    }

    @ParameterizedTest
    @CsvSource({
            "Pepino@1993,100",
            "Randon@%Quwry1234UUUabc!@#,205",
            "Inglorio19@$aaaaaa,125",
            "12345678,4",
            "asdfghjk,7",
    })
    void test_password_score(String password, int expectedScore) {
        PasswordHandler passwordHandler = new PasswordHandler();
        int actualScore = passwordHandler.getScore(password);
        assertEquals(expectedScore, actualScore);
    }


}