package hellojpa;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Order {

    @Id
    @GeneratedValue // AUTO가 default값
    @Column(name = "ORDER_ID")
    private Long id;
    @Column
    private LocalDateTime orderDate;
    @Enumerated(EnumType.STRING)
    private Status status;

    // OrderItem과 1:다 관계, 다쪽이 주인임
    @OneToMany(mappedBy = "order")  // ManyToOne에 설정한 변수명
    List<OrderItem> orderItems = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    Member member = new Member();

    public Order() {

    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public Status getStatus() {
        return status;
    }

    public Member getMember() {
        return member;
    }

    public void addOrderItems(OrderItem orderItem) {
        this.orderItems.add(orderItem);
        if (orderItem.getOrder() != this) {
            orderItem.setOrder(this);
        }
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setMember(Member member) {
        if (!member.getOrders().contains(this)) {
            member.getOrders().add(this);
        }
    }
}
