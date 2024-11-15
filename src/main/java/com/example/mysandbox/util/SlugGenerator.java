package com.example.mysandbox.util;

import org.springframework.stereotype.Component;

import java.text.Normalizer;
import java.util.Locale;
import java.util.regex.Pattern;

@Component
public class SlugGenerator {
    private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");


    public String generateSlug(String input) {
        if (input == null) {
            return "";
        }

        String nowhitespace = WHITESPACE.matcher(input).replaceAll("-");

        String normalized = Normalizer.normalize(nowhitespace, Normalizer.Form.NFD);

        String slug = NONLATIN.matcher(normalized).replaceAll("");

        slug = slug.toLowerCase(Locale.ENGLISH);

        slug = slug.replaceAll("-+", "-");

        slug = slug.replaceAll("^-|-$", "");

        return slug;
    }

    public String generateUniqueSlug(String input, boolean exists) {
        String slug = generateSlug(input);

        if (exists) {
            String randomStr = generateRandomString(6);
            slug = slug + "-" + randomStr;
        }

        return slug;
    }

    private String generateRandomString(int length) {
        String chars = "abcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder random = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = (int) (Math.random() * chars.length());
            random.append(chars.charAt(index));
        }

        return random.toString();
    }
}
