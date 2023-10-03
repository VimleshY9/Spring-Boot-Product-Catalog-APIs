package dev.vimlesh.productcatalog.repositories;

import dev.vimlesh.productcatalog.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository
extends JpaRepository<Price, Long> {
}
