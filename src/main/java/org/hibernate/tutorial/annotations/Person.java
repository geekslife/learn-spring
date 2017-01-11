package org.hibernate.tutorial.annotations;

import javafx.scene.control.Cell;

import javax.persistence.*;

/**
 * Created by geekslife on 2017. 1. 11..
 */
@Entity
public class Person {
    @Id
    @GeneratedValue
    private int id;

    private String name;
    public void setName( String name ) { this.name = name; }
    public String getName() { return name; }

    @OneToOne
    @JoinColumn(name="cellular_id")
    private Cellular cellular;

    public Cellular getCellular() { return cellular; }
    public void setCellular(Cellular cellular) { this.cellular = cellular; }

    public Person() {}
    public Person(String name, Cellular cellular) {
        this.name = name;
        this.cellular = cellular;
    }
    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cellular=" + cellular +
                '}';
    }
}
