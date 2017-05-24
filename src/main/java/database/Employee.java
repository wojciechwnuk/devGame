package database;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "empTable")
public class Employee {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private String position;
    private boolean hired;
    private int salary;

    public Employee() {
    }

    public Employee(int id, String firstName, String lastName, String position, boolean hired, int salary) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.hired = hired;
        this.salary = salary;
    }
}
