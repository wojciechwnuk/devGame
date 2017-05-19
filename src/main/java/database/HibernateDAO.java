package database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateDAO {

    private SessionFactory sessionFactory;

    void setUp() {
        sessionFactory = new Configuration()
                .configure() // configures settings from hibernate.cfg.xml
                .buildSessionFactory();
    }

    void exit() {
        sessionFactory.close();
    }

    void create() {
        Employee employee = new Employee();
        employee.setFirstName("Dariusz");
        employee.setLastName("Notariusz");
        employee.setHired(true);
        employee.setPosition("Junior");
        employee.setSalary(2500);
        employee.setId(1);

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(employee);
        session.getTransaction().commit();
        session.close();
    }

    void delete(int id) {

        Employee employee = new Employee();
        employee.setId(id);

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(employee);
        session.getTransaction().commit();
        session.close();
    }

    String findLastNameById(int id){

        String nazwisko;

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Employee employee=session.get(Employee.class, id);
        session.getTransaction().commit();
        nazwisko=employee.getLastName();

        return nazwisko;
    }

    public static void main(String[] args) {

        HibernateDAO da = new HibernateDAO();
        da.setUp();

        System.out.println(da.findLastNameById(4));
        da.exit();
    }
}
