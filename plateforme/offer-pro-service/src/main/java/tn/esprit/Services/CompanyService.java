package tn.esprit.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.Entitys.*;
import tn.esprit.Exceptions.MultiRessourceFoundException;
import tn.esprit.Exceptions.RessourceFoundException;
import tn.esprit.Exceptions.RessourceNotFoundException;
import tn.esprit.Libs.GenericCRUDService;
import tn.esprit.Repositorys.CompanyRepository;
import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service("company-service")
public class CompanyService extends GenericCRUDService<Company,Long> implements ICompanyService {
    private CompanyRepository companyRepository;
    private IFileService ifileService;
    private IAttachmentService iAttachmentService;
    @Autowired
    public CompanyService(CompanyRepository companyRepository ,
                          IFileService ifileService,
                          IAttachmentService iAttachmentService )
       {super(companyRepository);
        this.companyRepository = companyRepository;
        this.ifileService = ifileService;
        this.iAttachmentService = iAttachmentService;}
    @Transactional
    public Company insert(Company object) {
        MultipartFile multipartFile = null;
        Attachment logo  = null;
        try {
            multipartFile = ifileService.importFileToMultipartFile(FileService.defaultLogoCompanyPhoto);
        } catch (IOException e) {
            throw new tn.esprit.Exceptions.IOException(e.getMessage());

        }
        try {
            logo = iAttachmentService.saveAttachment(multipartFile);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
       object.setLogo(logo);
        return  companyRepository.save(object);
    }
    public Company update(Long id,  Company object) {
        Company company = companyRepository.findById(id).
                orElseThrow(()-> new RessourceNotFoundException("Service Company : update Company not existe with id : "+ id))  ;
        company.setName(object.getName());
        company.setDescription(object.getDescription());
        company.setPhone(object.getPhone());
        company.setEmail(object.getEmail());
        company.setState(object.getState());
        company.setCity(object.getCity());
        company.setZipCode(object.getZipCode());
        company.setAddress(object.getAddress());
        company.setAtivity(object.getAtivity());
        company.setLinkedIn(object.getLinkedIn());
        company.setWebsite(object.getWebsite());
        company = companyRepository.save(company);
        return company ;
    }

    @Override
    @Transactional
    public Company createComapany(Company company) {
        Optional<Company> companyOptional = companyRepository.findCompaniesByUsername(company.getUsername());
        if ( companyOptional.isPresent() ) { throw new MultiRessourceFoundException( "We found other Company has been created"); }
        companyOptional = companyRepository.findCompaniesByPhoneAndName(company.getPhone(),company.getName());
        if ( companyOptional.isPresent() ) { throw new RessourceFoundException( "We found other Company has been created"); }
        return this.insert(company);}

    @Override
    public Company  selectByUsername(String username) {

        return  companyRepository.findCompaniesByUsername( username).orElseThrow(()-> new RessourceNotFoundException("Cannot found Company by username"));
    }

    @Override
    public Company selectByName(String name) {
        return  companyRepository.findCompaniesByName( name).orElseThrow(()-> new RessourceNotFoundException("Cannot found Company by name"));}


    @Override
    public Attachment saveLogoToCompany(MultipartFile file, long idCompany ) throws Exception  {
        return iAttachmentService.saveLogoToCompany( file,  idCompany );  }
    @Override
    public Attachment saveCoverToCompany(MultipartFile file, long idCompany) throws Exception {
        return iAttachmentService.saveCoverToCompany( file,  idCompany );}

    @Override
    @Transactional
    public RecruitmentOffer addOfferCompany(Long idCompany, RecruitmentOffer object) {
        Company company = companyRepository.findById(idCompany).
                orElseThrow(()-> new RessourceNotFoundException("Service Company :  Offer Company  not existe with id Company : "+idCompany))  ;
       // object.getForms().forEach((form) -> { object.addForm(form);  } );
        object.setStartDateTime(LocalDateTime.now());
        object.setStatusOffer(StatusOffer.PUBLISHED)  ;
        object.addMultiForm(object.getForms());
        company.addRecruitmentOffer(object);
        company = companyRepository.save(company);
        return this.searchRecruitmentOfferViaId( company.getRecruitmentOffers()) ;  }



    public RecruitmentOffer searchRecruitmentOfferViaId(List<RecruitmentOffer> listRecruitmentOffers) {
        if (listRecruitmentOffers == null || listRecruitmentOffers.isEmpty()) {
            return null; // Return null if the list is empty or null
        }
        RecruitmentOffer maxIdRecruitmentOffer = listRecruitmentOffers.get(0); // Initialize with the first element
        for (RecruitmentOffer offer : listRecruitmentOffers) {
            if (offer.getId() > maxIdRecruitmentOffer.getId()) {
                maxIdRecruitmentOffer = offer; // Update if a higher ID is found
            }
        }
        return maxIdRecruitmentOffer;
    }

}


