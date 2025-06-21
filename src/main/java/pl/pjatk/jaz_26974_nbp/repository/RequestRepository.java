package pl.pjatk.jaz_26974_nbp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pjatk.jaz_26974_nbp.model.Request;

public interface RequestRepository extends JpaRepository<Request, Long> {
}
