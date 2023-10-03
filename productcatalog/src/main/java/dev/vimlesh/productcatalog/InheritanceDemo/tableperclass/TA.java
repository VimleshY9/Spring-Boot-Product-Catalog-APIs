package dev.vimlesh.productcatalog.InheritanceDemo.tableperclass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "tp_ta")
public class TA extends User {
    private double averageRating;
}