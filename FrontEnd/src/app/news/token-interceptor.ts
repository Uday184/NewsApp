import { Injectable } from "@angular/core";
import { HttpInterceptor, HttpRequest,  HttpHandler} from "@angular/common/http";
import { AuthenticationService } from '../authentication/service/auth.service';


@Injectable()
export class TokenInterceptor implements HttpInterceptor{

    constructor(private authService: AuthenticationService){

    }

    intercept(request: HttpRequest<any>, next: HttpHandler){
        console.log("Interceptor working");
        request = request.clone({
            setHeaders:{
                Authorization: `Bearer ${this.authService.getToken()}`
            }
        });
        return next.handle(request);
    }
}
