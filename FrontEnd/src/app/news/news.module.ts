import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ToolbarComponent } from './components/toolbar/toolbar.component';
import { MatToolbarModule, MatDialogModule } from '@angular/material';
import { MatButtonModule, MatCardModule, MatFormFieldModule, MatInputModule, MatSnackBarModule } from '@angular/material';
import { ContainerComponent } from './components/container/container.component';
import { ThumbnailComponent } from './components/thumbnail/thumbnail.component';
import { NewsService } from './service/news.service';
import { AuthenticationModule } from '../authentication/authentication.module';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { SharedService } from '../model/shared';
import { UpdateComponent } from './components/update/update.component';

@NgModule({
  imports: [
    CommonModule,
    MatToolbarModule,
    MatButtonModule,
    MatCardModule,
    MatFormFieldModule, MatInputModule,
    MatSnackBarModule,
    AuthenticationModule,
    FormsModule,
    HttpClientModule,
    MatDialogModule,
    MatButtonModule
  ],
  declarations: [ToolbarComponent, ContainerComponent, ThumbnailComponent, UpdateComponent],
  exports:[
    ToolbarComponent,
    ContainerComponent, ThumbnailComponent,
    MatButtonModule, MatSnackBarModule
  ],
  providers:[
    NewsService,
    SharedService
  ],entryComponents:[
    UpdateComponent
  ]
})
export class NewsModule { }
