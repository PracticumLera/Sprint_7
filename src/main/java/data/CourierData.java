package data;

import model.Courier;

public class CourierData {

    private static final String LOGIN = "login_";
    private static final String PASSWORD = "pass_";
    private static final String COURIER_NAME = "courierName_";

    private static String generateLogin() {
        return LOGIN + System.currentTimeMillis();
    }

    private static String generatePassword() {
        return PASSWORD + System.currentTimeMillis();
    }

    private static String generateFirstName() {
        return COURIER_NAME + System.currentTimeMillis();
    }

    public static Courier getRandomCourier() {
        return new Courier(generateLogin(), generatePassword(), generateFirstName());
    }

    public static Courier getCourierWithLogin(String login) {
        return new Courier(login, generatePassword(), generateFirstName());
    }

    public static Courier getCourierWithoutLogin() {
        return new Courier(null, generatePassword(), generateFirstName());
    }

    public static Courier getCourierWithoutPassword() {
        return new Courier(generateLogin(), null, generateFirstName());
    }
}
