import { Injectable } from "@angular/core";
import { BehaviorSubject } from "rxjs";


@Injectable()
export class CommService{

    private category = new BehaviorSubject<string>(null);
    categoryStatus = this.category.asObservable();

    private search = new BehaviorSubject<string>(null);
    searchStatus = this.search.asObservable();

    constructor(){

    }

    onChangeCategory(message: string){
        this.category.next(message);
    }

    onChangeSearch(searchKey:string){
        this.search.next(searchKey);
    }

}
