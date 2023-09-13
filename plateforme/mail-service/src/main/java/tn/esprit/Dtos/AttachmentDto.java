package tn.esprit.Dtos;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AttachmentDto {
     String id;
     String fileName;
     String downloadURL;
     String fileType;
     long fileSize;
}
