package tn.esprit.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.Entitys.Account;
import tn.esprit.Entitys.Attachment;
import tn.esprit.Entitys.Category;
import tn.esprit.Exceptions.RessourceNotFoundException;
import tn.esprit.Libs.GenericCRUDService;
import tn.esprit.Repositorys.AccountRepository;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDateTime;


@Service("account-service")
public class AccountService extends GenericCRUDService<Account,Long> implements IAccountService {
    private AccountRepository accountRepository;
    private IFileService ifileService;
    private IAttachmentService iAttachmentService;
    @Autowired
    public AccountService(AccountRepository accountRepository ,
                          IFileService ifileService,
                          IAttachmentService iAttachmentService )
       {super(accountRepository);
        this.accountRepository = accountRepository;
        this.ifileService = ifileService;
        this.iAttachmentService = iAttachmentService;}
    @Transactional
    public Account insert(Account object) {
        object.setCreatedAt(   LocalDateTime.now() );
        MultipartFile multipartFile = null;
        Attachment attachment  = null;
        try {
            multipartFile = ifileService.importFileToMultipartFile(FileService.defaultUserPhoto);
        } catch (IOException e) {
            throw new tn.esprit.Exceptions.IOException(e.getMessage());

        }
        try {
            attachment = iAttachmentService.saveAttachment(multipartFile);
            attachment.setCategory(Category.PHOTOPROFILE);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
       object.addAttachment(attachment);
        object.addUser(object.getUser());
        return  accountRepository.save(object);
    }
    public Account update(Long id,  Account object) {
        Account account = accountRepository.findById(id).
                orElseThrow(()-> new RessourceNotFoundException("Service Account : update Account not existe with id : "+id))  ;
        account.setFirstname(object.getFirstname());
        account.setLastname(object.getLastname());
        account.setCin(object.getCin());
        account.setDateOfBirth(object.getDateOfBirth());
        account.setPhone(object.getPhone());
        account.setEmail(object.getEmail());
        account.setLinkedIn(object.getLinkedIn());
        account.setGithub(object.getGithub());
        account.setGender(object.getGender());
        account.setState(object.getState());
        account.setCity(object.getCity());
        account.setZipCode(object.getZipCode());
        account.setAddress(object.getAddress());
        account = accountRepository.save(account);
        return account ;
    }
    @Override
    public Account selectbyUsername(String Username) {
        return accountRepository.findAccountByUsername(Username).
                orElseThrow(()-> new RessourceNotFoundException("Service Account ( selectbyUsername )  : cannot found account  : "+Username));
    }
}


