package tn.esprit.Entitys;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//id account_id (candidat)   apply_at  cover_letter  salary_desired (1000$)  start_date  statut_apply
@Entity
@Table(name ="apply_on_offer")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ApplyOnOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" )
    long id;
    @Column(name = "username" )
    String username;
    @Column(name = "apply_at")
    LocalDateTime applyAt;
    @Column(name = "statut_apply" )
    @Enumerated(EnumType.STRING)
    StatusApply statutApply;


    @Column(name = "salary_desired" )
    int salaryDesired;
    @Column(name = "start_date", columnDefinition = "DATE")
    LocalDate startDate;//2023-02-23
    @Column(name = "cover_letter" , columnDefinition = "TEXT" )
    String coverLetter;

    @JsonIgnore
    @ManyToOne
    RecruitmentOffer recruitmentOffer;
    @OneToMany(mappedBy = "applyOnOffer", cascade = CascadeType.ALL)
    List<Answer> answers = new ArrayList<Answer>();

    public void addMultiAnswer(List<Answer> answers) {
        if ( this.getAnswers() == null ){this.setAnswers(new ArrayList<Answer>());}
        for ( Answer form :   answers ){form.setApplyOnOffer(this);
            //this.getForms().add(form);
            //forms.add(form);form.setRecruitmentOffer(this);
        }
    }
    public void addAnswer(Answer answer) {
        if ( this.getAnswers() == null ){this.setAnswers(new ArrayList<Answer>());}
        answers.add(answer);  //this.getAttachments().add(attachment);
        answer.setApplyOnOffer(this);
    }
    public void removeAnswer(Answer answer) {
        if ( this.getAnswers() == null ){ return;}
        answers.remove(answer);
        answer.setApplyOnOffer(null);
    }
}
