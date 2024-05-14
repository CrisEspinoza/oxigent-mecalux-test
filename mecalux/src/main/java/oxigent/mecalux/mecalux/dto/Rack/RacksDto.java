package oxigent.mecalux.mecalux.dto.Rack;

import lombok.Builder;
import lombok.Data;
import oxigent.mecalux.mecalux.Enum.RackType;
import oxigent.mecalux.mecalux.dto.Warehouse.WarehouseDto;

@Data
@Builder
public class RacksDto {
    private Long id;
    private String uuid;
    private RackType type;
}
