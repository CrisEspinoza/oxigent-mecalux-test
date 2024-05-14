package oxigent.mecalux.mecalux.mapper;

import org.springframework.beans.BeanUtils;
import oxigent.mecalux.mecalux.dto.Warehouse.CreateWarehouseDto;
import oxigent.mecalux.mecalux.dto.Warehouse.WarehouseDto;
import oxigent.mecalux.mecalux.model.Warehouses;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static oxigent.mecalux.mecalux.mapper.RacksMapper.generateRacksDtos;

public class WarehouseMapper {

    public static WarehouseDto ToWarehousesDto(Warehouses warehouses) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        WarehouseDto warehouseDto = WarehouseDto.builder().build();
        BeanUtils.copyProperties(warehouses, warehouseDto);
        warehouseDto.setStartDate(sdf.format(warehouses.getStartDate()));
        warehouseDto.setUpdateDate(sdf.format(warehouses.getUpdateDate()));
        return warehouseDto;
    }

    public static ArrayList<WarehouseDto> generateWarehousesDtos(List<Warehouses> warehousesList) {
        int i;
        ArrayList<WarehouseDto> warehouseDtos = new ArrayList<>();
        for (i = 0; i < warehousesList.size(); i++) {
            warehouseDtos.add(ToWarehousesDto(warehousesList.get(i)));
        }
        return warehouseDtos;
    }

    public static Warehouses toWarehouses(CreateWarehouseDto warehouseDto) {
        Warehouses warehouses = new Warehouses();
        BeanUtils.copyProperties(warehouseDto, warehouses);
        return warehouses;
    }
}
