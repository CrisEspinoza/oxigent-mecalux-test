import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Warehouse } from '../../../shared/models/warehouse.model';
import {Rack} from "../../../shared/models/rack.model";

@Component({
  selector: 'app-warehouse-dialog',
  templateUrl: './warehouse-dialog.component.html',
  styleUrl: './warehouse-dialog.component.css'
})
export class WarehouseDialogComponent {
  constructor(
    public dialogRef: MatDialogRef<WarehouseDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: { title: string, warehouse: Warehouse , mode: string, racks: Array<Rack> }
  ) {}

  onClose(result: Warehouse | null): void {
    this.dialogRef.close(result);
  }
}
