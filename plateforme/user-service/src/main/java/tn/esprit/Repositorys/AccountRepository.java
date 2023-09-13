package tn.esprit.Repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.Entitys.*;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query("select ac from  Account ac JOIN  ac.user u   where  ((u.username = :username)) ")
    Optional<Account>   findAccountByUsername(@Param("username") String username   );

}
