package hellojpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Item {

    @Id
    @GeneratedValue // AUTO가 default값
    @Column(name = "ITEM_ID")
    private Long id;

    @Column
    private String name;

    @Column
    private Integer price;

    @Column
    private Integer stockQuantity;

    @OneToMany(mappedBy = "item")
    List<OrderItem> orderItems = new ArrayList<>();

    public void Item() {

    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }


    public void addOrderItem(OrderItem orderItem) {
        this.orderItems.add(orderItem);
        // 순환참조 방지
        if (orderItem.getItem() != this) {
            orderItem.setItem(this);
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
}
