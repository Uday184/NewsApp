import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SharedService } from '../../../model/shared';
import { AuthenticationService } from '../../../authentication/service/auth.service';
import { CommService } from '../../../model/comm';
import { MatSnackBar } from '@angular/material';


@Component({
  selector: 'app-toolbar',
  templateUrl: './toolbar.component.html',
  styleUrls: ['./toolbar.component.css']
})
export class ToolbarComponent implements OnInit {

  icon:string;
  isAdmin:boolean;

  constructor(private route: Router, private comm:SharedService, 
    private authService:AuthenticationService,
    private sharedService:CommService, private snackBar: MatSnackBar) {
    this.icon = "Category";
    this.isAdmin = authService.isUserAdmin();
   }

  ngOnInit() {

  }

  headLines(){
    this.comm.actionType = "H";
    this.route.navigateByUrl("/headlines");
  }

  logout(){
    this.authService.deleteToken();
    this.authService.deleteUserType();
    this.route.navigateByUrl("/login");
    this.snackBar.open("Logout successfully", "", {
      duration: 2000,
    });
  }

  searchNews(data){
    this.comm.actionType = "S";
    this.comm.searchKey = data.searchKey;
    if(this.route.url === '/search'){
      this.sharedService.onChangeSearch(data.searchKey);
    }else{
      this.route.navigateByUrl("/search");
    }
  }

  watchList(){
    this.route.navigateByUrl("/watchList");
  }
  
}