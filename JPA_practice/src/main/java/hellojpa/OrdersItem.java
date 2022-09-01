package hellojpa;

import javax.persistence.*;

@Entity
public class OrdersItem {

    @Id
    @GeneratedValue
    @Column(name = "ORDERS_ITEM_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ORDERS_ID")
    private Orders orders;

    @ManyToOne
    @JoinColumn(name = "ITEM_ID")
    private Item item;

    public OrdersItem() {

    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
