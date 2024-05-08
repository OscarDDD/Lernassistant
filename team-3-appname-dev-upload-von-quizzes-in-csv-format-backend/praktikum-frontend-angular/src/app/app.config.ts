import {ApplicationConfig} from '@angular/core';
import {provideHttpClient, withFetch} from "@angular/common/http";
import {provideRouter, Routes} from "@angular/router";
import {LoginComponent} from "./login/login.component";
import {WelcomePageComponent} from "./welcomepage/welcome-page.component";
import {UploadComponent} from "./upload/upload.component";

const routes: Routes = [
  {path: '', component: WelcomePageComponent},
  { path: 'login', component: LoginComponent },
  { path: 'upload', component: UploadComponent},
];

export const appConfig: ApplicationConfig = {
  providers: [provideHttpClient(withFetch()), provideRouter(routes)]
};
