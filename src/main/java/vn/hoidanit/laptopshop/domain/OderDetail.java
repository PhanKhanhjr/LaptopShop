package vn.hoidanit.laptopshop.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "oder_detail")
public class OderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int quantity;
    private double price;

    @ManyToOne
    @JoinColumn(name = "oder_id")
    private Oder oder;


    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }


}
