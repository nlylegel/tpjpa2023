package dao;

import domain.Slot;
import domain.Teacher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class SlotDao {

    EntityManager manager;

    public SlotDao(EntityManager manager) {
        this.manager = manager;
    }
    public List<Slot> getSlots() {
        List<Slot> slots = manager.createQuery("Select a From Slot a", Slot.class)
                .getResultList();
        return slots;
    }

    public Slot getSlotById(Long id) {
        Slot slot = manager.find(Slot.class, id);
        return slot;
    }

    public Slot createSlot(Long teacherId) {
        Teacher teacher = manager.find(Teacher.class, teacherId);
        if(teacher != null) {
            Slot slot = new Slot(manager.find(Teacher.class, teacherId));
            EntityTransaction tx = manager.getTransaction();
            tx.begin();
            manager.persist(slot);
            tx.commit();
            return slot;
        } else {
            throw new Error("Teacher does not exists");
        }
    }

    public void deleteSlot(Long slotId){
        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        manager.remove(manager.find(Slot.class, slotId));
        tx.commit();
    }
}
