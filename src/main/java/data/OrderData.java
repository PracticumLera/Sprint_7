package data;

import model.Order;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

public class OrderData {

    private static final Random RANDOM = new Random();
    private static final int MAX_NAME_SUFFIX = 1000;
    private static final int MAX_METRO_STATION = 10;
    private static final int MAX_RENT_TIME = 10;
    private static final int MAX_DELIVERY_DAYS = 30;
    private static final int MIN_DELIVERY_DAYS = 1;

    private static String generateFirstName() {
        return "Customer_" + RANDOM.nextInt(MAX_NAME_SUFFIX);
    }

    private static String generateLastName() {
        return "Lastname_" + RANDOM.nextInt(MAX_NAME_SUFFIX);
    }

    private static String generateAddress() {
        return "Address_" + RANDOM.nextInt(MAX_NAME_SUFFIX);
    }

    private static String generatePhone() {
        return "+7" + (1000000000 + RANDOM.nextInt(90000000));
    }

    private static String generateComment() {
        return "Comment_" + RANDOM.nextInt(MAX_NAME_SUFFIX);
    }

    private static String generateDeliveryDate() {
        int days = RANDOM.nextInt(MAX_DELIVERY_DAYS) + MIN_DELIVERY_DAYS;
        return LocalDate.now().plusDays(days).toString();
    }

    private static int generateMetroStation() {
        return RANDOM.nextInt(MAX_METRO_STATION) + 1;
    }

    private static int generateRentTime() {
        return RANDOM.nextInt(MAX_RENT_TIME) + 1;
    }

    public static Order getOrderWithColor(List<String> color) {
        return new Order(
                generateFirstName(),
                generateLastName(),
                generateAddress(),
                generateMetroStation(),
                generatePhone(),
                generateRentTime(),
                generateDeliveryDate(),
                generateComment(),
                color
        );
    }
}