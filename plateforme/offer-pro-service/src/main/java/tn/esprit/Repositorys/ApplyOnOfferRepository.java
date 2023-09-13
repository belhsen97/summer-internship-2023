package tn.esprit.Repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.Entitys.ApplyOnOffer;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApplyOnOfferRepository extends JpaRepository<ApplyOnOffer, Long> {
    Optional<ApplyOnOffer> findApplyOnOfferByIdAndUsername(Long applyOnOfferId, String username);
    List<ApplyOnOffer> findApplyOnOffersByUsername(String username);
    @Query("select case when (count(aoo) > 0)  then true else false end from ApplyOnOffer aoo JOIN aoo.recruitmentOffer ro where  ((aoo.username = :username) and (ro.id = :recruitmentOfferId)  )")
    boolean isCorrectBy(@Param("recruitmentOfferId") Long recruitmentOfferId ,@Param("username") String username);



    @Query("select aoo from ApplyOnOffer aoo JOIN aoo.recruitmentOffer ro where  ((aoo.username = :username) and (ro.id = :recruitmentOfferId)  )")
    List<ApplyOnOffer>  findApplyOnOffersByUsernameAndByRecruitmentId(@Param("username") String username ,@Param("recruitmentOfferId") Long recruitmentOfferId);
}
