package tn.esprit.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.Entitys.Form;
import tn.esprit.Entitys.RecruitmentOffer;
import tn.esprit.Exceptions.RessourceNotFoundException;
import tn.esprit.Libs.GenericCRUDService;
import tn.esprit.Repositorys.FormRepository;
import tn.esprit.Repositorys.RecruitmentOfferRepository;

import javax.transaction.Transactional;

@Service("form-service")
public class FormService  extends GenericCRUDService<Form,Long> implements IFormService {
    private FormRepository formRepository;
    @Autowired
    public FormService(FormRepository formRepository  )
    {super(formRepository);  this.formRepository = formRepository; }
    public Form update(Long id, Form object) {
        Form form = formRepository.findById(id).
                orElseThrow(()-> new RessourceNotFoundException("Service Form : update Form not existe with id : "+id))  ;
        form.setDescription(object.getDescription());
        form.setContent(object.getContent());
        form.setType(object.getType());
        form.setRequired(object.isRequired());
        return form ;
    }
}