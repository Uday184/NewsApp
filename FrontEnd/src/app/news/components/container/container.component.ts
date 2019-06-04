import { Component, OnInit } from '@angular/core';
import { NewsService } from '../../service/news.service';
import { News } from '../../../model/news';
import { SharedService } from '../../../model/shared';
import { Router } from '@angular/router';
import { AuthenticationService } from '../../../authentication/service/auth.service';
import { CommService } from '../../../model/comm';

@Component({
  selector: 'app-container',
  templateUrl: './container.component.html',
  styleUrls: ['./container.component.css']
})
export class ContainerComponent implements OnInit {

  isAdmin: boolean;
  news: Array<News>;
  category:string;

  constructor(private service: NewsService, private comm: SharedService,
    private router: Router, private authService: AuthenticationService,
    private commService: CommService) {
    this.isAdmin = this.authService.isUserAdmin();
  }

  ngOnInit() {
   
    switch (this.router.url) {
      case "/headlines":
        this.service.getHeadLines().subscribe((data) => {
          this.category = "headlines";
          this.news = data;
        }, (err) => {

        });
        break;


      case "/search":
        this.commService.searchStatus.subscribe(message => {
          this.searchServiceCall(message);
        });
        this.searchServiceCall(this.comm.searchKey);
        break;

        case "/watchList":
          this.category = "watchList"
          this.service.getAdminWatchList().subscribe(
            (data)=>{
              this.news = data;
            },
            (err)=>{

            }
          );
        break;
    }

  }

  searchServiceCall(searchKey: string) {
    this.category = "other";
    this.service.search(searchKey).subscribe(
      (data) => {
        this.news = data;
      }, (err) => {

      }
    );
  }

  deleteWatchList(delNnews){
   for(var i=0;i<this.news.length;i++){
        if(this.news[i].id === delNnews.id){
          this.news.splice(i,1);
          break;
        }
    }
  }
}