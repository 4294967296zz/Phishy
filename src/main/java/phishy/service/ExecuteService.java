package phishy.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.*;
import javax.transaction.Transactional;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@Service
@AllArgsConstructor
public class ExecuteService {

    private TrainingProjectService trainingProjectService;

    @Transactional
    public void sendMail(Map<String, String> data, List<String> email_list, Long trpId) throws IOException, MessagingException {
        trainingProjectService.updateTRP(trpId, "진행중");
        
        Properties properties = new Properties();
        properties.load(new ByteArrayInputStream(Files.readAllBytes(Paths.get(String.valueOf(data.get("property"))))));
        Session session = Session.getDefaultInstance(properties);
        String senderNm = MimeUtility.encodeText(MimeUtility.encodeText(data.get("mail_sender_nm"), "EUC-KR", "B"), "UTF-8", "B");
        String senderAddr = MimeUtility.encodeText(MimeUtility.encodeText(data.get("mail_sender_nm"), "EUC-KR", "B"), "UTF-8", "B");

        Iterator mailList = email_list.iterator();

        while(mailList.hasNext()) {

            MimeMessage message = new MimeMessage(session);
            Address[] from = new Address[]{new InternetAddress(senderNm, senderAddr)};
            message.addFrom(from);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(String.valueOf(mailList.next())));
            message.setSubject(data.get("mail_title"));
            Multipart multipart = new MimeMultipart();
            BodyPart htmlBodyPart = new MimeBodyPart();
            htmlBodyPart.setContent("<h1>dfsdfasdfasdf</h1>", "text/html; charset=utf-8");
            multipart.addBodyPart(htmlBodyPart);
            message.setContent(multipart);
            Transport.send(message);
        }
    }


}
