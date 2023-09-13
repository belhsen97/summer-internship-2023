package tn.esprit.Services;

import tn.esprit.Entitys.RecruitmentOffer;
import tn.esprit.Libs.IGenericCRUD;

public interface IRecruitmentOfferService extends IGenericCRUD<RecruitmentOffer,Long> {
    RecruitmentOffer selectByIdAndtitle(long id, String title);
}

