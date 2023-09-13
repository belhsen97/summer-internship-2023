
import { Roles } from "./Roles";
export class UserDto {
    id!: number;
    username! : string ; 
    //password! : string ;
    role! : Roles;
    enabled ! : boolean ; 
    code! : string ;
}