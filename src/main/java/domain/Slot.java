package domain;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Slot {

    private Long id;

    private Date date = new Date();

    private Teacher teacher;

    private Customer customer;

    public Slot(Teacher teacher){
        this.teacher = teacher;
        teacher.getSlots().add(this);
        teacher.setSlots(teacher.getSlots());
    }
    public Slot(){}

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    protected void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    @ManyToOne(cascade = CascadeType.PERSIST)
    public Teacher getTeacher() {
        return teacher;
    }

    @ManyToOne(cascade = CascadeType.PERSIST)
    public Customer getCustomer() {
        return customer;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

}
