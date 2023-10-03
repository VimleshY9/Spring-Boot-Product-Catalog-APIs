package dev.vimlesh.productcatalog.InheritanceDemo.singletable;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "st_ta")
public class TA extends User {
    private double averageRating;
}