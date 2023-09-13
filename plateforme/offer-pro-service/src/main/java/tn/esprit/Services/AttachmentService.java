package tn.esprit.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.Entitys.Attachment;
import tn.esprit.Entitys.Company;
import tn.esprit.Libs.GenericCRUDService;
import tn.esprit.Repositorys.AttachmentRepository;
import tn.esprit.Repositorys.CompanyRepository;
import tn.esprit.Exceptions.RessourceNotFoundException;

@Service("attachment-service")
public class AttachmentService extends GenericCRUDService<Attachment,String> implements IAttachmentService {
    private AttachmentRepository   attachmentRepository;
    private CompanyRepository companyRepository;
    @Autowired
    public AttachmentService(AttachmentRepository attachmentRepository,
                             CompanyRepository companyRepository  )
    {super(attachmentRepository);
        this.attachmentRepository = attachmentRepository;
        this.companyRepository = companyRepository;}
    @Override
    public Attachment saveAttachment(MultipartFile file) throws Exception {
        String fileName = "";
        try {
            fileName = StringUtils.cleanPath(file.getOriginalFilename());
            if(fileName.contains("..")) {
                throw  new Exception("Filename contains invalid path sequence "
                        + fileName);
            }
            Attachment attachment = Attachment.builder()
                    .fileName(fileName)
                    .fileType(file.getContentType())
                    .fileSize(file.getSize())
                    .data(file.getBytes())
                    .build();
            return attachmentRepository.save(attachment);
        } catch (Exception e) { throw new Exception("Could not save File: " + fileName);   }
    }
    @Override
    @Transactional
    public Attachment saveLogoToCompany(MultipartFile file, long idCompany ) throws Exception  {
        Company company = companyRepository.findById(idCompany).
                orElseThrow(()-> new RessourceNotFoundException("Service Account : delete Account not existe with id : "+idCompany)) ;
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if(fileName.contains("..")) {
                throw  new Exception("Filename contains invalid path sequence "
                        + fileName);
            }
            Attachment logo = Attachment.builder()
                    .fileName(fileName)
                    .fileType(file.getContentType())
                    .fileSize(file.getSize())
                    .data(file.getBytes())
                    .build();
            if ( company.getLogo() != null ){ this.delete(company.getLogo().getId());}
            company.setLogo(logo);
            company =  companyRepository.save(company);return company.getLogo();
        } catch (Exception e) {
            throw new Exception("Could not save File: " + fileName);
        }
    }

    @Override
    public Attachment saveCoverToCompany(MultipartFile file, long idCompany) throws Exception {
        Company company = companyRepository.findById(idCompany).
                orElseThrow(()-> new RessourceNotFoundException("Service Account : delete Account not existe with id : "+idCompany)) ;
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if(fileName.contains("..")) {
                throw  new Exception("Filename contains invalid path sequence "
                        + fileName);
            }
            Attachment logo = Attachment.builder()
                    .fileName(fileName)
                    .fileType(file.getContentType())
                    .fileSize(file.getSize())
                    .data(file.getBytes())
                    .build();
            if ( company.getCover() != null ){ this.delete(company.getCover().getId());}
            company.setCover(logo);
            company =  companyRepository.save(company);return company.getCover();
        } catch (Exception e) {
            throw new Exception("Could not save File: " + fileName);
        }
    }

    @Override
    public Attachment getAttachment(String fileId) throws Exception {
        return attachmentRepository.findById(fileId).orElseThrow( () -> new Exception("File not found with Id: " + fileId));
    }
}
