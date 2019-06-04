import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { AuthenticationModule } from './authentication/authentication.module';
import { NewsModule } from './news/news.module';
import { MatButtonModule } from '@angular/material';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatCardModule, MatFormFieldModule, MatInputModule } from '@angular/material';
import { FormsModule } from '@angular/forms';
import { NewsRoutingModule } from './router.module';
import { Routes, RouterModule } from '@angular/router';
import { SecurityIsolation } from './security-isolation';
import { CommService } from './model/comm';




const appRouter:Routes = [
  {
    path: '',
    redirectTo: '/login',
    pathMatch: 'full'
  }
]

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AuthenticationModule,
    NewsModule,
    MatButtonModule,
    MatCardModule, MatFormFieldModule, MatInputModule,
    FormsModule,
    BrowserAnimationsModule,
    NewsRoutingModule,
    RouterModule.forRoot(appRouter)
  ],
  providers: [
    SecurityIsolation,
    CommService
  ],
  bootstrap: [AppComponent],
  exports:[
    MatButtonModule,
    MatCardModule, MatFormFieldModule, MatInputModule,
    FormsModule,
    NewsRoutingModule
  ]
})
export class AppModule { }