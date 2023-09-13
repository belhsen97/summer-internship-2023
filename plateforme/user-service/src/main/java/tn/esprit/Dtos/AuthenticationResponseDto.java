package tn.esprit.Dtos;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalTime;


@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)

public class AuthenticationResponseDto extends MsgReponseStatusDto {
   @Builder(builderMethodName = "childBuilder")
   public AuthenticationResponseDto(String title,
                                    LocalDate datestamp,
                                    LocalTime timestamp,
                                    ReponseStatus status,
                                    String message,
                                    String token) {
      super(title ,datestamp , timestamp,  status , message);
      this.token = token;
   }
   String token;
}
