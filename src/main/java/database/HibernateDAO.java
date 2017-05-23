package database;

import lombok.Data;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Data
public class HibernateDAO {

    public long databaseSize;
    private SessionFactory sessionFactory;

    public void setUp() {
        sessionFactory = new Configuration()
                .configure() // configures settings from hibernate.cfg.xml
                .buildSessionFactory();
    }

    public void exit() {
        sessionFactory.close();
    }

    void create(String firstname, String lastname, boolean hired, String position, int salary) {

        Employee employee = new Employee();
        employee.setFirstName(firstname);
        employee.setLastName(lastname);
        employee.setHired(hired);
        employee.setPosition(position);
        employee.setSalary(salary);

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(employee);
        session.getTransaction().commit();
        databaseSize = databaseSize();

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

   public void hire(int id){
        setUp();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Employee employee = session.get(Employee.class, id);
        employee.setHired(true);
        session.getTransaction().commit();
        exit();
    }
    public void dismiss(int id){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Employee employee = session.get(Employee.class, id);
        employee.setHired(false);
        session.getTransaction().commit();
    }

   public String findLastNameById(int id) {
        String nazwisko;

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Employee employee = session.get(Employee.class, id);
        session.getTransaction().commit();
        nazwisko = employee.getLastName();
        System.out.println(nazwisko);

        return nazwisko;
    }

    public List<Employee> findAll() {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Employee> employees = session.createQuery("FROM Employee", Employee.class).getResultList();
        session.getTransaction().commit();

        return employees;
    }


    public Long countHired(boolean hired){
        setUp();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Criteria crit = session.createCriteria(Employee.class);
        crit.add(Restrictions.eq("hired", hired));
        crit.setProjection(Projections.rowCount());
        Long count = (Long) crit.uniqueResult();
        exit();
        return  count;
    }

    public List<Employee> findByHired(int hired) {
        setUp();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Employee> criteria = builder.createQuery(Employee.class);
        Root<Employee> employeeRoot = criteria.from(Employee.class);
        criteria.select(employeeRoot);
        criteria.where(builder.equal(employeeRoot.get("hired"), hired));

        List<Employee> employees = session.createQuery(criteria).getResultList();
        session.getTransaction().commit();
        exit();
        return employees;
    }

    private Long databaseSize() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Long count = (Long) session.createQuery("select count(*) as id from Employee").iterate().next();
        session.getTransaction().commit();
        return count;
    }

    public void addHumansIfEmpty() {
        if (databaseSize() == 0) {
            create("Adam", "Adamski", false, "Junior", 2500);
            create("Beata", "Beczkowska", false, "Junior", 2600);
            create("Celina", "Cycowska", false, "Regular", 3500);
            create("Damian", "Daminski", false, "Regular", 3500);
            create("Edmund", "Ebuncki", false, "Senior", 5500);
        }
    }

    public void clearTable(){
        setUp();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.createQuery("delete from Employee").executeUpdate();
        session.getTransaction().commit();
        exit();
    }

    public void updatePosition(){
        setUp();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
     //   session.createQuery("")

    }


    public static void main(String[] args) {
        HibernateDAO da = new HibernateDAO();

        da.exit();
    }
}
