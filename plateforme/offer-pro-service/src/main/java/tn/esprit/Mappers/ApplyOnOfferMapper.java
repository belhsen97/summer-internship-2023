package tn.esprit.Mappers;

import org.springframework.stereotype.Component;
import tn.esprit.Dtos.ApplyOnOfferDto;
import tn.esprit.Dtos.AttachmentDto;
import tn.esprit.Dtos.CompanyDto;
import tn.esprit.Dtos.RecruitmentOfferDto;
import tn.esprit.Entitys.Answer;
import tn.esprit.Entitys.ApplyOnOffer;
import tn.esprit.Entitys.Company;
import tn.esprit.Entitys.RecruitmentOffer;
import tn.esprit.Libs.IGenericMapperDto;

import java.util.ArrayList;
import java.util.List;

@Component
public class ApplyOnOfferMapper implements IGenericMapperDto<ApplyOnOfferDto, ApplyOnOffer> {
    private IGenericMapperDto<RecruitmentOfferDto,RecruitmentOffer >  iRecruitmentOfferMapper;
    public ApplyOnOfferMapper (  IGenericMapperDto<RecruitmentOfferDto,RecruitmentOffer >  iRecruitmentOfferMapper ){
        this.  iRecruitmentOfferMapper =  iRecruitmentOfferMapper;  }
    @Override
    public ApplyOnOffer mapToEntity(ApplyOnOfferDto Dto) {
        if ( Dto == null ){ return null ;}
        return ApplyOnOffer.builder()
                .id(Dto.getId())
                .username(Dto.getUsername())
                //.coverLetter(Dto.getCoverLetter())
                //.salaryDesired(Dto.getSalaryDesired())
                //.startDate(Dto.getStartDate())
                //.applyAt(Dto.getApplyAt())
                .statutApply(Dto.getStatutApply())
                //.recruitmentOffer(Dto.getRecruitmentOffer())
                //.answers(Dto.getAnswers())   ????????????????????????????????????????????
                .build();
    }

    @Override
    public ApplyOnOfferDto mapToDto(ApplyOnOffer Entity, Class<?> typeclass) {
        if ( Entity == null ){ return null ;}
       //  RecruitmentOffer recruitmentOffer = (Entity.getRecruitmentOffer()==null ? null : Entity.getRecruitmentOffer() );
        RecruitmentOfferDto recruitmentOfferDto = (Entity.getRecruitmentOffer()==null || typeclass == RecruitmentOfferDto.class
                ?
                null : iRecruitmentOfferMapper.mapToDto(Entity.getRecruitmentOffer(),ApplyOnOfferDto.class) );



        List<Answer> listAnswers  =   (Entity.getAnswers()==null ? new ArrayList<Answer>() :  Entity.getAnswers() );
        return ApplyOnOfferDto.builder()
                .id(Entity.getId())
                .username(Entity.getUsername())
                //.coverLetter(Entity.getCoverLetter())
                //.salaryDesired(Entity.getSalaryDesired())
                //.startDate(Entity.getStartDate())
                //.applyAt(Entity.getApplyAt())
                .statutApply(Entity.getStatutApply())
                .recruitmentOffer(recruitmentOfferDto)
                .answers(listAnswers)
                //.recruitmentOffers((typeclass ==  RecruitmentOffer.class ? null : listRecruitmentOffers ))
                .build();
    }
}