package tn.esprit.Mappers;


import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import tn.esprit.Dtos.ApplyOnOfferDto;
import tn.esprit.Dtos.CompanyDto;
import tn.esprit.Dtos.RecruitmentOfferDto;
import tn.esprit.Entitys.*;
import tn.esprit.Libs.IGenericMapperDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RecruitmentOfferMapper  implements IGenericMapperDto<RecruitmentOfferDto, RecruitmentOffer> {
    private IGenericMapperDto<CompanyDto,Company >  iCompanyMapper;
    private IGenericMapperDto<ApplyOnOfferDto,ApplyOnOffer >  iApplyOnOfferMapper;
    @Lazy
    public RecruitmentOfferMapper (  IGenericMapperDto<CompanyDto,Company >  iCompanyMapper ,
                                     IGenericMapperDto<ApplyOnOfferDto,ApplyOnOffer >  iApplyOnOfferMapper){
        this.iApplyOnOfferMapper =  iApplyOnOfferMapper;
        this.  iCompanyMapper =  iCompanyMapper;  }

    @Override
    public RecruitmentOffer mapToEntity(RecruitmentOfferDto Dto) {
        if ( Dto == null ){ return null ;}

      List<ApplyOnOffer>   listApplyOnOffers = Dto.getApplyOnOffersDto().stream()
                .map(Obj -> iApplyOnOfferMapper.mapToEntity(Obj))
                .collect(Collectors.toList());

        return RecruitmentOffer.builder()
                .id(Dto.getId())
                .typeOffer(Dto.getTypeOffer())
                .title(Dto.getTitle())
                .vacantJobs(Dto.getVacantJobs())
                .description(Dto.getDescription())
                .requirements(Dto.getRequirements())
                .contractType(Dto.getContractType())
                .gender(Dto.getGender())
                .langue(Dto.getLangue())
                .experienceFrom(Dto.getExperienceFrom())
                .experienceTo(Dto.getExperienceTo())
                .startDateTime(Dto.getStartDateTime())
                .endDateTime(Dto.getEndDateTime())
                .statusOffer(Dto.getStatusOffer())
                .studyLevel(Dto.getStudyLevel())
                .campany( iCompanyMapper.mapToEntity(Dto.getCampanyDto()))
                .applyOnOffers(listApplyOnOffers)
                 // .applyOnOffers(Dto.getApplyOnOffers())
                .forms(Dto.getForms())
                .build();
    }

    @Override
    public RecruitmentOfferDto mapToDto(RecruitmentOffer Entity , Class<?> typeclass ) {
        if ( Entity == null ){ return null ;}
       //CompanyDto  companyDto = ( (  typeclass = ) ? : iCompanyMapper.mapToDto(Entity.getCampany(),typeclass) );

       // List<ApplyOnOffer> listApplyOnOffers  =   (Entity.getApplyOnOffers()==null || typeclass == ApplyOnOffer.class ? null :  Entity.getApplyOnOffers());
        List<ApplyOnOfferDto> listApplyOnOffersDto  =   (Entity.getApplyOnOffers()==null || typeclass == ApplyOnOfferDto.class
                ? null :
                Entity.getApplyOnOffers().stream()
                        .map(Obj -> iApplyOnOfferMapper.mapToDto(Obj,RecruitmentOfferDto.class))
                        .collect(Collectors.toList())
                );


        CompanyDto companyDto =   (Entity.getCampany() ==null || typeclass == CompanyDto.class
                ?
                null :
                iCompanyMapper.mapToDto(Entity.getCampany(),RecruitmentOfferDto.class));

        return RecruitmentOfferDto.builder()
                .id(Entity.getId())
                .typeOffer(Entity.getTypeOffer())
                .title(Entity.getTitle())
                .vacantJobs(Entity.getVacantJobs())
                .description(Entity.getDescription())
                .requirements(Entity.getRequirements())
                .contractType(Entity.getContractType())
                .gender(Entity.getGender())
                .langue(Entity.getLangue())
                .experienceFrom(Entity.getExperienceFrom())
                .experienceTo(Entity.getExperienceTo())
                .startDateTime(Entity.getStartDateTime())
                .endDateTime(Entity.getEndDateTime())
                .statusOffer(Entity.getStatusOffer())
                .studyLevel(Entity.getStudyLevel())
                //.campanyDto( iCompanyMapper.mapToDto(Entity.getCampany(),RecruitmentOffer.class))
                .campanyDto( companyDto )
                //.applyOnOffers(listApplyOnOffers)
                .applyOnOffersDto(listApplyOnOffersDto)
                .forms(Entity.getForms())
                .build();
    }
}
