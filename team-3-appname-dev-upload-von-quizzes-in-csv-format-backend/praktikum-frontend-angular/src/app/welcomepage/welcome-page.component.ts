import { Component } from '@angular/core';
import {HeaderComponent} from "../header/header.component";

@Component({
  selector: 'app-welcomepage',
  standalone: true,
  imports: [
    HeaderComponent
  ],
  templateUrl: './welcome-page.component.html',
  styleUrl: './welcome-page.component.scss'
})
export class WelcomePageComponent {

}
