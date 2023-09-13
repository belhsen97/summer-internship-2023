package tn.esprit.Configures;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tn.esprit.Mappers.AttachmentMapper;

@Configuration
public class MyConfigInitParameters {
    @Value("${myApp.link.WebPage}")
    private String pageHomeWebPage;
    @Value("${myApp.link.Path.AttachementDownload}")
    private String pathAttachementDownload;
    @Value("${myApp.link.GlobalBackEnd}")
    private String linkGlobalBackEnd;
    @Value("${server.servlet.context-path}")
    private String pathServiceMail;
    public static String staticLinkServiceMail;


    @Bean
    public String configure() {
        AttachmentMapper.host_ContextPath = linkGlobalBackEnd + pathServiceMail + pathAttachementDownload;
        //http://localhost:8099/management-offers/mail-service/attachment/download/
        MyConfigInitParameters.staticLinkServiceMail = linkGlobalBackEnd + pathServiceMail;
        return "configured";
    }
}