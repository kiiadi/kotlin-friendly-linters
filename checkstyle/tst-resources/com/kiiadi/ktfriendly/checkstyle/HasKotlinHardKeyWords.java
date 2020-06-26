package com.kiiadi.ktfriendly.checkstyle;

import java.util.Optional;
import java.util.stream.Stream;

public class HasInvalidIdentifiers {
    public static final String when = "blah";
    public static String in() {
        return "hello";
    }
    public static void betterHandleLambdas() {
        Stream.of("hello").forEach(str -> {
            Optional.of(str).ifPresent(value -> {
                String strValue = value;
                System.out.println(strValue);
            });
        });
    }
}