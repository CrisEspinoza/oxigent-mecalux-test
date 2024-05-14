import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { WarehouseTableComponent } from './warehouses/components/warehouse-table/warehouse-table.component';

const routes: Routes = [
  { path: '', component: WarehouseTableComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
