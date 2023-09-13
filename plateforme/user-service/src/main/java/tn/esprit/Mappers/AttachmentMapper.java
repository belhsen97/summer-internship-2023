package tn.esprit.Mappers;


import tn.esprit.Dtos.AttachementDto;
import tn.esprit.Entitys.Attachment;

public class AttachmentMapper {
    public static String host_ContextPath = "";
    public static AttachementDto mapToDto(Attachment attachment  ){
        String downloadURl = AttachmentMapper.host_ContextPath +attachment.getId();
        return AttachementDto.builder()
                .id(attachment.getId())
                .fileName(attachment.getFileName())
                .downloadURL(downloadURl)
                .fileType(attachment.getFileType())
                .fileSize(attachment.getFileSize())
                .category(attachment.getCategory())
                .addAt(attachment.getAddAt())
                .build();
    }

}