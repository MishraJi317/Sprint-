import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Property } from './property';
import { Observable } from 'rxjs';
import { UserService } from './user.service';

@Injectable({
  providedIn: 'root'
})
export class PropertyService {

  private baseUrl = "http://localhost:8080/home/getProperties"
  private singleProperty = "http://localhost:8080/home/property/" 
  constructor( private http: HttpClient, private userservice: UserService){}



  getProperties():Observable<Property[]>
  {
    let token = this.userservice.getToken();
    let headerobj = new HttpHeaders().set("Authorization",token);
    console.log(headerobj)
    return this.http.get<Property[]>(`${this.baseUrl}`,{headers:headerobj});
  }

  getSingleProperty(id: number):Observable<Property>
  {
    let token = this.userservice.getToken();
    let headerobj = new HttpHeaders().set("Authorization",token);
    console.log(`${this.baseUrl}/${id}`);
    return this.http.get<Property>(`${this.baseUrl}/${id}`,{headers:headerobj});
  }
}
