package tn.esprit.Dtos;

import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.Entitys.Category;

import java.time.LocalDateTime;
import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AttachementDto {
     String id;
     String fileName;
     String downloadURL;
     String fileType;
     long fileSize;
     Category category;
     LocalDateTime addAt ;
}
