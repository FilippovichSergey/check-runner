package item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Item {

    private int id;
    private String name;
    private double price;
    private int quantity;

    public Item(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

}
