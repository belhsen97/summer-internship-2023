package tn.esprit.Repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.Entitys.ApplyOnOffer;
import tn.esprit.Entitys.Company;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    Optional<Company> findCompaniesByUsername(String username);
    Optional<Company> findCompaniesByName(String name);
    Optional<Company> findCompaniesByPhoneAndName( int phone,String name);
}
