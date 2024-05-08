import {inject, Injectable} from '@angular/core';
import {Message} from "./message.model";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class MessageService {
  http = inject(HttpClient);

  getMessage = () => this.http.get<Message>(environment.API_URL +'/api/messages/last')
  postMessage = (message: Message) => this.http.post<Message>(environment.API_URL+'/api/messages', message)
}
