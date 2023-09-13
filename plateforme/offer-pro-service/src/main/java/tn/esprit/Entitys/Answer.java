package tn.esprit.Entitys;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
//id  fileName fileType  fileSize data
@Entity
@Table(name ="answer")
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Answer {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    String id;
    @Column(name = "file_name" )
    String fileName;
    @Column(name = "file_type" )
    String fileType;
    @Column(name = "file_size" )
    long fileSize;
    @Lob
    @Column(name = "data" )
    byte[] data;
    @OneToOne()
    Form form;
    @ManyToOne
    ApplyOnOffer applyOnOffer;
}
