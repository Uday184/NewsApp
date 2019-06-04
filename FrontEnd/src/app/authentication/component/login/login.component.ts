import { Component, OnInit } from '@angular/core';
import { User } from '../../../model/user';
import { AuthenticationService } from '../../service/auth.service';
import { Router } from '@angular/router';
import { MAT_CHECKBOX_CLICK_ACTION, MatSnackBar } from '@angular/material';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user: User;

  constructor(private service: AuthenticationService, private route: Router, private snackBar: MatSnackBar) {
    this.user = new User();
  }

  ngOnInit() {
  }

  onClick(actionType: string) {
    if(actionType === 'login'){
      this.service.loginUser(this.user).subscribe(
        (data) => {
          if(data['token']){
            this.service.setToken(data['token']);
            this.service.setUserType(data['userType']);
            this.service.setUser(this.user.userId);
            this.route.navigate(['/headlines']);
        }
        }, (err) => {
  //alert('Login failed pls try again');
  this.snackBar.open("Login Failed . Please try again", "", {
    duration: 2000,
  });
        }
      );
    }else{
      this.route.navigateByUrl("/register");
    }
  }
}
