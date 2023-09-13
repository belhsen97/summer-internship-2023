package tn.esprit.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.Entitys.*;
import tn.esprit.Exceptions.RessourceNotFoundException;
import tn.esprit.Libs.GenericCRUDService;
import tn.esprit.Repositorys.RecruitmentOfferRepository;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDateTime;


@Service("recruitment-offer-service")
public class RecruitmentOfferService  extends GenericCRUDService<RecruitmentOffer,Long> implements IRecruitmentOfferService {
    private RecruitmentOfferRepository recruitmentOfferRepository;
    private ICompanyService iCompanyService;
    @Autowired
    public RecruitmentOfferService(RecruitmentOfferRepository recruitmentOfferRepository
   ,ICompanyService iCompanyService )
    {super(recruitmentOfferRepository);  this.recruitmentOfferRepository = recruitmentOfferRepository;
        this.iCompanyService = iCompanyService;}
    @Transactional
    public RecruitmentOffer insert(RecruitmentOffer object) {
        object.addMultiForm(object.getForms());//object.getForms().forEach((form) -> { object.addForm(form);  } );
        return  recruitmentOfferRepository.save(object);  }
    public RecruitmentOffer update(Long id, RecruitmentOffer object) {
        RecruitmentOffer recruitmentOffer = recruitmentOfferRepository.findById(id).
                orElseThrow(()-> new RessourceNotFoundException("Service RecruitmentOffer : update RecruitmentOffer not existe with id : "+id))  ;
        recruitmentOffer.setTypeOffer(object.getTypeOffer());
        recruitmentOffer.setDescription(object.getDescription());
        recruitmentOffer.setRequirements(object.getRequirements());
        recruitmentOffer.setTitle(object.getTitle());
        recruitmentOffer.setVacantJobs(object.getVacantJobs());
        recruitmentOffer.setContractType(object.getContractType());
        recruitmentOffer.setGender(object.getGender());
        recruitmentOffer.setLangue(object.getLangue());
        recruitmentOffer.setExperienceFrom(object.getExperienceFrom());
        recruitmentOffer.setExperienceTo(object.getExperienceTo());
        recruitmentOffer.setStartDateTime(object.getStartDateTime());
        recruitmentOffer.setEndDateTime(object.getEndDateTime());
        recruitmentOffer.setStatusOffer(object.getStatusOffer());
        recruitmentOffer.setStudyLevel(object.getStudyLevel());
        recruitmentOffer = recruitmentOfferRepository.save(recruitmentOffer);
        return recruitmentOffer ;
    }
    @Override
    public RecruitmentOffer selectByIdAndtitle(long id, String title){
        return recruitmentOfferRepository.findRecruitmentOfferByIdAndTitle(id,title).
                orElseThrow(()-> new RessourceNotFoundException("Service RecruitmentOffer :  Recruitment Offer not existe with id Company : "+id +" and title :"+title)) ;
    }
}
