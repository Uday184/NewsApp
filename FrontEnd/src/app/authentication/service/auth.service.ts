import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/internal/Observable';
import { map, catchError } from 'rxjs/operators';
import { retry } from 'rxjs/internal/operators/retry';
import { User } from '../../model/user';
import * as jwt_decode from 'jwt-decode';


export const TOKEN_NAME="jwt_token";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  authEndPointUrl:string;
  token:string;

  
  constructor(private http: HttpClient) {
    this.authEndPointUrl = "http://localhost:8089/user/";
  }

  registerUser(movie){
      return this.http.post(this.authEndPointUrl+'register', movie, {responseType:"text"});
  }

  loginUser(movie){
    return this.http.post(this.authEndPointUrl+'login', movie, {responseType:"json"});
  }

  private handleError(error:Response){

  }

  setToken(token:string){
      return localStorage.setItem(TOKEN_NAME, token);
  }

  setUserType(userType:string){
    return localStorage.setItem("USER_TYPE", userType);
  }

  isUserAdmin():boolean{
    return localStorage.getItem("USER_TYPE")==='A'?true:false;
  }

  deleteUserType(){
    return localStorage.removeItem("USER_TYPE");
  }

  setUser(user:string){
    return localStorage.setItem("USER", user);
  }

  getUser():string{
    return localStorage.getItem("USER");
  }

  deleteUser(){
    return localStorage.removeItem("USER");
  }

  getToken(){
      return localStorage.getItem(TOKEN_NAME);
  }

  deleteToken(){
      return localStorage.removeItem(TOKEN_NAME);
  }

  isTokenExpired(token?:string):boolean{
    if(!token){
        token = this.getToken();
    }
    if(!token){
      return true;
    }
    const date = this.getTokenExpirationDate(token);
    if(date===undefined || date === null){
        return false;
    }
    return !(date.valueOf() > new Date().valueOf());
  }

  getTokenExpirationDate(token:string):Date{
    const decoded = jwt_decode(token);
    if(decoded.exp===undefined){
      return null;
    }
    const date = new Date(0);
    date.setUTCSeconds(decoded.exp);
    return date;
  }
}