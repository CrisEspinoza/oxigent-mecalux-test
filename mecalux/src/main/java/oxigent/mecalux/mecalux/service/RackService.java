package oxigent.mecalux.mecalux.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import oxigent.mecalux.mecalux.dto.Rack.CreateRacksDto;
import oxigent.mecalux.mecalux.dto.Rack.RacksDto;
import oxigent.mecalux.mecalux.excepciones.NotFoundException;
import oxigent.mecalux.mecalux.mapper.RacksMapper;
import oxigent.mecalux.mecalux.mapper.WarehouseMapper;
import oxigent.mecalux.mecalux.model.Racks;
import oxigent.mecalux.mecalux.model.Warehouses;
import oxigent.mecalux.mecalux.repository.RacksRepository;
import oxigent.mecalux.mecalux.repository.WarehouseRepository;

import java.util.List;
import java.util.UUID;

@Service
public class RackService {

    @Autowired
    private RacksRepository racksRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

    public RacksDto create(CreateRacksDto createRacksDto) {
        Racks racks = RacksMapper.toRacks(createRacksDto);
        racks.setUuid(String.valueOf(UUID.randomUUID()));
        racks.setWarehouse(warehouseRepository.getReferenceById(createRacksDto.getWarehouse()));
        try{
            racks = racksRepository.save(racks);
            return RacksMapper.ToRacksDto(racks);
        }catch (DataAccessException e) {
            throw new RuntimeException("Failed to save rack due to data access problems", e);
        }
    }

    public List<RacksDto> findAllByUuid(String uuid) {
        try {
            Warehouses warehouses = warehouseRepository.findByUuid(uuid);
            return RacksMapper.generateRacksDtos(racksRepository.findAllByWarehouse(warehouses));
        } catch (DataAccessException e) {
            throw new RuntimeException("Failed to getAll rack due to data access problems", e);
        }
    }

    public void delete(Long id) {
        try {
            racksRepository.deleteById(id);
        } catch (DataAccessException e) {
            throw new RuntimeException("Failed to delete rack due to data access problems", e);
        }
    }
}
