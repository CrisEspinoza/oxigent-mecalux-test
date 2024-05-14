package oxigent.mecalux.mecalux.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import oxigent.mecalux.mecalux.model.Racks;
import oxigent.mecalux.mecalux.model.Warehouses;

import java.util.List;
import java.util.UUID;

@Repository("racksRepository")
public interface RacksRepository extends JpaRepository<Racks, Long> {
    List<Racks> findAllByWarehouse(Warehouses warehouses);
    void deleteAllByWarehouse(Warehouses warehouses);
}