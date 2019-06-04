import { Injectable }  from '@angular/core';
import { CanActivate,Router } from '@angular/router';
import { AuthenticationService } from './authentication/service/auth.service';


@Injectable()
export class SecurityIsolation implements CanActivate{

    constructor(private authService: AuthenticationService, private router: Router){

    }

    canActivate(){
        if(!this.authService.isTokenExpired()){
            return true;
        }
        console.log(this.authService.isTokenExpired);
        console.log("auth gaurd service");
       this.router.navigate([ "/login"]);
       return false;
    }
}