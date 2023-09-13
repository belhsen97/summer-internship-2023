package tn.esprit.Repositorys;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.Entitys.Form;

import java.util.List;

@Repository
public interface FormRepository extends JpaRepository<Form, Long> {
}