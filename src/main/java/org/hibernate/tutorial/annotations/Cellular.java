package org.hibernate.tutorial.annotations;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by geekslife on 2017. 1. 11..
 */
@Entity
public class Cellular {
    @Id
    @GeneratedValue
    private int id;

    private int number;

    public Cellular() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getNumber() { return number; }
    public void setNumber(int number) { this.number = number; }

    public Cellular(int number) { this.number = number; }

    @Override
    public String toString() {
        return "Cellular{" +
                "id=" + id +
                ", number=" + number +
                '}';
    }
}
