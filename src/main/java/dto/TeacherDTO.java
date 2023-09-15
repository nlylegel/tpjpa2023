package dto;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;

import java.util.ArrayList;
import java.util.List;


public class TeacherDTO {
    private Long id;

    private String name;

    private List<Long> slots = new ArrayList<Long>();

    public TeacherDTO(Long id, String name, List<Long> slots) {
        this.id = id;
        this.name = name;
        this.slots = slots;
    }
    public TeacherDTO() {
    }

    @XmlElement(name = "id")
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @XmlElement(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElementWrapper(name = "slots")
    @XmlElement(name = "slot")
    public List<Long> getSlots() {
        return slots;
    }

    protected void setSlots(List<Long> slots) { this.slots = slots; }

    @Override
    public String toString() {
        return "Employee [id=" + id + ", name=" + name + ", department="
                + slots + "]";
    }

}
