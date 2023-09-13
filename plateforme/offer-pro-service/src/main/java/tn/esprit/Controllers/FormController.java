package tn.esprit.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.Entitys.Form;
import tn.esprit.Libs.GenericCRUDController;
import tn.esprit.Services.IFormService;

@Controller
@RestController
@RequestMapping("/form")
public class FormController extends GenericCRUDController<Form,Long> {
    private IFormService iFormService;

    @Autowired
    public FormController(@Qualifier("form-service") IFormService iFormService)
    { super(iFormService);  this.iFormService = iFormService;}
}
