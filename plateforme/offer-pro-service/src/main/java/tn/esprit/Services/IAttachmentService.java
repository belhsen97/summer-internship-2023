package tn.esprit.Services;

import org.springframework.web.multipart.MultipartFile;
import tn.esprit.Entitys.Attachment;
import tn.esprit.Libs.IGenericCRUD;

import java.util.List;

public interface IAttachmentService extends IGenericCRUD<Attachment,String> {
    Attachment saveAttachment(MultipartFile file)throws Exception ;
    Attachment  saveLogoToCompany(MultipartFile file, long idCompany)throws Exception ;
    Attachment  saveCoverToCompany(MultipartFile file, long idCompany)throws Exception ;
    Attachment getAttachment(String fileId) throws Exception;
}