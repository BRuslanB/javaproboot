package kz.bitlab.javapro.javaproboot.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "t_cars")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="name", length = 100)
    private String name;

    @Column(name="car_model", columnDefinition = "TEXT")
    private String model;

    @Column(name="price")
    private int price;

    @Column(name="engine_volume")
    private double engineVolume;

    @ManyToOne(fetch = FetchType.EAGER)
    private Country country;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Category> categories;
}