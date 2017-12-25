package util;

import java.util.Random;

public class RandomGenerator {
    public static String getRandomString() {
        String chars = "qwertyuiopasdfghjklzxcvbnm1234567890";
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        while (builder.length() < 10) {
            int index = (int) (random.nextFloat() * chars.length());
            builder.append(chars.charAt(index));
        }
        return builder.toString();
    }
}
