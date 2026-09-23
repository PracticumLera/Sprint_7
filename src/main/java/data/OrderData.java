package data;

import model.Order;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class OrderData {

    private static final String CUSTOMER_NAME = "customerName_";
    private static final String LAST_NAME = "lastname_";
    private static final String ADDRESS = "address_";
    private static final String PHONE = "+7";
    private static final String COMMENT = "comment_";
    private static final Random RANDOM = new Random();

    private static String generateFirstName() {
        return CUSTOMER_NAME + System.currentTimeMillis();
    }

    private static String generateLastName() {
        return LAST_NAME + System.currentTimeMillis();
    }

    private static String generateAddress() {
        return ADDRESS + System.currentTimeMillis();
    }

    private static String generatePhone() {
        return PHONE + System.currentTimeMillis();
    }

    private static String generateComment() {
        return COMMENT + System.currentTimeMillis();
    }

    private static String generateDeliveryDate() {
        LocalDate date = LocalDate.now().plusDays(RANDOM.nextInt(30) + 1);
        return date.toString();
    }

    private static int generateMetroStation() {
        return (int) (System.currentTimeMillis() % 10) + 1;
    }

    private static int generateRentTime() {
        return (int) (System.currentTimeMillis() % 10) + 1;
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

    public static Order getOrderWithBlackColor() {
        return getOrderWithColor(Arrays.asList("BLACK"));
    }

    public static Order getOrderWithGreyColor() {
        return getOrderWithColor(Arrays.asList("GREY"));
    }

    public static Order getOrderWithBothColors() {
        return getOrderWithColor(Arrays.asList("BLACK", "GREY"));
    }

    public static Order getOrderWithoutColor() {
        return getOrderWithColor(null);
    }
}
