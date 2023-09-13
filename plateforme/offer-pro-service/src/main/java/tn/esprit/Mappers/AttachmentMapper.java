package tn.esprit.Mappers;


import tn.esprit.Dtos.AttachmentDto;
import tn.esprit.Entitys.Attachment;

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

}