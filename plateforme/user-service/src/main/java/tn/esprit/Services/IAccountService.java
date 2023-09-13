package tn.esprit.Services;


import tn.esprit.Entitys.Account;
import tn.esprit.Libs.IGenericCRUD;

public interface IAccountService extends IGenericCRUD<Account,Long> {
    Account selectbyUsername(String  Username);
}
