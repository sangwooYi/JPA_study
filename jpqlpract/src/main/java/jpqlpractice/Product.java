package jpqlpractice;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product extends BaseEntity {

    private String productName;
    private Integer price;
    private Integer stockAmount;

    @OneToMany(mappedBy = "product")
    private List<OrderInfo> orderInfos = new ArrayList<>();

    public Product() {

    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getStockAmount() {
        return stockAmount;
    }

    public void setStockAmount(Integer stockAmount) {
        this.stockAmount = stockAmount;
    }

    public List<OrderInfo> getOrderInfos() {
        return orderInfos;
    }
}
