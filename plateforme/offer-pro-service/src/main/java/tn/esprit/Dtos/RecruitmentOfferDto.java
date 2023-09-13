package tn.esprit.Dtos;

import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.Entitys.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;



@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RecruitmentOfferDto {
    long id;
    TypeOffer typeOffer;
    String title;
    int vacantJobs;
    String description;
    String requirements;
    ContractType contractType;
    Gender gender;
    String langue;
    int experienceFrom;
    int experienceTo;
    LocalDateTime startDateTime;
    LocalDateTime endDateTime;
    StatusOffer statusOffer;
    Studylevel studyLevel;
    CompanyDto campanyDto;
    List<ApplyOnOfferDto>  applyOnOffersDto = new ArrayList<ApplyOnOfferDto>();
    List<Form>  forms = new ArrayList<Form>();
}
