package oxigent.mecalux.mecalux.dto.Warehouse;

import lombok.Builder;
import lombok.Data;
import oxigent.mecalux.mecalux.Enum.FamilyType;
import oxigent.mecalux.mecalux.dto.Rack.RacksDto;

import java.util.ArrayList;
import java.util.Date;

@Data
@Builder
public class WarehouseDto{
    private Long id;
    private String uuid;
    private String client;
    private FamilyType family;
    private Integer size;
    private String startDate;
    private String updateDate;
}
