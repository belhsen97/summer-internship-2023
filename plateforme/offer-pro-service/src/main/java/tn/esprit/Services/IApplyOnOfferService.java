package tn.esprit.Services;


import tn.esprit.Entitys.ApplyOnOffer;
import tn.esprit.Entitys.StatusApply;
import tn.esprit.Libs.IGenericCRUD;

import java.util.List;

public interface IApplyOnOfferService extends IGenericCRUD<ApplyOnOffer,Long> {
    ApplyOnOffer updateStatusApply(String username, Long idApplyOnOffer, StatusApply statusApply);
     ApplyOnOffer addApplyOnOfferByRecruitmentOfferAndAccount(ApplyOnOffer object , Long idRecruitmentOffer  );
    List<ApplyOnOffer> selectByAccountId(String username );
    void deleteByAccountIdAndIdRecruitmentOffer( String username,Long RecruitmentOfferId);
}