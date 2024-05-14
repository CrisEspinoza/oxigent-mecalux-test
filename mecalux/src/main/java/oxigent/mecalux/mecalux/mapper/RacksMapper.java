package oxigent.mecalux.mecalux.mapper;

import org.springframework.beans.BeanUtils;
import oxigent.mecalux.mecalux.dto.Rack.CreateRacksDto;
import oxigent.mecalux.mecalux.dto.Rack.RacksDto;
import oxigent.mecalux.mecalux.model.Racks;

import java.util.ArrayList;
import java.util.List;

public class RacksMapper {

    public static RacksDto ToRacksDto(Racks racks) {
        RacksDto racksDto = RacksDto.builder().build();
        BeanUtils.copyProperties(racks, racksDto);
        return racksDto;
    }

    public static ArrayList<RacksDto> generateRacksDtos(List<Racks> racksArrayList) {
        int i;
        ArrayList<RacksDto> racksDtos = new ArrayList<>();
        for (i = 0; i < racksArrayList.size(); i++) {
            racksDtos.add(ToRacksDto(racksArrayList.get(i)));
        }
        return racksDtos;
    }

    public static Racks toRacks(CreateRacksDto racksDto) {
        Racks racks = new Racks();
        BeanUtils.copyProperties(racksDto, racks);
        return racks;
    }
}
