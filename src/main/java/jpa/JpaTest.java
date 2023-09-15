package jpa;
import java.util.List;

import dao.CustomerDao;
import dao.SlotDao;
import dao.TeacherDao;
import domain.Customer;
import domain.Slot;
import domain.Teacher;
import dto.SlotDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import services.CustomerServices;
import services.SlotServices;
import services.TeacherServices;

public class JpaTest {
	private EntityManager manager;
	public JpaTest(EntityManager manager) {
		this.manager = manager;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		EntityManager manager = EntityManagerHelper.getEntityManager();
		CustomerServices customerServices = new CustomerServices();
		TeacherServices teacherServices = new TeacherServices();
		SlotServices slotServices = new SlotServices();

		JpaTest test = new JpaTest(manager);
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		try {
			Customer customer1 = customerServices.createCustomer("customer1");
			Teacher prof1 = teacherServices.createTeacher("prof1");
			SlotDTO slot1 = slotServices.createSlot(prof1.getId());
			customerServices.bookAppointment(slot1.getId(), customer1.getId());

		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();
		manager.close();
		EntityManagerHelper.closeEntityManagerFactory();
		System.out.println(".. done");
	}

}
