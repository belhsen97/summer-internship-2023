import { Gender } from "../user_model/Gender";
import { ApplyOnOfferDto } from "./ApplyOnOfferDto";
import { CompanyDto } from "./CompanyDto";
import { ContractType } from "./ContractType";
import { FormDto } from "./FormDto";
import { StatusOffer } from "./StatusOffer"; 
import { StudyLevel } from "./StudyLevel";
import { TypeOffer } from "./TypeOffer";

export class RecruitmentOfferDto {
    id! : number ;
    typeOffer! : TypeOffer ;
    title! : string ;
    description! : string ;
    requirements! : string ;
    contractType! : ContractType ; 
    endDateTime :  Date = new Date();
    startDateTime : Date = new Date();
    statusOffer! : StatusOffer;
    vacantJobs! : number;
    gender! :  Gender; 
    experienceFrom! : number;
    experienceTo! : number;
    langue! : string ;
    studyLevel! : StudyLevel;
    campanyDto : CompanyDto=  new CompanyDto();;
    applyOnOffersDto : ApplyOnOfferDto[]  = new Array<ApplyOnOfferDto>();
    forms : FormDto[] = new Array<FormDto>();  ;
    constructor() {}
} 