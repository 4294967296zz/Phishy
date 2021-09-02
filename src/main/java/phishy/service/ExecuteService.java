package phishy.service;

import lombok.AllArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;
import phishy.domain.Entity.TrainingProjectEntity;
import phishy.dto.TrainingProjectDto;

import javax.mail.*;
import javax.mail.internet.*;
import javax.transaction.Transactional;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;

@Service
@AllArgsConstructor
public class ExecuteService {

    private TrainingProjectService trainingProjectService;
    private TrainingResultService trainingResultService;
    private MailLogService mailLogService;
    private UserService userService;

    @Transactional
    public void sendMail(Map<String, String> data, List<String> email_list, Long trpId, Long trsId) throws IOException, MessagingException, InterruptedException {
        // 훈련 실행 즉시 상태 update
        Integer trpSent = trainingProjectService.updateTRP(trpId, "진행중");

        Properties properties = new Properties();
        properties.load(new ByteArrayInputStream(Files.readAllBytes(Paths.get(String.valueOf(data.get("property"))))));
        Session session = Session.getDefaultInstance(properties);

        // 발신자 이름 인코딩
        String senderNm = MimeUtility.encodeText(MimeUtility.encodeText(data.get("mail_sender_nm"), "EUC-KR", "B"), "UTF-8", "B");

        Iterator mailList = email_list.iterator();
        String rcpt = "";

        while(mailList.hasNext()) {
            // 수신자 이메일주소 string 처리
            rcpt = String.valueOf(mailList.next());
            // MIME 세션 생성
            MimeMessage message = new MimeMessage(session);
            // 발신자 이메일주소, 이름 설정
            Address[] from = new Address[]{new InternetAddress(senderNm, data.get("mail_sender_addr"))};
            message.addFrom(from);
            //수신자 설정
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(rcpt));
            // 참조 수신자 설정
//            message.addRecipients(Message.RecipientType.CC, "alex@marvelsystem.co.kr");
            // 숨은 참조 수신자 설정
//            message.addRecipients(Message.RecipientType.BCC, getAddresses("nowonbun@gmail.com"));
            // 제목 설정
            message.setSubject(data.get("mail_title"));
            // 훈련 결과 insert
            Long trrId = trainingResultService.registerTRR(
                    userService.getUserByEmail(rcpt).getUserNm(),
                    userService.getUserByEmail(rcpt).getUserRank(),
                    rcpt, trpId, trsId, trpSent
            );
            String openCheck = "<img alt=\"본문\" title=\"본문\" src= \"http://localhost:8080/checkMail.do?trr="+trrId+"/\"style=\"display:none;outline:none;text-decoration:none;background:#ffffff\" class=\"CToWUd\">";
            String mail_content = data.get("mail_content").replace("{#button_link}", "http://localhost:8080/linkClicked.do?tr="+trrId);
            // 본문 설정
            Multipart multipart = new MimeMultipart();
            BodyPart htmlBodyPart = new MimeBodyPart();
            htmlBodyPart.setContent(openCheck+mail_content, "text/html; charset=utf-8");
            multipart.addBodyPart(htmlBodyPart);
            message.setContent(multipart);
            // 메일 발송
            Transport.send(message);
            // 인터벌 설정
            Thread.sleep(Integer.parseInt(data.get("interval"))*1000);
            // 메일 로그 insert
            mailLogService.registerMailLog(trpId, trpId);
            // 수신자 이메일 주소 초기화
            rcpt = null;
        }
    }


}


