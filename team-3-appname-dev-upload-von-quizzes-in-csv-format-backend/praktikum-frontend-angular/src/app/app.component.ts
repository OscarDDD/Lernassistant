import {Component} from '@angular/core';
import {CommonModule} from '@angular/common';
import {MessageComponent} from "./message/message.component";
import {HeaderComponent} from "./header/header.component";
import {WelcomePageComponent} from "./welcomepage/welcome-page.component";
import {LoginComponent} from "./login/login.component";
import {RouterLink, RouterLinkActive, RouterOutlet} from "@angular/router";
import {UploadComponent} from "./upload/upload.component";
import {HTTP_INTERCEPTORS, HttpClient, HttpClientModule} from "@angular/common/http";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule,
            MessageComponent,
            HeaderComponent,
            HttpClientModule,
            WelcomePageComponent,
            LoginComponent,
            UploadComponent,
            RouterOutlet,
            RouterLink,
            RouterLinkActive
            ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
}
