import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {WarehouseTableComponent} from './components/warehouse-table/warehouse-table.component';
import {WarehouseDialogComponent} from './components/warehouse-dialog/warehouse-dialog.component';
import {RackTableComponent} from './components/rack-table/rack-table.component';
import {SharedModule} from '../shared/shared.module';
import {FormsModule} from '@angular/forms';
import {MatDialogModule} from '@angular/material/dialog';
import {MatFormField, MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from '@angular/material/input';
import {MatButtonModule} from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';

@NgModule({
  declarations: [
    WarehouseTableComponent,
    WarehouseDialogComponent,
    RackTableComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    MatFormField,
    SharedModule,
    MatDialogModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule,
    BrowserAnimationsModule
  ],
  exports: [
    WarehouseTableComponent
  ]
})
export class WarehouseModule {}
