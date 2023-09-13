package tn.esprit.Repositorys;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.Entitys.Answer;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, String> { }