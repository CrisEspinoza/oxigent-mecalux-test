package oxigent.mecalux.mecalux.dto.Warehouse;

import lombok.Builder;
import lombok.Data;
import oxigent.mecalux.mecalux.Enum.FamilyType;
import oxigent.mecalux.mecalux.dto.Rack.RacksDto;

import java.util.ArrayList;

@Data
@Builder
public class CreateWarehouseDto {
    private String client;
    private FamilyType family;
    private Integer size;
    private ArrayList<Long> racks;
}
