package org.hibernate.tutorial.annotations;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by geekslife on 2017. 1. 11..
 */
@Entity
@Table(name="EVENTS")
public class Event {
    private long id;
    private String title;
    private Date date;

    public Event() {}
    public Event(String title, Date date) {
        this.title = title;
        this.date = date;
    }

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name="increment", strategy = "increment")
    public Long getId() { return id; }
    public void setId( long id ) { this.id = id; }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="EVENT_DATE")
    public Date getDate() { return date; }
    public void setDate( Date date ) { this.date = date; }

    public String getTitle() { return title; }
    public void setTitle( String title ) { this.title = title; }
}

