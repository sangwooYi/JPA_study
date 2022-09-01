package hellojpa;

import javax.persistence.*;

@Entity
public class Delivery {

    @Id
    @GeneratedValue
    @Column(name = "DELIVERY_ID")
    private Long id;

    @Column
    private String city;
    @Column
    private String street;
    @Column
    @Enumerated(EnumType.STRING)
    private DStatus status;

    @OneToOne(mappedBy = "delivery")
    private Orders order;

    public Delivery() {
    }

    public Delivery(String city, String street, DStatus status) {
        this.city = city;
        this.street = street;
        this.status = status;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public DStatus getStatus() {
        return status;
    }

    public void setStatus(DStatus status) {
        this.status = status;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }
}
