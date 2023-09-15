package dao;

import domain.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceContext;
import jpa.EntityManagerHelper;

import java.util.List;

public class CustomerDao {

    private EntityManager manager;
    public CustomerDao(EntityManager manager) {
        this.manager = manager;
    }

    public Customer createCustomer(String name) {
        Customer customer = new Customer(name);
        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        manager.persist(customer);
        tx.commit();
        return customer;
    }

    public List<Customer> getCustomers() {
        List<Customer> customers = manager.createQuery("Select a From Customer a", Customer.class)
                .getResultList();
        return customers;
    }

    public Customer getCustomerById(Long id) {
        Customer customer = manager.find(Customer.class, id);
        return customer;
    }

    public void deleteCustomer(Long customerId) {
        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        manager.remove(manager.find(Customer.class, customerId));
        tx.commit();
    }




}
