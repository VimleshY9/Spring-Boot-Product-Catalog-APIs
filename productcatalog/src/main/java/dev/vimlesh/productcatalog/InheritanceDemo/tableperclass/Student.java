package dev.vimlesh.productcatalog.InheritanceDemo.tableperclass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "tp_student")
public class Student extends User {
    private double psp;
    private double attendance;
}