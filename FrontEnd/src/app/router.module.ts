import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './authentication/component/login/login.component';
import { RegisterComponent } from './authentication/component/register/register.component';
import { ContainerComponent } from './news/components/container/container.component';
import { SecurityIsolation } from './security-isolation';

const movieRoutes: Routes = [
  {
      path: '',
      children: [
          {
              path: '',
              redirectTo: '/login',
              pathMatch: 'full',
            
          },
          {
              path: 'login',
              component: LoginComponent,         
          },
          {
              path: 'register',
              component: RegisterComponent,
              data:{
                  
              }             
          },
          {
            path: 'register/admin',
            component: RegisterComponent,
            data:{
                
            }             
          },
          {
              path: 'headlines',
              component: ContainerComponent,
              data:{
                  
              },
              canActivate:[SecurityIsolation]             
          },
          {
            path: 'watchList',
            component: ContainerComponent,
            data:{
                
            }, canActivate:[SecurityIsolation]           
        },
          {
              path: 'search',
              component: ContainerComponent,
              data:{
                  
              }, canActivate:[SecurityIsolation]          
          }
      ]
  }
]
@NgModule({
  imports: [
      RouterModule.forChild(movieRoutes)
  ],
  exports: [
      RouterModule
  ]
})
export class NewsRoutingModule { }