package reperman.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reperman.entity.Part;

public interface PartRepo extends JpaRepository<Part, Long> {
}
