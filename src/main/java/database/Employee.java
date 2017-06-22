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
}
