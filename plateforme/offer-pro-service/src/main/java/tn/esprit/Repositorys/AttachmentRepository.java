package tn.esprit.Repositorys;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.Entitys.Attachment;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, String> { }
