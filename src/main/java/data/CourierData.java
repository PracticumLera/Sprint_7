package data;

import model.Courier;

import java.util.Random;
import java.util.UUID;

public class CourierData {

    private static final Random RANDOM = new Random();
    private static final int LOGIN_UUID_LENGTH = 8;
    private static final int PASSWORD_UUID_LENGTH = 6;
    private static final int MAX_NAME_SUFFIX = 1000;

    private static String generateLogin() {
        return "courier_" + UUID.randomUUID().toString().substring(0, LOGIN_UUID_LENGTH);
    }

    private static String generatePassword() {
        return "pass_" + UUID.randomUUID().toString().substring(0, PASSWORD_UUID_LENGTH);
    }

    private static String generateFirstName() {
        return "Name_" + RANDOM.nextInt(MAX_NAME_SUFFIX);
    }

    public static Courier getRandomCourier() {
        return new Courier(generateLogin(), generatePassword(), generateFirstName());
    }

    public static Courier getCourierWithoutLogin() {
        return Courier.builder()
                .password(generatePassword())
                .firstName(generateFirstName())
                .build();
    }

    public static Courier getCourierWithoutPassword() {
        return Courier.builder()
                .login(generateLogin())
                .firstName(generateFirstName())
                .build();
    }
}