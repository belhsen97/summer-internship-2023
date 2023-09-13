import { ReponseStatus } from "./ReponseStatus";
export class MsgReponseStatusDto { 
    title! : string ;
    datestamp! : Date ;
    timestamp! : Date ; 
    status! : ReponseStatus ;
    message! : string ;
}