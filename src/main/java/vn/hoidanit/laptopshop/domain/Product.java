package vn.hoidanit.laptopshop.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @NotEmpty(message = "Tên sản phẩm không được để trống")
    private String name;

    @NotNull
    @DecimalMin(value = "0", inclusive = false, message = "Giá phải lớn hơn 0")
    private double price;
    private String image;

    @NotNull
    @NotEmpty(message = "detailDesc không được để trống")
    private String detailDesc;

    @NotNull
    @NotEmpty(message = "sortDesc không được để trống" )
    private String shortDesc;

    @NotNull
    @Min(value = 1, message = "Số lượng cần lớn hơn hoặc bằng 1")
    private long quantity;
    private long sold;
    private String factory;
    private String target;

    public @NotNull @NotEmpty(message = "sortDesc không được để trống") String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(@NotNull @NotEmpty(message = "sortDesc không được để trống") String shortDesc) {
        this.shortDesc = shortDesc;
    }

    @OneToMany(mappedBy = "product")
    private Set<OderDetail> oderDetails;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public @NotNull @NotEmpty(message = "Tên sản phẩm không được để trống") String getName() {
        return name;
    }

    public void setName(@NotNull @NotEmpty(message = "Tên sản phẩm không được để trống") String name) {
        this.name = name;
    }

    @NotNull
    @DecimalMin(value = "0", inclusive = false, message = "Giá phải lớn hơn 0")
    public double getPrice() {
        return price;
    }

    public void setPrice(@NotNull @DecimalMin(value = "0", inclusive = false, message = "Giá phải lớn hơn 0") double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public @NotNull @NotEmpty(message = "detailDesc không được để trống") String getDetailDesc() {
        return detailDesc;
    }

    public void setDetailDesc(@NotNull @NotEmpty(message = "detailDesc không được để trống") String detailDesc) {
        this.detailDesc = detailDesc;
    }

    public @NotNull @NotEmpty(message = "sortDesc không được để trống") String getSortDesc() {
        return shortDesc;
    }

    public void setSortDesc(@NotNull @NotEmpty(message = "sortDesc không được để trống") String sortDesc) {
        this.shortDesc = sortDesc;
    }

    @NotNull
    @Min(value = 1, message = "Số lượng cần lớn hơn hoặc bằng 1")
    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(@NotNull @Min(value = 1, message = "Số lượng cần lớn hơn hoặc bằng 1") long quantity) {
        this.quantity = quantity;
    }

    public long getSold() {
        return sold;
    }

    public void setSold(long sold) {
        this.sold = sold;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Set<OderDetail> getOderDetails() {
        return oderDetails;
    }

    public void setOderDetails(Set<OderDetail> oderDetails) {
        this.oderDetails = oderDetails;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", image='" + image + '\'' +
                ", detailDesc='" + detailDesc + '\'' +
                ", sortDesc='" + shortDesc + '\'' +
                ", quantity=" + quantity +
                ", sold=" + sold +
                ", factory='" + factory + '\'' +
                ", target='" + target + '\'' +
                '}';
    }
}
