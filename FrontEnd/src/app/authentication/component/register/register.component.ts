import { Component, OnInit } from '@angular/core';
import { User } from '../../../model/user';
import { Router } from '@angular/router';
import { AuthenticationService } from '../../service/auth.service';
import { MatSnackBar} from '@angular/material'

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  user:User;
  constructor(private service: AuthenticationService, private route: Router, private snackBar:MatSnackBar) {
    this.user = new User();
   }

  ngOnInit() {
  }

  register(){
    if (this.route.url === '/register/admin') {
      this.user.userType = 'A';
    } else {
      this.user.userType = 'C';
    }
    this.service.registerUser(this.user).subscribe(
      (data) => {
        this.snackBar.open("Registered Successfully", "", {
          duration: 2000,
        });
        this.route.navigateByUrl('/login');
      }, (err) => {
        this.snackBar.open("Please try again", "", {
          duration: 2000,
        });
      }
    );
  }

  login(){
    this.route.navigateByUrl('/login');
  }
}