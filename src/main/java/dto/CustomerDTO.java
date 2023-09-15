package dto;

import domain.Slot;
import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "Customer")
public class CustomerDTO {
    private Long id;

    private String name;

    protected List<Long> appointments = new ArrayList<Long>();

    public CustomerDTO(Long id, String name, List<Long> appointments) {
        this.id = id;
        this.name = name;
        this.appointments = appointments;
    }
    public CustomerDTO() {}

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

    @XmlElementWrapper(name = "appointments")
    @XmlElement(name = "appointment")
    public List<Long> getAppointments() {
        return appointments;
    }

    protected void setAppointments(List<Long> slot) { this.appointments = slot; }
    public void addAppointments(Long appointmentId) {
        this.appointments.add(appointmentId);
    }

    public void removeAppointments(Long appointmentId) {
        this.appointments.remove(appointmentId);
    }

    @Override
    public String toString() {
        return "Employee [id=" + id + ", name=" + name + ", department="
                + appointments + "]";
    }

}
