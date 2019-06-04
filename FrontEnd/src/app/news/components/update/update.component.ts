import { Component, OnInit, Inject } from '@angular/core';
import { News } from '../../../model/news';
import { MatDialogRef, MatSnackBar, MAT_DIALOG_DATA } from '@angular/material';
import { NewsService } from '../../service/news.service';


@Component({
  selector: 'app-update',
  templateUrl: './update.component.html',
  styleUrls: ['./update.component.css']
})
export class UpdateComponent implements OnInit {

  news:News;
  
  constructor( private service : NewsService,
    public dialogRef : MatDialogRef<UpdateComponent>,
    @Inject(MAT_DIALOG_DATA)public data:any) { 
      
    this.news = new News();
    this.news = data.obj;
  }

  ngOnInit() {
  }

  cancel(){
    this.dialogRef.close();
  }

  update(){
      this.service.updateNews(this.news).subscribe(
        (data)=>{
          this.dialogRef.close();
        }
      );
  }

}