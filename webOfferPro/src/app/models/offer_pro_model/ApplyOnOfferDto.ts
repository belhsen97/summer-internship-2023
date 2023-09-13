import { Answer } from "./Answer";
import { RecruitmentOfferDto } from "./RecruitmentOfferDto";
import { StatusApply } from "./StatusApply";

export class ApplyOnOfferDto {
    id! : number ;
    username! : string ;
    statutApply! : StatusApply;
    applyAt! : Date;
    
    
    coverLetter! : string ;
    salaryDesired! : number ;
    startDate! : Date;


    recruitmentOffer : RecruitmentOfferDto =  new RecruitmentOfferDto();
    answers : Answer[]  = new Array<Answer>();  ; 
}   