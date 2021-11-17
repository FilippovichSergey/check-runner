import card.DiscountCard;
import item.Item;
import java.util.ArrayList;
import java.util.List;
import receipt.ReceiptPrinter;

public class CheckRunner {

    public static void main(String[] args) {
        String[] args1 = {"1-1", "2-2", "3-5", "100-10", "card-102"};
        ReceiptPrinter printer = new ReceiptPrinter();
        List<DiscountCard> discountCardList = new ArrayList<>();
        for (int i = 0; i < 10_000; i++) {
            discountCardList.add(new DiscountCard(i));
        }
        double cardDiscount = 0;
        for (DiscountCard discountCard : discountCardList) {
            if (printer.findCardNumber(args1) == null) {
                break;
            }
            if (printer.findCardNumber(args1) == discountCard.getId()) {
                cardDiscount = discountCard.getDiscount();
                break;
            }
            //TODO can it be replaced like I wrote below?
        }
        List<Item> itemList = new ArrayList<>();
        for (int i = 0; i < 10_000; i++) {
            itemList.add(new Item(i, String.valueOf(i), i));
        }

        List<Item> receiptList = new ArrayList<>();
        printer.parseArgs(args1).forEach((k, v) -> {
            for (Item item : itemList) {
                if (k == item.getId()) {
                    receiptList.add(new Item(k, item.getName(), item.getPrice(), v));
                }
            }
        });

        printer.printReceipt(receiptList, cardDiscount);
    }

}
