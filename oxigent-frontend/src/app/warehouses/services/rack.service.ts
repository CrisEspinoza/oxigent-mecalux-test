import {Injectable} from '@angular/core';
import {Observable, switchMap} from 'rxjs';
import {Warehouse} from '../../shared/models/warehouse.model';
import {WarehouseDialogComponent} from "../components/warehouse-dialog/warehouse-dialog.component";
import {MatDialog} from "@angular/material/dialog";
import {ApiService} from "../../core/service/api.service";
import {Rack} from "../../shared/models/rack.model";

@Injectable({
  providedIn: 'root'
})
export class RackService {
  constructor(private dialog: MatDialog, private api: ApiService) { }

  getRacks(uuid: string): Observable<Rack[]> {
    return this.api.getAllById("rack", uuid)
  }

  guardaDetalle(warehouse: Warehouse, title: string): Observable<any> {
    const dialogRef = this.dialog.open(WarehouseDialogComponent, {
      width: '500px',
      data: { warehouse, title, mode: "guardar" }
    });

    return dialogRef.afterClosed().pipe(
      switchMap(result => {
        if (result) {
          result.id = warehouse.id
          return this.api.update("warehouses", result)
        } else {
          return new Observable();
        }
      })
    );
  }


}
