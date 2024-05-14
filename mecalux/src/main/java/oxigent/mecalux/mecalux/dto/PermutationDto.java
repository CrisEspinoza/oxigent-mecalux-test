package oxigent.mecalux.mecalux.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PermutationDto {
    private Integer amount;
    private List<String> options;
}
