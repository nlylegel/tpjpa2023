package services;

import dao.CustomerDao;
import dao.SlotDao;
import dao.TeacherDao;
import domain.Slot;
import dto.SlotDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceContext;
import jpa.EntityManagerHelper;

import java.util.ArrayList;
import java.util.List;

public class SlotServices {
    @PersistenceContext
    EntityManager manager = EntityManagerHelper.getEntityManager();;

    CustomerDao customerDao;
    SlotDao slotDao;
    TeacherDao teacherDao;

    public SlotServices() {
        this.customerDao = new CustomerDao(manager);
        this.slotDao = new SlotDao(manager);
        this.teacherDao = new TeacherDao(manager);

    }
    public List<SlotDTO> getSlots() {
        List<Slot> slots = slotDao.getSlots();
        List<SlotDTO> newListSlotDTO = new ArrayList<>();
        slots.forEach((element) -> {
            SlotDTO temp = new SlotDTO(element.getId(), element.getDate(), element.getTeacher().getId(), element.getCustomer().getId());
            newListSlotDTO.add(temp);
        });
        return newListSlotDTO;
    }

    public SlotDTO getSlotById(Long id) {
        Slot element = slotDao.getSlotById(id);
        SlotDTO temp = new SlotDTO(element.getId(), element.getDate(), element.getTeacher().getId(), element.getCustomer().getId());
        return temp;
    }

    public SlotDTO createSlot(Long teacherId) {
        Slot element = slotDao.createSlot(teacherId);
        SlotDTO temp = new SlotDTO(
                element.getId(),
                element.getDate(),
                element.getTeacher().getId(),
                element.getCustomer() != null ? element.getCustomer().getId() : 0);
        return temp;
    }

    public void deleteSlot(Long slotId){
        slotDao.deleteSlot(slotId);
    }
}
