package domain;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Teacher {
    private Long id;

    private String name;

    private List<Slot> slots = new ArrayList<Slot>();

    public Teacher(String name) {
        this.name = name;
    }

    public Teacher() {
    }
    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }
    protected void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.PERSIST)
    public List<Slot> getSlots() {
        return slots;
    }

    protected void setSlots(List<Slot> slots) { this.slots = slots; }

    @Override
    public String toString() {
        return "Employee [id=" + id + ", name=" + name + ", department="
                + slots + "]";
    }

}
