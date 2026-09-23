package data;

import model.Courier;

import java.util.Random;
import java.util.UUID;

public class CourierData {

    private static final Random RANDOM = new Random();

    private static String generateLogin() {
        return "courier_" + UUID.randomUUID().toString().substring(0, 8);
    }

    private static String generatePassword() {
        return "pass_" + UUID.randomUUID().toString().substring(0, 6);
    }

    private static String generateFirstName() {
        return "Name_" + RANDOM.nextInt(1000);
    }

    public static Courier getRandomCourier() {
        return new Courier(generateLogin(), generatePassword(), generateFirstName());
    }

    public static Courier getCourierWithoutLogin() {
        return new Courier(null, generatePassword(), generateFirstName());
    }

    public static Courier getCourierWithoutPassword() {
        return new Courier(generateLogin(), null, generateFirstName());
    }
}