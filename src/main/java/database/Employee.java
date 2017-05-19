package database;

import javax.persistence.*;

@Entity
@Table(name = "empTable")
public class Employee {
    private int id;
    private String firstName;
    private String lastName;
    private String position;
    private boolean hired;
    private int salary;

    Employee() {
    }

    public Employee(int id, String firstName, String lastName, String position, boolean hired, int salary) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.hired = hired;
        this.salary = salary;
    }
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

     void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

     void setFirstName(String firstName) {
        this.firstName = firstName;
    }

     String getLastName() {
        return lastName;
    }

     void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }

    void setPosition(String position) {
        this.position = position;
    }

    public boolean isHired() {
        return hired;
    }

     void setHired(boolean hired) {
        this.hired = hired;
    }

    public int getSalary() {
        return salary;
    }

     void setSalary(int salary) {
        this.salary = salary;
    }
}
