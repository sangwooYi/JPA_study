package hellojpa;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Orders {

    @Id
    @GeneratedValue
    @Column(name = "ORDERS_ID")
    private Long id;

    @Column
    private LocalDateTime orderState;
    @Column
    @Enumerated(EnumType.STRING)
    private DStatus status;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToOne
    @JoinColumn(name = "DELIVERY_ID")
    private Delivery delivery;

    @OneToMany(mappedBy = "orders")
    private List<OrdersItem> ordersItems = new ArrayList<>();

    public Orders() {
    }

    public LocalDateTime getOrderState() {
        return orderState;
    }

    public DStatus getStatus() {
        return status;
    }

    public Member getMember() {
        return member;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setOrderState(LocalDateTime orderState) {
        this.orderState = orderState;
    }

    public void setStatus(DStatus status) {
        this.status = status;
    }

    public List<OrdersItem> getOrdersItems() {
        return ordersItems;
    }

    // 양방향 매핑에서도 이부분은 한쪽에만 선언해주면됨
    // 이쪽에 setMember를 선언해주던, 반대쪽에 addOrders를 선언해주면 됨
    public void setMember(Member member) {
        this.member = member;
        // 순환참조 방지
        if (!member.getOrders().contains(this)) {
            member.getOrders().add(this);
        }
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        if (delivery.getOrder() != this) {
            delivery.setOrder(this);
        }
    }
    public void addOrderItems(OrdersItem ordersItem) {
        this.ordersItems.add(ordersItem);

        if (ordersItem.getOrders() != this) {
            ordersItem.setOrders(this);
        }
    }

}
