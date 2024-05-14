package oxigent.mecalux.mecalux.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import oxigent.mecalux.mecalux.dto.Rack.CreateRacksDto;
import oxigent.mecalux.mecalux.dto.Rack.RacksDto;
import oxigent.mecalux.mecalux.service.RackService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/rack")
public class RackController {

    @Autowired
    private RackService rackService;

    @PostMapping("")
    public ResponseEntity<RacksDto> create(@RequestBody @Valid CreateRacksDto racksDto) {
        RacksDto created = rackService.create(racksDto);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<List<RacksDto>> findAllById(@PathVariable String uuid) {
        List<RacksDto> warehouses = rackService.findAllByUuid(uuid);
        return ResponseEntity.ok(warehouses);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        rackService.delete(id);
        return ResponseEntity.ok().build();
    }
}
