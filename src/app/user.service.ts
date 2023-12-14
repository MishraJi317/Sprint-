import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { User } from './user';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseUrl = "http://localhost:8080/auth/login"
  constructor( private http: HttpClient){}



  getLogin(user)
  {
    return this.http.post(`${this.baseUrl}`,user)
  }

  loginUser(token)
  {
    localStorage.setItem("token",token);
    return true;
  }

  isLoggedIn()
  {
    let token = localStorage.getItem("token");
    if(token==undefined || token==='' || token==null)
    {
      return false;
    }

    else
    {
      return true;
    }
  }

  logout()
  {
    localStorage.removeItem("token");
  }

  getToken()
  {
    return localStorage.getItem("token");
  }
}
