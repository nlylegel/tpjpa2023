package domain;

import dto.SlotDTO;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import io.swagger.v3.oas.models.tags.Tag;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;

@Entity
public class Customer {
    private Long id;

    private String name;

    protected List<Slot> appointments = new ArrayList<Slot>();

    public Customer(String name) {
        this.name = name;
    }
    public Customer() {}

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

    @OneToMany(mappedBy = "customer", cascade = CascadeType.PERSIST)
    public List<Slot> getAppointments() {
        return appointments;
    }

    protected void setAppointments(List<Slot> slot) { this.appointments = slot; }
    public void addAppointments(Slot appointment) {
        this.appointments.add(appointment);
    }

    public void removeAppointments(Slot appointment) {
        this.appointments.remove(appointment);
    }

    @Override
    public String toString() {
        return "Employee [id=" + id + ", name=" + name + ", department="
                + appointments + "]";
    }

}
