package phishy.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Properties;

@Service
@AllArgsConstructor
public class ExecuteService {

//    public void sendEmail(List<SpamEmail> groupSpams, String propsPath) throws IOException, MessagingException {
//        Properties properties = new Properties();
//        properties.load(new ByteArrayInputStream(Files.readAllBytes(Paths.get(propsPath))));
//        Session session = Session.getDefaultInstance(properties);
//        Iterator var5 = groupSpams.iterator();
//
//        while(var5.hasNext()) {
//            SpamEmail groupSpam = (SpamEmail)var5.next();
//            MimeMessage message = new MimeMessage(session);
//            Address[] from = new Address[]{new InternetAddress(groupSpam.getSender(), groupSpam.getSenderName())};
//            message.addFrom(from);
//            message.addRecipient(RecipientType.TO, new InternetAddress(groupSpam.getRecipientTo()));
//            message.setSubject(groupSpam.getSubject());
//            Multipart multipart = new MimeMultipart();
//            BodyPart htmlBodyPart = new MimeBodyPart();
//            htmlBodyPart.setContent(groupSpam.getBody(), "text/html; charset=utf-8");
//            multipart.addBodyPart(htmlBodyPart);
//            Iterator var11 = groupSpam.getInlineImages().iterator();
//
//            while(var11.hasNext()) {
//                Path inlineImage = (Path)var11.next();
//                MimeBodyPart imagePart = new MimeBodyPart();
//                int bsi = inlineImage.toString().lastIndexOf("\\");
//                imagePart.setHeader("Content-ID", inlineImage.toString().substring(bsi + 1));
//                imagePart.setDisposition("inline");
//                imagePart.attachFile(inlineImage.toString());
//                multipart.addBodyPart(imagePart);
//            }
//
//            message.setContent(multipart);
//            System.out.println("sending to..." + groupSpam.getRecipientTo());
//            Transport.send(message);
//        }
//
//    }

}
