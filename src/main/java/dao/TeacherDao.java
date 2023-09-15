package dao;

import domain.Teacher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class TeacherDao {
    private EntityManager manager;

    public TeacherDao(EntityManager manager) {
        this.manager = manager;
    }

    public Teacher createTeacher(String name) {
        Teacher teacher = new Teacher(name);
        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        manager.persist(teacher);
        tx.commit();
        return teacher;
    }

    public List<Teacher> getTeachers() {
        List<Teacher> teachers = manager.createQuery("Select a From Teacher a", Teacher.class).getResultList();
        return teachers;
    }

    public Teacher getTeacherById(Long id) {
        Teacher teacher = manager.find(Teacher.class, id);
        return teacher;
    }

    public void deleteTeacher(Long teacherId) {
        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        Teacher teacher = manager.find(Teacher.class, teacherId);
        teacher.getSlots().forEach(e -> manager.remove(e));
        manager.remove(teacher);
        tx.commit();
    }
}
