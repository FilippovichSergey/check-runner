package receipt;

import item.Item;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReceiptPrinter {

    public Integer findCardNumber(String[] args) {
        Integer cardNumber = null;
        final int lastIndexNumber = args.length - 1;
        final int cardLength = "card".length();
        final int minimumCardNameLength = "card-1".length();
        //TODO you can create extra variable in order to make your loop shorter
        final String lastIndexArg = args[lastIndexNumber].substring(0, cardLength);
        if (args[lastIndexNumber].length() >= minimumCardNameLength
                &&lastIndexArg.equalsIgnoreCase("card")) {
            cardNumber = Integer.parseInt(args[lastIndexNumber].substring(args[lastIndexNumber].indexOf("-") + 1));
        }
        return cardNumber;
    }

    public void printReceipt(List<Item> list, double cardDiscount) {
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm:ss");
        //TODO try to use StringBuilder, I suppose it can help you here
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
            //TODO quantityDiscount can be initialised with 0, aftert that we can remove else section
            double quantityDiscount = 0;
            if (item.getQuantity() > 5) {
                quantityDiscount = 0.1;
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

    public Map<Integer, Integer> parseArgs(String[] args) {

        //TODO try to use CommandLineParser or similar existing libraries, it can help you here...
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

}
