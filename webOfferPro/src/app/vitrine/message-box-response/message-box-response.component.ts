import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { MsgReponseStatusDto } from 'src/app/models/user_model/MsgReponseStatusDto';
import { ReponseStatus } from 'src/app/models/user_model/ReponseStatus';


@Component({
  selector: 'app-message-box-response',
  templateUrl: './message-box-response.component.html',
  styleUrls: ['./message-box-response.component.css']
})
export class MessageBoxResponseComponent {
  readonly  SUCCESSFUL :ReponseStatus = ReponseStatus.SUCCESSFUL;
  readonly  ERROR:ReponseStatus = ReponseStatus.ERROR;
  readonly  UNSUCCESSFUL:ReponseStatus = ReponseStatus.UNSUCCESSFUL;
  @Input() state : boolean = false;
  @Input() size?: string; //modal-xl   modal-sm  modal-lg
  @Input() msgReponse: MsgReponseStatusDto = new MsgReponseStatusDto(); 
  @Output() onCloseEvent=new EventEmitter<boolean>();  
  constructor() { }
  onClickNo(){this.state = false;  this.onCloseEvent.emit(this.state);  }
}
