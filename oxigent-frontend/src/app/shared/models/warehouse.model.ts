import {Rack} from "./rack.model";

export interface Warehouse {
  id: number;
  uuid: string;
  client: string;
  family: string;
  size: number;
  updateDate: string;
  startDate: string;
  racks: Rack[];
}
