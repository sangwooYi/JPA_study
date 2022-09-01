package hellojpa;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Item {

    @Id
    @GeneratedValue
    @Column(name = "ITEM_ID")
    private Long id;

    @Column
    private String name;
    @Column
    private Integer price;

    @Column
    private Integer stockQuantity;

    @OneToMany(mappedBy = "item")
    private List<OrdersItem> ordersItems = new ArrayList<>();

    public Item() {

    }

    public Item(String name, Integer price, Integer stockQuantity) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public List<OrdersItem> getOrderItems() {
        return ordersItems;
    }

    public void addOrderItem(OrdersItem ordersItem) {
        this.ordersItems.add(ordersItem);

        if (ordersItem.getItem() != this) {
            ordersItem.setItem(this);
        }
    }

}

