package oxigent.mecalux.mecalux.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import oxigent.mecalux.mecalux.dto.PermutationDto;
import oxigent.mecalux.mecalux.dto.Warehouse.CreateWarehouseDto;
import oxigent.mecalux.mecalux.dto.Warehouse.WarehouseDto;
import oxigent.mecalux.mecalux.service.WarehouseService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/warehouses")
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

    @PostMapping("")
    public ResponseEntity<WarehouseDto> create(@RequestBody @Valid CreateWarehouseDto createWarehouseDto) {
        WarehouseDto created = warehouseService.create(createWarehouseDto);
        return ResponseEntity.ok(created);
    }

    @GetMapping("")
    public ResponseEntity<List<WarehouseDto>> getAl() {
        List<WarehouseDto> warehouses = warehouseService.findAll();
        return ResponseEntity.ok(warehouses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WarehouseDto> getById(@PathVariable Long id) {
        WarehouseDto warehouseDto = warehouseService.findById(id);
        return ResponseEntity.ok(warehouseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WarehouseDto> update(@PathVariable Long id, @RequestBody @Valid CreateWarehouseDto updateWarehouseDto) {
        WarehouseDto updated = warehouseService.update(id, updateWarehouseDto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        warehouseService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/permutations")
    public ResponseEntity<PermutationDto> permutations(@PathVariable Long id){
        PermutationDto permutationDto = warehouseService.permutations(id);
        return ResponseEntity.ok(permutationDto);
    }
}
