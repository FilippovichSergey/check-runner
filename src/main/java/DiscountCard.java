public class DiscountCard {
    private int id;
    private double discount;

    public DiscountCard(int id) {
        this.id = id;
        discount = Math.random() * 10 + 2;
    }

    public DiscountCard(int id, double discount){
        this(id);
        this.discount = discount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
