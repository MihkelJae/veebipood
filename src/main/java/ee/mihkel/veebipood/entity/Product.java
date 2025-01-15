package ee.mihkel.veebipood.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Getter
//@Setter
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
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


}
