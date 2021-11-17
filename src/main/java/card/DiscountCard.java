package card;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DiscountCard {

    //TODO use lombok library in order to make your code shorter and more readable
    private int id;
    private double discount;

    public DiscountCard(int id) {
        this.id = id;
        discount = Math.random() * 10 + 2;
    }

    //TODO I suppose that this constructor is not used anywhere, can be deleted
    public DiscountCard(int id, double discount) {
        this(id);
        this.discount = discount;
    }
}
