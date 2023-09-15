package services;

import dao.CustomerDao;
import dao.SlotDao;
import dao.TeacherDao;
import domain.Customer;
import domain.Teacher;
import dto.CustomerDTO;
import dto.TeacherDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceContext;
import jpa.EntityManagerHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TeacherServices {
    private EntityManager manager = EntityManagerHelper.getEntityManager();;

    CustomerDao customerDao;
    SlotDao slotDao;
    TeacherDao teacherDao;

    public TeacherServices() {
        this.customerDao = new CustomerDao(manager);
        this.slotDao = new SlotDao(manager);
        this.teacherDao = new TeacherDao(manager);

    }

    public Teacher createTeacher(String name) {
        return teacherDao.createTeacher(name);
    }

    public void deleteTeacher(Long teacherId) {
        teacherDao.deleteTeacher(teacherId);
    }

    public List<TeacherDTO> getTeachers() {
        List<Teacher> teachers = teacherDao.getTeachers();
        List<TeacherDTO> newListTeacherDTO = new ArrayList<>();
        teachers.forEach((element) -> {
            TeacherDTO temp = new TeacherDTO(element.getId(), element.getName(), element.getSlots().stream().map(e -> e.getId()).collect(Collectors.toList()));
            newListTeacherDTO.add(temp);
        });
        return newListTeacherDTO;      }

    public TeacherDTO getTeacherById(Long id) {
        Teacher teacher = teacherDao.getTeacherById(id);
        TeacherDTO temp = new TeacherDTO(teacher.getId(), teacher.getName(), teacher.getSlots().stream().map(e -> e.getId()).collect(Collectors.toList()));
        return temp;    }
}
