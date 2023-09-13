package tn.esprit.Mappers;


import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.Dtos.AttachmentDto;
import tn.esprit.Models.Attachment;

public class AttachmentMapper {
    public static String host_ContextPath = "";
    public static AttachmentDto mapToDto(Attachment attachment  ){
        String downloadURl = AttachmentMapper.host_ContextPath +attachment.getId();
        return AttachmentDto.builder()
                .id(attachment.getId())
                .fileName(attachment.getFileName())
                .downloadURL(downloadURl)
                .fileType(attachment.getFileType())
                .fileSize(attachment.getFileSize())
                .build();
    }
    public static Attachment  mapToEntity(MultipartFile file) throws Exception {
        Attachment attachment = new Attachment();
        String fileName = "";
        try {
            fileName = StringUtils.cleanPath(file.getOriginalFilename());
            if(fileName.contains("..")) {throw  new Exception("Filename contains invalid path sequence "+ fileName);}
            attachment = Attachment.builder()
                    .fileName(fileName)
                    .fileType(file.getContentType())
                    .fileSize(file.getSize())
                    .data(file.getBytes()).build();
            return   attachment ;
        } catch (Exception e) {
            throw new Exception("Could not save File: " + fileName);
        }
        //return attachment;
    }
}
