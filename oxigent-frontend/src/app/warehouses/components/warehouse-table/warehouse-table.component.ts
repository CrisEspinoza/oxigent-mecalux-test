import {Component, OnInit} from '@angular/core';
import {WarehouseService} from '../../services/warehouse.service';
import {Warehouse} from '../../../shared/models/warehouse.model';

@Component({
  selector: 'app-warehouse-table',
  templateUrl: './warehouse-table.component.html',
  styleUrls: ['./warehouse-table.component.css']
})
export class WarehouseTableComponent implements OnInit {
  warehouses: Warehouse[] = [];

  constructor(private warehouseService: WarehouseService) {}

  ngOnInit(): void {
    this.getWarehouses();
  }

  getWarehouses() {
    this.warehouseService.getWarehouses()
      .subscribe(warehouses => this.warehouses = warehouses);
  }

  agregarNuevo() {
    this.warehouseService.crear( "Crear Warehouse")
      .subscribe(
        () => {
          this.getWarehouses();
        },
        error => {
          console.error('Error al editar warehouse:', error);
        }
      );
  }

  verDetalle(warehouse: Warehouse) {
    this.warehouseService.verDetalle(warehouse, "Warehouse");
  }

  editarDetalle(warehouse: Warehouse) {
    this.warehouseService.editarDetalle(warehouse, "Editar Warehouse")
      .subscribe(
        () => {
          this.getWarehouses();
        },
        error => {
          console.error('Error al editar warehouse:', error);
        }
      );
  }

  eliminar(warehouse: Warehouse){
    this.warehouseService.eliminar(warehouse)
      .subscribe(
        () => {
          // Eliminar el almacén de la lista después de la eliminación
          this.warehouses = this.warehouses.filter(item => item !== warehouse);
        },
        error => {
          console.error('Error al eliminar warehouse:', error);
        }
      );
  }
}
