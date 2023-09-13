import {  Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-message-box-confirmation-standart',
  templateUrl: './message-box-confirmation-standart.component.html',
  styleUrls: ['./message-box-confirmation-standart.component.css']
})
export class MessageBoxConfirmationStandartComponent implements OnInit  {
  constructor() { }
  ngOnInit(): void {} 

  @Input() state : boolean = false;
  @Input() title?: string;
  @Input() text?: string;
  @Output() onClickEvent=new EventEmitter<boolean>();  
  onClickNo():void { 
    this.state = false; 
    this.onClickEvent.emit(this.state);
  }
  onClickYes():void {  
    this.state = true;  
    this.onClickEvent.emit(this.state);
  }
}
