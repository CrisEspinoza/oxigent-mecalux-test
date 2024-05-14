package oxigent.mecalux.mecalux.repository;

import lombok.NonNull;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import oxigent.mecalux.mecalux.model.Warehouses;

import java.util.Optional;

@Repository("warehouseRepository")
public interface WarehouseRepository extends JpaRepository<Warehouses, Long> {

    Warehouses findByUuid(String uuid);
    @Query("SELECT w FROM warehouses w JOIN w.racks WHERE w.id = :id")
    Optional<Warehouses> findByIdWithRacks(Long id);
}