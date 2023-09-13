package tn.esprit.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.Libs.IObjectMapperConvert;
import tn.esprit.Mappers.AttachmentMapper;
import tn.esprit.Models.Attachment;
import tn.esprit.Models.Msg;
import tn.esprit.Services.IsmtpMailService;

import javax.mail.MessagingException;
import java.util.ArrayList;


@RestController
@RequestMapping("/smtp")
public class MailController {
    @Autowired
    private IsmtpMailService mailSender;
    @Autowired
    private IObjectMapperConvert iObjectMapperConvert;

    @PostMapping("/simple")
    public ResponseEntity<String> simpleMail(@RequestBody Msg msg){
         try {
            mailSender.connect();
            mailSender.sendingSimple(msg);
            } catch (MessagingException e) {
              throw new RuntimeException(e);
            }
        return ResponseEntity.ok( "sending OK");
    }
    @PostMapping("/html")
    public ResponseEntity<String> htmlMail(@RequestBody  Msg msg){
        try {
            mailSender.connect();
            mailSender.sendingWithViewHTML(msg);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok( "sending OK");
    }
    @PostMapping("/mail-file-stream")
    public ResponseEntity<String> mailFileStream(@RequestParam("files") MultipartFile[] files, @RequestParam("msg")  String msgStr) throws JsonProcessingException ,MessagingException,Exception {
          Msg msg = (Msg) iObjectMapperConvert.convertToObject ( msgStr , Msg.class );
        System.out.println(msg.getEmail());
          msg.setAttachements(new ArrayList<Attachment>());
        for ( MultipartFile file : files )
            { msg.getAttachements().add( AttachmentMapper.mapToEntity(file) );   }
            mailSender.connect();
            mailSender.sendingWithStreamDocuments(msg);
        return ResponseEntity.ok( "sending OK");
    }
}
