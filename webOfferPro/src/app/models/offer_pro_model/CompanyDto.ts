import { StateRegion } from "../user_model/StateRegion";
import { ActivityArea } from "./ActivityArea";
import { AttachementDto } from "./AttachementDto";  
import { RecruitmentOfferDto } from "./RecruitmentOfferDto";

export class CompanyDto {
    id! : number ;
    name! : string ;
    username! : string ;
    description! : string ;
    state! : StateRegion;  
    city! : string ;
    zipCode! : number;  
    address! : string ;
    phone!: number;   
    email! : string ;
    linkedIn! : string ;
    website! : string ;
    activity! : ActivityArea;
    logo : AttachementDto = new AttachementDto();
    cover : AttachementDto = new AttachementDto();
    recruitmentOffersDto : RecruitmentOfferDto[] = new Array<RecruitmentOfferDto>();  
}