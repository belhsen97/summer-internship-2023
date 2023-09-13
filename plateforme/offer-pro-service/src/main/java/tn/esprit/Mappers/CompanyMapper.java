package tn.esprit.Mappers;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import tn.esprit.Dtos.AttachmentDto;
import tn.esprit.Dtos.CompanyDto;
import tn.esprit.Dtos.RecruitmentOfferDto;
import tn.esprit.Entitys.*;
import tn.esprit.Libs.IGenericMapperDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CompanyMapper implements IGenericMapperDto<CompanyDto,Company > {
    private IGenericMapperDto<RecruitmentOfferDto,RecruitmentOffer >  iRecruitmentOfferMapper;

    public CompanyMapper (  IGenericMapperDto<RecruitmentOfferDto,RecruitmentOffer >  iRecruitmentOfferMapper ){
        this.  iRecruitmentOfferMapper =  iRecruitmentOfferMapper;  }
    @Override
    public Company mapToEntity(CompanyDto companyDto) {
        if ( companyDto == null ){ return null ;}
        return Company.builder()
                .id(companyDto.getId())
                .username(companyDto.getUsername())
                .name(companyDto.getName())
                .description(companyDto.getDescription())
                .phone(companyDto.getPhone())
                .email(companyDto.getEmail())
                .linkedIn(companyDto.getLinkedIn())
                .website(companyDto.getWebsite())
                .ativity(companyDto.getActivity())
                .state(companyDto.getState())
                .city(companyDto.getCity())
                .zipCode(companyDto.getZipCode())
                .address(companyDto.getAddress())
                .build();
    }

    @Override
    public CompanyDto mapToDto(Company company ,Class<?> typeclass  ) {
        if ( company == null ){ return null ;}
        AttachmentDto logo = (company.getLogo()==null ? null :  AttachmentMapper.mapToDto(company.getLogo()) );
        AttachmentDto cover = (company.getCover()==null ? null :  AttachmentMapper.mapToDto(company.getCover()) );
        System.out.println(typeclass);
        List<RecruitmentOfferDto> listRecruitmentOffersDto  =   (
                company.getRecruitmentOffers()==null ||  typeclass ==  RecruitmentOfferDto.class
                        ?
                        null
                        :
                        company.getRecruitmentOffers().stream()
                        .map(Obj ->   iRecruitmentOfferMapper.mapToDto(Obj , CompanyDto.class ))
                        .collect(Collectors.toList()) );

        return CompanyDto.builder()
                .id(company.getId())
                .username(company.getUsername())
                .name(company.getName())
                .description(company.getDescription())
                .email(company.getEmail())
                .linkedIn(company.getLinkedIn())
                .website(company.getWebsite())
                .phone(company.getPhone())
                .activity(company.getAtivity())
                .state(company.getState())
                .city(company.getCity())
                .zipCode(company.getZipCode())
                .address(company.getAddress())
                .logo(logo)
                .cover(cover)
                .recruitmentOffersDto(listRecruitmentOffersDto)
                .build();
    }
}

