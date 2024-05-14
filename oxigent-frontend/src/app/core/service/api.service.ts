import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../enviroments/environment';
import {Warehouse} from "../../shared/models/warehouse.model";
import {catchError, throwError} from "rxjs";
import {Rack} from "../../shared/models/rack.model";

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  private apiUrlBase = environment.apiUrl;

  constructor(private http: HttpClient) {}

  get(endpoint: string) {
    return this.http.get<Warehouse[]>(`${this.apiUrlBase}/${endpoint}`).pipe(
      catchError(error => {
        console.error('Error fetching warehouse:', error);
        return throwError(error);
      })
    );
  }

  getAllById(endpoint: string, uuid: string) {
    return this.http.get<Rack[]>(`${this.apiUrlBase}/${endpoint}/${uuid}`).pipe(
      catchError(error => {
        console.error('Error fetching rack:', error);
        return throwError(error);
      })
    );
  }

  post(endpoint: string, data: any) {
    console.log("here 3")
    return this.http.post(`${this.apiUrlBase}/${endpoint}`, {
      "client": data.client,
      "family": data.family,
      "size": data.size,
      "uuid": data.uuid
    }).pipe(
      catchError(error => {
        console.error('Error creando warehouse:', error);
        return throwError(error);
      })
    );
  }

  update(endpoint: string, data: any) {
    return this.http.put(`${this.apiUrlBase}/${endpoint}/${data.id}`, {
      "client": data.client,
      "family": data.family,
      "size": data.size
    }).pipe(
      catchError(error => {
        console.error('Error actualizando warehouse:', error);
        return throwError(error);
      })
    );
  }

  delete(id: number, endpoint: string) {
    return this.http.delete(`${this.apiUrlBase}/${endpoint}/${id}`).pipe(
      catchError(error => {
        console.error('Error eliminando warehouse:', error);
        throw error;
      })
    );
  }

}
