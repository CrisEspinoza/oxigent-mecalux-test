import { Component, Input, OnInit } from '@angular/core';
import { Rack } from '../../../shared/models/rack.model';

@Component({
  selector: 'app-rack-table',
  templateUrl: './rack-table.component.html'
})
export class RackTableComponent implements OnInit {
  @Input() racks: Rack[] | undefined;

  columns = [
    { key: 'id', title: 'ID' },
    { key: 'code', title: 'Code' },
    { key: 'capacity', title: 'Capacity' },
    { key: 'warehouseId', title: 'Warehouse ID' }
  ];

  ngOnInit(): void {
  }
}
