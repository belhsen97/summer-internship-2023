package tn.esprit.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.Dtos.CompanyDto;
import tn.esprit.Dtos.AttachmentDto;
import tn.esprit.Dtos.RecruitmentOfferDto;
import tn.esprit.Entitys.Attachment;
import tn.esprit.Entitys.Company;
import tn.esprit.Entitys.RecruitmentOffer;
import tn.esprit.Libs.GenericCRUDController;
import tn.esprit.Libs.GenericCRUDControllerDto;
import tn.esprit.Libs.IGenericMapperDto;
import tn.esprit.Mappers.AttachmentMapper;
import tn.esprit.Services.ICompanyService;

@Controller
@RestController
@RequestMapping("/company")
/*public class CompanyController extends GenericCRUDController<Company,Long> {
    private ICompanyService iCompanyService;
    private IGenericMapperDto<CompanyDto,Company >  companyMapper;

    @Autowired
    public CompanyController(@Qualifier("company-service") ICompanyService iCompanyService,
                             IGenericMapperDto<CompanyDto,Company >   companyMapper)
    { super(iCompanyService);
        this.iCompanyService = iCompanyService;this.companyMapper = companyMapper;}

    @PutMapping("add-offer/{idCompany}")
    public  RecruitmentOffer addOfferCompany(@PathVariable("idCompany") Long idCompany,@RequestBody RecruitmentOffer object){
      return    iCompanyService.addOfferCompany(idCompany, object)  ;
    }

    @PutMapping ("/upload-Logo/{idCompany}")
    public AttachmentDto uploadLogoToCompany(@RequestParam("file")MultipartFile file, @PathVariable("idCompany")   Long idCompany) throws Exception {
        Attachment attachment = iCompanyService.saveLogoToCompany(file,idCompany);
        return  AttachmentMapper.mapToDto( attachment  );
    }
}*/

public class CompanyController extends GenericCRUDControllerDto<CompanyDto,Company,Long> {
    private final ICompanyService iCompanyService;
    private final IGenericMapperDto<CompanyDto,Company >  companyMapper;
    private final IGenericMapperDto<RecruitmentOfferDto,RecruitmentOffer >  recruitmentOfferMapper;

    @Autowired
    public CompanyController(@Qualifier("company-service") ICompanyService iCompanyService,
                             IGenericMapperDto<CompanyDto,Company >   companyMapper,
                             IGenericMapperDto<RecruitmentOfferDto,RecruitmentOffer >  recruitmentOfferMapper)
    { super(iCompanyService,companyMapper);
        this.iCompanyService = iCompanyService;
        this.companyMapper = companyMapper;
        this.recruitmentOfferMapper = recruitmentOfferMapper;}

    @PostMapping("create")
    public CompanyDto createComapany( @RequestBody CompanyDto companyDto) {
        Company company = iCompanyService.createComapany(companyMapper.mapToEntity(companyDto));
        return companyMapper.mapToDto( company , CompanyDto.class);}

    @GetMapping("username/{username}")
    public  CompanyDto getByUsername(@PathVariable("username") String username){
        return  companyMapper.mapToDto(iCompanyService.selectByUsername(username) , CompanyDto.class)  ;
    }
    @GetMapping("name/{name}")
    public  CompanyDto getByName(@PathVariable("name") String name){
        return  companyMapper.mapToDto(iCompanyService.selectByName(name) , CompanyDto.class)  ;
    }
    @PutMapping ("/upload-logo/{idCompany}")
    public AttachmentDto uploadLogoToCompany(@RequestParam("file")MultipartFile file, @PathVariable("idCompany")   Long idCompany) throws Exception {
        Attachment attachment = iCompanyService.saveLogoToCompany(file,idCompany);
        return  AttachmentMapper.mapToDto( attachment  );
    }
    @PutMapping ("/upload-cover/{idCompany}")
    public AttachmentDto uploadCoverToCompany(@RequestParam("file")MultipartFile file, @PathVariable("idCompany")   Long idCompany) throws Exception {
        Attachment attachment = iCompanyService.saveCoverToCompany(file,idCompany);
        return  AttachmentMapper.mapToDto( attachment  );
    }


    @PutMapping("add-offer/{idCompany}")
    public  RecruitmentOfferDto addOfferCompany(@PathVariable("idCompany") Long idCompany,@RequestBody RecruitmentOfferDto object){
        final RecruitmentOffer recruitmentOffer =  recruitmentOfferMapper.mapToEntity(object);
        return recruitmentOfferMapper.mapToDto(   iCompanyService.addOfferCompany(idCompany, recruitmentOffer) , RecruitmentOfferDto.class );
    }
}