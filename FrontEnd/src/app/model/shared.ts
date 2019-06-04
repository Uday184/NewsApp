import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SharedService {

    public isAdmin:boolean;
    public actionType:string;
    public searchKey:string;
}
