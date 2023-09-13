package tn.esprit.Entitys;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//id
//type_offer
//title (offre)
//vacant_jobs ( nbr de place)
//description
//contract_type
//gender
//langue
//experience_from
//experience_to ( 1 à 3 ans )
//study_level  Maîtrise, IEP, IUP, Bac + 4
//start_date_time
//end_date_time ( expiration )
//status_offer

@Entity
@Table(name ="recruitment_offer") //parent
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
//Another approach is to use the @JsonIdentityInfo annotation, which allows Jackson to handle cyclic dependencies by assigning unique identifiers to objects during serialization and then using these identifiers to handle references. This approach is useful when you
//With @JsonIdentityInfo, Jackson will handle the circular references by using the unique id property of each entity.
//@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class)
public class RecruitmentOffer  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" )
    long id;
    @Column(name = "type_offer" )
    @Enumerated(EnumType.STRING)
    TypeOffer typeOffer;
    @Column(name = "title" )
    String title;
    @Column(name = "vacant_jobs" )
    int vacantJobs;
    @Column(name = "description" , columnDefinition = "TEXT" )
    String description;
    @Column(name = "requirements" , columnDefinition = "TEXT" )
    String requirements;
    @Column(name = "contract_type" , length=16  )
    @Enumerated(EnumType.STRING)
    ContractType contractType;
    @Column(name = "gender" , length=8  )
    @Enumerated(EnumType.STRING)
    Gender gender;
    @Column(name = "langue" , length=64  )
    String langue;
    @Column(name = "experience_from" )
    int experienceFrom;
    @Column(name = "experience_to" )
    int experienceTo;
    @Column(name = "start_date")
    LocalDateTime startDateTime;//2023-02-23T08:30:00
    @Column(name = "end_date")
    LocalDateTime endDateTime;//2023-02-23T08:30:00
    @Column(name = "status_offer" , length=12  )
    @Enumerated(EnumType.STRING)
    StatusOffer statusOffer;
    @Column(name = "study_level", length=16  )
    @Enumerated(EnumType.STRING)
    Studylevel studyLevel;
    @ManyToOne()
    @JsonIgnore
    Company campany;
    @OneToMany(mappedBy = "recruitmentOffer",cascade = CascadeType.ALL )
    List<ApplyOnOffer>  applyOnOffers = new ArrayList<ApplyOnOffer>();
    @OneToMany(mappedBy = "recruitmentOffer", cascade = CascadeType.ALL)
    List<Form>  forms = new ArrayList<Form>();
    public void addMultiForm(List<Form> forms) {
        if ( this.getForms() == null ){this.setForms(new ArrayList<Form>());}
        for ( Form form :   forms ){form.setRecruitmentOffer(this);
            //this.getForms().add(form);
            //forms.add(form);form.setRecruitmentOffer(this);
        }
    }
    public void addForm(Form form) {
        if ( this.getForms() == null ){this.setForms(new ArrayList<Form>());}
        forms.add(form);  //this.getAttachments().add(attachment);
        form.setRecruitmentOffer(this);
    }
    public void removeForm(Form form) {
        if ( this.getForms() == null ){ return;}
        forms.remove(form);
        form.setRecruitmentOffer(null);
    }
    public void addMultiOffer(List<ApplyOnOffer> applyOnOffers) {
        if ( this.getApplyOnOffers() == null ){this.setApplyOnOffers(new ArrayList<ApplyOnOffer>());}
        for ( ApplyOnOffer applyOnOffer :   applyOnOffers ){applyOnOffer.setRecruitmentOffer(this);
            //this.getForms().add(form);
            //forms.add(form);form.setRecruitmentOffer(this);
        }
    }
    public void addOffer(ApplyOnOffer applyOnOffer) {
        if ( this.getApplyOnOffers() == null ){this.setApplyOnOffers(new ArrayList<ApplyOnOffer>());}
        applyOnOffers.add(applyOnOffer);  //this.getAttachments().add(attachment);
        applyOnOffer.setRecruitmentOffer(this);
    }
    public void removeOffer(ApplyOnOffer applyOnOffer) {
        if ( this.getApplyOnOffers() == null ){ return;}
        applyOnOffers.remove(applyOnOffer);
        applyOnOffer.setRecruitmentOffer(null);
    }
}
