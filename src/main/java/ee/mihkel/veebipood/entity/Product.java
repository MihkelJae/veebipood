package ee.mihkel.veebipood.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price;
    private boolean active;
    private String image;
    // parempoolne tähenda kas on üks või List
//    @OneToOne     üks     isik + kontaktandmed    toode + koostisosad
//    @OneToMany    List
//    @ManyToOne    üks     toode + kategooria
//    @ManyToMany   List
    //vasakpoolne, kas saab kasutada ka kellelgi teisel isik + andmed
    @ManyToOne
    private Category category;//kümnevõistluse, tulemuse külge sportlane @ManyToOne


    public Product() {
    }

    public Product(Long id, String name, double price, boolean active, String image, Category category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.active = active;
        this.image = image;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
