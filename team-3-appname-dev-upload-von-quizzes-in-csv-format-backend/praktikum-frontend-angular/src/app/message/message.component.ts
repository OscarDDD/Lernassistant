import {Component, inject, OnInit} from '@angular/core';
import {MessageService} from "./message.service";
import {Observable, take} from "rxjs";
import {Message} from "./message.model";
import {AsyncPipe} from "@angular/common";
import {FormControl, FormsModule, ReactiveFormsModule} from "@angular/forms";

@Component({
  selector: 'app-message',
  standalone: true,
  imports: [
    AsyncPipe,
    FormsModule,
    ReactiveFormsModule
  ],
  templateUrl: './message.component.html',
  styleUrl: './message.component.scss'
})
export class MessageComponent  implements OnInit{

  messageService = inject(MessageService)

  lastMessage! : Observable<Message>
  message = new FormControl('');

  ngOnInit(): void {
    this.lastMessage = this.messageService.getMessage()
  }

  updateMessage() {
    this.messageService
      .postMessage({content: this.message.value! })
      .pipe(take(1)).subscribe(() => this.message.reset()
    )
  }
}
