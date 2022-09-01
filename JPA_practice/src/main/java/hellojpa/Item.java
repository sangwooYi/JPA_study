package hellojpa;

<<<<<<< HEAD

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
=======
import javax.persistence.*;

@Entity
// Inheritance 의 디폴트 전략은 SINGLE_TABLE이다! 주의!
@Inheritance(strategy = InheritanceType.JOINED)
>>>>>>> c387d3e1c84ebe2a6951de9d71b309b7e1f403ec
public class Item {

    @Id
    @GeneratedValue
<<<<<<< HEAD
    @Column(name = "ITEM_ID")
=======
>>>>>>> c387d3e1c84ebe2a6951de9d71b309b7e1f403ec
    private Long id;

    @Column
    private String name;
    @Column
    private Integer price;
<<<<<<< HEAD

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
=======
    @Column
    private Integer stockQuantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
>>>>>>> c387d3e1c84ebe2a6951de9d71b309b7e1f403ec
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
<<<<<<< HEAD

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

=======
}
>>>>>>> c387d3e1c84ebe2a6951de9d71b309b7e1f403ec
