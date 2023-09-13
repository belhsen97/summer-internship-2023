package tn.esprit.Entitys;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//id  username  password  code  role enabled
@Entity
@Table(name ="company")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
//id  name description state  city zipCode  address phone email logo activity_area
//@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class)
public class Company  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" )
    long id;
    @Column(name = "username" )
    String username;
    @Column(name = "name" )
    String name;
    @Column(name = "description", columnDefinition = "TEXT" )
    String description;
    @Column(name = "state_region" , length=16  )
    @Enumerated(EnumType.STRING)
    StateRegion state;
    @Column(name = "city" )
    String city;
    @Column(name = "zip_code" )
    int zipCode;
    @Column(name = "address" )
    String address;
    @Column(name = "phone" )
    int phone;
    @Column(name = "email" )
    String email;
    @Column(name = "linked_in" )
    String linkedIn;
    @Column(name = "website" )
    String website;
    @Column(name = "activity_area", length=64  )
    @Enumerated(EnumType.STRING)
    ActivityArea ativity;
    @OneToOne(cascade = CascadeType.ALL)
    Attachment logo;
    @OneToOne(cascade = CascadeType.ALL)
    Attachment cover;
    @OneToMany(mappedBy = "campany", cascade = CascadeType.ALL )
    List<RecruitmentOffer> recruitmentOffers = new ArrayList<RecruitmentOffer>();
    public void addRecruitmentOffer(RecruitmentOffer recruitmentOffer) {
        if ( this.getRecruitmentOffers() == null ){this.setRecruitmentOffers(new ArrayList<RecruitmentOffer>());}
        recruitmentOffers.add(recruitmentOffer);
        recruitmentOffer.setCampany(this);
    }
    public void removeRecruitmentOffer(RecruitmentOffer recruitmentOffer) {
        if ( this.getRecruitmentOffers() == null ){ return;}
        recruitmentOffers.remove(recruitmentOffer);
        recruitmentOffer.setCampany(null);
    }
}
