package tn.esprit.Services;


import org.springframework.web.multipart.MultipartFile;
import tn.esprit.Entitys.Attachment;
import tn.esprit.Entitys.Company;
import tn.esprit.Entitys.RecruitmentOffer;
import tn.esprit.Libs.IGenericCRUD;

import java.util.List;

public interface ICompanyService extends IGenericCRUD<Company,Long> {
    Company createComapany(Company company);
    Company  selectByUsername(String username);
    Company  selectByName(String name);
    RecruitmentOffer addOfferCompany(Long idCompany, RecruitmentOffer object);
    Attachment saveLogoToCompany(MultipartFile file, long idCompany ) throws Exception;
    Attachment saveCoverToCompany(MultipartFile file, long idCompany ) throws Exception;
}
