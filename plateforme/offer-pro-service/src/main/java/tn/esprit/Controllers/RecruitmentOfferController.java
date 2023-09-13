package tn.esprit.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.Dtos.CompanyDto;
import tn.esprit.Dtos.RecruitmentOfferDto;
import tn.esprit.Entitys.RecruitmentOffer;
import tn.esprit.Libs.GenericCRUDController;
import tn.esprit.Libs.GenericCRUDControllerDto;
import tn.esprit.Libs.IGenericMapperDto;
import tn.esprit.Services.IRecruitmentOfferService;

@Controller
@RestController
@RequestMapping("/recruitment-offer")
/*public class RecruitmentOfferController extends GenericCRUDController<RecruitmentOffer,Long> {
    private IRecruitmentOfferService iRecruitmentOfferService;
    @Autowired
    public RecruitmentOfferController(@Qualifier("recruitment-offer-service") IRecruitmentOfferService iRecruitmentOfferService)
    { super(iRecruitmentOfferService);  this.iRecruitmentOfferService = iRecruitmentOfferService;}
}*/
public class RecruitmentOfferController extends GenericCRUDControllerDto<RecruitmentOfferDto, RecruitmentOffer,Long> {
    private IRecruitmentOfferService iRecruitmentOfferService;
    private IGenericMapperDto<RecruitmentOfferDto, RecruitmentOffer> recruitmentOfferMapper;

    @Autowired
    public RecruitmentOfferController(@Qualifier("recruitment-offer-service") IRecruitmentOfferService iRecruitmentOfferService,
                             IGenericMapperDto<RecruitmentOfferDto, RecruitmentOffer> recruitmentOfferMapper) {
        super(iRecruitmentOfferService, recruitmentOfferMapper);
        this.iRecruitmentOfferService = iRecruitmentOfferService;
        this.recruitmentOfferMapper = recruitmentOfferMapper;
    }

    @GetMapping("{id}/title/{title}")
    public RecruitmentOfferDto getByIdAndtitle(@PathVariable("id") long id,@PathVariable("title") String title){
        return   recruitmentOfferMapper.mapToDto(iRecruitmentOfferService.selectByIdAndtitle(id,title) , RecruitmentOfferDto.class)  ;
    }
}