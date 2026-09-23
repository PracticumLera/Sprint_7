package data;

import model.Order;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

public class OrderData {

    private static final Random RANDOM = new Random();

    private static String generateFirstName() {
        return "Customer_" + RANDOM.nextInt(1000);
    }

    private static String generateLastName() {
        return "Lastname_" + RANDOM.nextInt(1000);
    }

    private static String generateAddress() {
        return "Address_" + RANDOM.nextInt(1000);
    }

    private static String generatePhone() {
        return "+7" + (1000000000 + RANDOM.nextInt(90000000));
    }

    private static String generateComment() {
        return "Comment_" + RANDOM.nextInt(1000);
    }

    private static String generateDeliveryDate() {
        return LocalDate.now().plusDays(RANDOM.nextInt(30) + 1).toString();
    }

    private static int generateMetroStation() {
        return RANDOM.nextInt(10) + 1;
    }

    private static int generateRentTime() {
        return RANDOM.nextInt(10) + 1;
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