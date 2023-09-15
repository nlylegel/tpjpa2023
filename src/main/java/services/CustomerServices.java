package services;

import dao.CustomerDao;
import dao.SlotDao;
import dao.TeacherDao;
import domain.Customer;
import domain.Slot;
import dto.CustomerDTO;
import dto.SlotDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceContext;
import jpa.EntityManagerHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerServices {
    @PersistenceContext
    private EntityManager manager = EntityManagerHelper.getEntityManager();

    CustomerDao customerDao;
    SlotDao slotDao;
    TeacherDao teacherDao;

    public CustomerServices() {
        this.customerDao = new CustomerDao(manager);
        this.slotDao = new SlotDao(manager);
        this.teacherDao = new TeacherDao(manager);

    }

    public List<CustomerDTO> getCustomers() {
        List<Customer> customers = customerDao.getCustomers();
        List<CustomerDTO> newListCustomerDTO = new ArrayList<>();
        customers.forEach((element) -> {
            CustomerDTO temp = new CustomerDTO(element.getId(), element.getName(), element.getAppointments().stream().map(e -> e.getId()).collect(Collectors.toList()));
            newListCustomerDTO.add(temp);
        });
        return newListCustomerDTO;
    }

    public CustomerDTO getCustomerById(Long id) {
        Customer customer = customerDao.getCustomerById(id);
        CustomerDTO temp = new CustomerDTO(customer.getId(), customer.getName(), customer.getAppointments().stream().map(e -> e.getId()).collect(Collectors.toList()));
        return temp;
    }

    public Customer createCustomer(String name) {
        return customerDao.createCustomer(name);
    }

    public void deleteCustomer(Long customerId) {
        customerDao.deleteCustomer(customerId);
    }

    public void bookAppointment(Long appointmentId, Long customerId) {
        Slot appointment = slotDao.getSlotById(appointmentId);
        if(appointment != null) {
            if(appointment.getCustomer() == null) {
                Customer customer = manager.find(Customer.class, customerId);
                if (customer != null) {
                    EntityTransaction tx = manager.getTransaction();
                    tx.begin();
                    appointment.setCustomer(customer);
                    customer.addAppointments(appointment);
                    tx.commit();
                } else {
                    throw new Error("Customer doesn't exist");
                }
            } else {
                throw new Error("A customer has already booked this slot");
            }
        } else {
            throw new Error("Appointment doesn't exist");
        }
    }
    public void cancelAppointment(Long appointmentId, Long customerId) {
        Slot appointment = slotDao.getSlotById(appointmentId);
        if(appointment != null) {

            Customer customer = manager.find(Customer.class, customerId);
            if (customer != null) {
                if (appointment.getCustomer() == customer) {
                    EntityTransaction tx = manager.getTransaction();
                    tx.begin();
                    appointment.setCustomer(null);
                    customer.removeAppointments(appointment);
                    tx.commit();
                } else {
                    throw new Error("Customer has already booked this slot");
                }
            } else {
                throw new Error("Customer doesn't exist");
            }
        } else {
            throw new Error("Appointment doesn't exist");
        }
    }



}
