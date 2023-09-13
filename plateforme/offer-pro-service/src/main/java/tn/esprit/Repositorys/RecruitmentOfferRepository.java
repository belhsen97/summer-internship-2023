package tn.esprit.Repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.Entitys.RecruitmentOffer;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecruitmentOfferRepository extends JpaRepository<RecruitmentOffer, Long> {
    Optional<RecruitmentOffer> findRecruitmentOfferByIdAndTitle(long id,String title);
}