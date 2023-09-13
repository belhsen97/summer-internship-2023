package tn.esprit.Entitys;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

//id field_title  field_content  field_type   field_required
@Entity
@Table(name ="form")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Form {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" )
    long id;
    @Column(name = "field_description", columnDefinition = "TEXT" )
    String description;
    @Column(name = "field_content" , columnDefinition = "TEXT" )
    String content;
    @Column(name = "field_type" , length=16  )
    @Enumerated(EnumType.STRING)
    FieldType type;
    @Column(name = "field_required" )
    boolean required;
    @ManyToOne
    @JsonIgnore
    RecruitmentOffer recruitmentOffer;
}
