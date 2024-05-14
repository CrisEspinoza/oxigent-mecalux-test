package oxigent.mecalux.mecalux.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import oxigent.mecalux.mecalux.Enum.FamilyType;
import oxigent.mecalux.mecalux.Enum.RackType;
import oxigent.mecalux.mecalux.dto.PermutationDto;
import oxigent.mecalux.mecalux.dto.Warehouse.CreateWarehouseDto;
import oxigent.mecalux.mecalux.dto.Warehouse.WarehouseDto;
import oxigent.mecalux.mecalux.excepciones.NotFoundException;
import oxigent.mecalux.mecalux.mapper.WarehouseMapper;
import oxigent.mecalux.mecalux.model.Racks;
import oxigent.mecalux.mecalux.model.Warehouses;
import oxigent.mecalux.mecalux.repository.RacksRepository;
import oxigent.mecalux.mecalux.repository.WarehouseRepository;

import java.util.*;

@Service
public class WarehouseService {

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private RacksRepository racksRepository;

    public WarehouseDto create(CreateWarehouseDto warehouseDto) {
        Warehouses warehouses = WarehouseMapper.toWarehouses(warehouseDto);
        warehouses.setUuid(String.valueOf(UUID.randomUUID()));
        try{
            warehouses = warehouseRepository.save(warehouses);
            return WarehouseMapper.ToWarehousesDto(warehouses);
        }catch (DataAccessException e) {
            throw new RuntimeException("Failed to save warehouse due to data access problems", e);
        }
    }

    public List<WarehouseDto> findAll() {
        try {
            return WarehouseMapper.generateWarehousesDtos(warehouseRepository.findAll());
        } catch (DataAccessException e) {
            throw new RuntimeException("Failed to getAll warehouses due to data access problems", e);
        }
    }

    public WarehouseDto update(Long id, CreateWarehouseDto updateWarehouseDto) {
        return warehouseRepository.findById(id)
                .map(warehouses -> {
                    warehouses.setClient(updateWarehouseDto.getClient());
                    warehouses.setSize(updateWarehouseDto.getSize());
                    warehouses.setFamily(updateWarehouseDto.getFamily());
                    warehouses.setUpdateDate(new Date());
                    return WarehouseMapper.ToWarehousesDto(warehouseRepository.save(warehouses));
                })
                .orElseThrow(() -> new NotFoundException("Warehouse not found with id " + id));
    }

    public WarehouseDto findById(Long id) {
        return warehouseRepository.findById(id)
                .map(WarehouseMapper::ToWarehousesDto)
                .orElseThrow(() -> new NotFoundException("Warehouse not found with id " + id));
    }

    @Transactional
    public void delete(Long id) {
        try {
            Warehouses warehouse = warehouseRepository.findByIdWithRacks(id)
                    .orElseThrow(() -> new NotFoundException("Warehouse not found with id " + id));

            racksRepository.deleteAllByWarehouse(warehouse);
            warehouseRepository.deleteById(id);
        } catch (DataAccessException e) {
            throw new RuntimeException("Failed to delete warehouse due to data access problems", e);
        }
    }

    public PermutationDto permutations(Long id) {
        PermutationDto permutationDto = PermutationDto.builder().build();
        warehouseRepository.findById(id)
                .map(warehouses -> {
                    Integer size = warehouses.getSize();
                    List<String> combinations = new ArrayList<>();
                    ShelfCombinations it = new ShelfCombinations(getAllowedTypes(warehouses.getFamily()), size);
                    while (it.hasNext()) {
                        combinations.add(it.next());
                    }
                    permutationDto.setOptions(combinations);
                    permutationDto.setAmount(combinations.size());
                    return permutationDto;
                })
                .orElseThrow(() -> new NotFoundException("Warehouse not found with id " + id));
        return permutationDto;
    }

    private static char[] getAllowedTypes(FamilyType type) {
        switch (type) {
            case EST:
                return new char[] {RackType.A.getTypeChar(), RackType.B.getTypeChar(), RackType.C.getTypeChar()};
            case ROB:
                return new char[] {RackType.A.getTypeChar(), RackType.C.getTypeChar(), RackType.D.getTypeChar()};
            default:
                throw new IllegalArgumentException("Unknown type: " + type);
        }
    }
}
