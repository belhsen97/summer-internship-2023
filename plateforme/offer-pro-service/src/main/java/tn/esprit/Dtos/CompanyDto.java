package tn.esprit.Dtos;


import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.Entitys.ActivityArea;
import tn.esprit.Entitys.StateRegion;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.List;



@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
//id  name description state  city zipCode  address phone email logo activity_area
public class CompanyDto  {
    long id;
    String username;
    String name;
    String description;
    StateRegion state;
    String city;
    int zipCode;
    String address;
    int phone;
    String email;
    String linkedIn;
    String website;
    ActivityArea activity;
    AttachmentDto logo;
    AttachmentDto cover;
    List<RecruitmentOfferDto> recruitmentOffersDto = new ArrayList<RecruitmentOfferDto>();
}
