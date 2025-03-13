package com.accounts.accounts.config;

import java.util.concurrent.ThreadLocalRandom;

public class AccountUtils {
    public static String generateAccountNumber() {
        // Get the last 6 digits of the current timestamp
        String timestampPart = String.valueOf(System.currentTimeMillis()).substring(7);

        // Generate a 6-digit random number
        int randomPart = ThreadLocalRandom.current().nextInt(100000, 999999);

        // Concatenate both parts to form a 12-digit account number
        return timestampPart + randomPart;
    }

    public static void main(String[] args) {
        // Test the account number generation
        System.out.println("Generated Account Number: " + generateAccountNumber());
    }
}

