import {Component, OnInit} from "@angular/core";
import {RouterLink} from "@angular/router";
import {FormBuilder, FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {HeaderComponent} from "../header/header.component";
import {AuthService} from "./auth.service";
import {TokenStorageService} from "./token-storage.service";


@Component({
  selector: "app-login",
  templateUrl: "./login.component.html",
  styleUrls: ["./login.component.scss"],
  standalone: true,
  imports: [
    RouterLink,
    FormsModule,
    ReactiveFormsModule,
    HeaderComponent
  ]
})

export class LoginComponent implements OnInit{
  loginForm: any = {
    username : null,
    password : null
  };
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = "";
  roles: string[] = [];

  constructor(private authService: AuthService, private tokenStorage: TokenStorageService) {
  }
  onSubmit() {
    const { username, password } = this.loginForm;

    this.authService.login(username, password).subscribe(
      data => {
        this.tokenStorage.saveToken(data.accessToken);
        this.tokenStorage.saveUser(data);

        this.isLoginFailed = false;
        this.isLoggedIn = true;
        this.roles = this.tokenStorage.getUser().roles;
        this.reloadPage();
      },
      err => {
        this.errorMessage = err.error.message;
        this.isLoginFailed = true;
      }
    );
  }

  ngOnInit(){
    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
    }
    }
  reloadPage() {
    window.location.reload();
}
}


