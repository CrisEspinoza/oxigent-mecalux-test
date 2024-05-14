package oxigent.mecalux.mecalux.dto.Rack;

import lombok.Builder;
import lombok.Data;
import oxigent.mecalux.mecalux.Enum.RackType;

@Data
public class CreateRacksDto {
    private RackType type;
    private Long warehouse;
}
