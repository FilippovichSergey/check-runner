import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckRunner {
    public static void main(String[] args) {
        String[] args1 = {"1-1", "2-2", "3-5", "100-10", "card-102"};
        CheckRunner checkRunner = new CheckRunner();
        List<DiscountCard> discountCardList = new ArrayList<>();
        for (int i = 0; i < 10_000; i++) {
            discountCardList.add(new DiscountCard(i));
        }
        double cardDiscount = 0;
        for (DiscountCard discountCard : discountCardList) {
            if (checkRunner.findCardNumber(args1) == null) {
                break;
            }
            if (checkRunner.findCardNumber(args1) == discountCard.getId()) {
                cardDiscount = discountCard.getDiscount();
                break;
            }
        }
        List<Item> itemList = new ArrayList<>();
        for (int i = 0; i < 10_000; i++) {
            itemList.add(new Item(i, String.valueOf(i), i));
        }

        List<Item> receiptList = new ArrayList<>();
        checkRunner.parseArgs(args1).forEach((k, v) -> {
            for (Item item : itemList) {
                if (k == item.getId()) {
                    receiptList.add(new Item(k, item.getName(), item.getPrice(), v));
                }
            }
        });

        checkRunner.printReceipt(receiptList, cardDiscount);
    }

    public Map<Integer, Integer> parseArgs(String[] args) {
        Map<Integer, Integer> map = new HashMap<>();
        int end = args.length;
        if (findCardNumber(args) != null) {
            end = args.length - 1;
        }
        for (int i = 0; i < end; i++) {
            Integer id = Integer.parseInt(args[i].substring(0, args[i].indexOf("-")));
            Integer quantity = Integer.parseInt(args[i].substring(args[i].indexOf("-") + 1));
            map.put(id, quantity);
        }
        return map;
    }

    public Integer findCardNumber(String[] args) {
        Integer cardNumber = null;
        int lastIndexNumber = args.length - 1;
        int cardLength = "card".length();
        int minimumCardNameLength = "card-1".length();
        if (args[lastIndexNumber].length() >= minimumCardNameLength
                && args[lastIndexNumber].substring(0, cardLength).equalsIgnoreCase("card")) {
            cardNumber = Integer.parseInt(args[lastIndexNumber].substring(args[lastIndexNumber].indexOf("-") + 1));
        }
        return cardNumber;
    }

    public void printReceipt(List<Item> list, double cardDiscount) {
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm:ss");
        String receipt = "CASH RECEIPT"
                .concat(System.lineSeparator())
                .concat("SUPERMARKET BULKI SERGEYA")
                .concat(System.lineSeparator())
                .concat("Mos Eisley, Tatooine, Tatoo system, Arkanis sector")
                .concat(System.lineSeparator())
                .concat("Tel: 123-456-7890")
                .concat(System.lineSeparator())
                .concat("CASHIER: #0042\t\t\t\t\tDATE: ")
                .concat(formatterDate.format(date))
                .concat(System.lineSeparator())
                .concat("\t\t\t\t\t\t\t\tTIME: ")
                .concat(formatterTime.format(date));
        System.out.println(receipt);
        System.out.println("---------------------------------------------------------");
        System.out.printf("%-15s%-20s%-10s%-10s%n", "Quantity", "Description", "Price", "Total");
        double sum = 0;
        for (Item item : list) {
            double quantityDiscount;
            if (item.getQuantity() > 5) {
                quantityDiscount = 0.1;
            } else {
                quantityDiscount = 0;
            }
            double total =
                    item.getQuantity() * item.getPrice() * (1 - quantityDiscount) * (1 - cardDiscount / 100);
            sum += total;
            System.out.printf("%-15d%-20s$%-10.2f$%-10.2f%n", item.getQuantity(), item.getName(), item.getPrice(),
                    total);
        }
        System.out.println("---------------------------------------------------------");
        System.out.printf("Total\t\t\t\t\t\t\t\t\t\t$%.2f", sum);
    }
}
