package dto;

import domain.Customer;
import domain.Teacher;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.Date;

@XmlRootElement(name = "SlotDTO")
public class SlotDTO {

    private Long id;

    private Date date;

    private long teacherId;

    private long customerId;

    public SlotDTO(Long id, Date date, Long teacherId, Long customerId){
        this.id = id;
        this.date = date;
        this.teacherId = teacherId;
        this.customerId = customerId;
    }
    @XmlElement(name = "date")
    public Date getDate() {
        return date;
    }

    @XmlElement(name = "customerId")
    public long getCustomerId() {
        return customerId;
    }

    @XmlElement(name = "id")
    public Long getId() {
        return id;
    }

    @XmlElement(name = "teacherId")
    public long getTeacherId() {
        return teacherId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    protected void setId(Long id) {
        this.id = id;
    }

    public void setTeacherId(long teacherId) {
        this.teacherId = teacherId;
    }
}
