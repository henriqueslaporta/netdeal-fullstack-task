package com.netdeal.fullstacktask.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordEncryptionTest {
        @Test
        public void test_encode_password() {
            String password = "password123";
            String encodedPassword = PasswordEncryption.encode(password);
            assertNotNull(encodedPassword);
            assertNotEquals(password, encodedPassword);
        }

        @Test
        public void test_check_password() {
            String password = "password123";
            String encodedPassword = PasswordEncryption.encode(password);
            assertTrue(PasswordEncryption.checkPassword(password, encodedPassword));
        }

        @Test
        public void test_incorrect_password() {
            String password = "password123";
            String encodedPassword = PasswordEncryption.encode(password);
            assertFalse(PasswordEncryption.checkPassword("incorrectPassword", encodedPassword));
        }

        @Test
        public void test_incorrect_password_hash() {
            String password = "password123";
            assertThrows(IllegalArgumentException.class, () -> PasswordEncryption.checkPassword(password, "incorrectPasswordHash"));
        }

}