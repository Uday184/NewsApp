import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './component/login/login.component';
import { RegisterComponent } from './component/register/register.component';
import { MatCardModule, MatFormFieldModule, MatInputModule,MatButtonModule, MatSnackBarModule } from '@angular/material';
import { FormsModule } from '@angular/forms';
import { AuthenticationService } from './service/auth.service';


@NgModule({
  imports: [
    CommonModule,
    MatCardModule, MatFormFieldModule, MatInputModule,
    FormsModule,
    MatSnackBarModule,
    MatButtonModule
  ],
  declarations: [LoginComponent, RegisterComponent],
  exports: [LoginComponent, RegisterComponent],
  providers:[AuthenticationService]
})
export class AuthenticationModule { }
