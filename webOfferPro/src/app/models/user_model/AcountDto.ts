import { CompanyDto } from "../offer_pro_model/CompanyDto";
import { AttachementDto } from "./AttachementDto"; 
import { Gender } from "./Gender";
import { StateRegion } from "./StateRegion";
import { UserDto } from "./UserDto";
export class AccountDto {
    id!: number; 
    createdAt!: Date ;
    firstname! : string ;
    lastname! : string ;
    cin!: number; 
    phone!: number;   
    dateOfBirth!: Date ;
    email! : string ;
    linkedIn! : string ;
    github! : string ;
    gender! : Gender;   
    state! : StateRegion;  
    city! : string ;
    zipCode!: number;  
    address! : string ;
    userDto : UserDto= new UserDto(); 
    attachementsDto : AttachementDto[]= new Array<AttachementDto>();  
    companyDto : CompanyDto = new CompanyDto()
}