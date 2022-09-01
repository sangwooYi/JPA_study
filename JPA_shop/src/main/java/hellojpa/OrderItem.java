package hellojpa;

import javax.persistence.*;

@Entity
public class OrderItem {

    @Id
    @GeneratedValue
    @Column(name = "ORDER_ITEM_ID")
    private Long id;

    @Column
    private Integer orderPrice;

    @Column
    private Integer count;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    Order order = new Order();

    @ManyToOne
    @JoinColumn(name = "ITEM_ID")
    Item item = new Item();

    public Integer getOrderPrice() {
        return orderPrice;
    }

    public Integer getCount() {
        return count;
    }

    public Order getOrder() {
        return order;
    }

    public Item getItem() {
        return item;
    }

    public void setOrderPrice(Integer orderPrice) {
        this.orderPrice = orderPrice;
    }

    public void setCount(Integer count) {
        this.count = count;
    }


    public void setOrder(Order order) {
        this.order = order;
        if (!order.getOrderItems().contains(this)) {
            order.getOrderItems().add(this);
        }
    }

    public void setItem(Item item) {
        this.item = item;
        // 순환참조 방지
        if (!item.getOrderItems().contains(this)) {
            item.getOrderItems().add(this);
        }
    }

    public OrderItem() {

    }
}
