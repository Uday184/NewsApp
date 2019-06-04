import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { News } from '../../../model/news';
import { NewsService } from '../../service/news.service';
import { UpdateComponent } from '../update/update.component';
import { MatDialog, MatSnackBar } from '@angular/material';

@Component({
  selector: 'app-thumbnail',
  templateUrl: './thumbnail.component.html',
  styleUrls: ['./thumbnail.component.css']
})
export class ThumbnailComponent implements OnInit {

  @Input()
  news:News;
  @Input()
  isAdmin:boolean;
  @Input()
  category:string;

  @Output()
  delete = new EventEmitter();

  constructor(private service:NewsService, private dialog: MatDialog, private snackBar: MatSnackBar) { 

  }

  ngOnInit() {
  
  }

  addNews(){
    this.news.category = this.category;
    this.service.addNews(this.news).subscribe((data)=>{
      this.snackBar.open("Added Successfully", "", {
        duration: 2000,
      });
    },(err)=>{
      console.log(err);
    });
  }

  deleteNews(){
    this.service.deleteNews(this.news).subscribe((data)=>{
      this.delete.emit(this.news);
    },(err)=>{

    });
  }

  updateNews(){
    
     let dialogRef = this.dialog.open(UpdateComponent, {
      width: '600px',
      data : {obj: this.news}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
    
    this.service.updateNews(this.news);
  }

}