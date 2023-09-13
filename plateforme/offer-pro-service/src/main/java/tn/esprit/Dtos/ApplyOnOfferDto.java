package tn.esprit.Dtos;

import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.Entitys.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApplyOnOfferDto {
    long id;
    String username;
    StatusApply statutApply;

    /*String coverLetter;
    int salaryDesired;
    LocalDate startDate;*/

    RecruitmentOfferDto recruitmentOffer;
    List<Answer> answers = new ArrayList<Answer>();
}
