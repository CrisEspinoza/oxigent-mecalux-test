import {Injectable} from '@angular/core';
import {Observable, switchMap} from 'rxjs';
import {Warehouse} from '../../shared/models/warehouse.model';
import {WarehouseDialogComponent} from "../components/warehouse-dialog/warehouse-dialog.component";
import {MatDialog} from "@angular/material/dialog";
import {ApiService} from "../../core/service/api.service";
import {RackService} from "./rack.service";

@Injectable({
  providedIn: 'root'
})
export class WarehouseService {
  constructor(private dialog: MatDialog, private api: ApiService, private rackService: RackService) { }

  getWarehouses(): Observable<Warehouse[]> {
    return this.api.get("warehouses")
  }

  verDetalle(warehouse: Warehouse, title: string): void {
    this.rackService.getRacks(warehouse.uuid).subscribe(racks => {
      console.log(racks)
      this.dialog.open(WarehouseDialogComponent, {
        width: '800px',
        data: { warehouse, racks, title, mode: 'ver' }
      });
    });
  }

  crear(title: string): Observable<any> {
    const dialogRef = this.dialog.open(WarehouseDialogComponent, {
      width: '250px',
      data: { warehouse: {uuid: '', client: '', family: '', size: null }, racks: null, title, mode: 'crear'}
    });

    return dialogRef.afterClosed().pipe(
      switchMap(result => {
        console.log(result)
        if (result) {
          console.log("Here")
          return this.api.post("warehouses", result);
        } else {
          return new Observable();
        }
      })
    );
  }

  editarDetalle(warehouse: Warehouse, title: string): Observable<any> {
    return this.rackService.getRacks(warehouse.uuid).pipe(
      switchMap(racks => {
        const dialogRef = this.dialog.open(WarehouseDialogComponent, {
          width: '800px',
          data: { warehouse, racks, title, mode: 'editar' }
        });

        return dialogRef.afterClosed().pipe(
          switchMap(result => {
            if (result) {
              result.id = warehouse.id;
              return this.api.update("warehouses", result);
            } else {
              return new Observable();
            }
          })
        );
      })
    );
  }

  eliminar(warehouse: Warehouse) : Observable<any> {
    return this.api.delete(warehouse.id, "warehouses");
  }
}
