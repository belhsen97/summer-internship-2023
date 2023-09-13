package tn.esprit.Configures;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tn.esprit.Mappers.AttachmentMapper;
import tn.esprit.Services.FileService;

@Configuration
public class MyConfigInitParameters {
    @Value("${myApp.file.defaultLogoCompanyPhoto}")
    private String file_defaultLogoCompanyPhoto;
    @Value("${myApp.link.WebPage}")
    private String pageHomeWebPage;
    @Value("${myApp.link.Path.AttachementDowload}")
    private String pathAttachementDowload;
    @Value("${myApp.link.GlobalBackEnd}")
    private String linkGlobalBackEnd;
    @Value("${server.servlet.context-path}")
    private String pathServiceUser;
    @Value("${myApp.separator.FieldAnswer}")
    public static String separatorFieldAnswer;
    public static String staticLinkServiceUser;

    @Bean
    public String configure() {
        AttachmentMapper.host_ContextPath = linkGlobalBackEnd + pathServiceUser +pathAttachementDowload;
        //http://localhost:8099/biochar/user-service/attachment/download/
        FileService.defaultLogoCompanyPhoto = file_defaultLogoCompanyPhoto;
        FileService.pageHomeLink = pageHomeWebPage;
        MyConfigInitParameters.staticLinkServiceUser = linkGlobalBackEnd + pathServiceUser;
        return "configured";
    }

}