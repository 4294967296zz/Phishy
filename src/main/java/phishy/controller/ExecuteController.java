package phishy.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import phishy.service.ExecuteService;
import phishy.service.TrainingProjectService;
import phishy.service.TrainingUserService;

import javax.mail.MessagingException;
import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Controller
@AllArgsConstructor
public class ExecuteController {

    private TrainingProjectService trainingProjectService;
    private TrainingUserService trainingUserService;
    private ExecuteService executeService;

    @RequestMapping(value = "/executeMail.do", method = RequestMethod.POST)
    public @ResponseBody
    Object executeMail(@RequestParam("trpId") Long trpId,
                       @RequestParam("trsId") Long trsId,
                       @RequestParam("tugId") Long tugId) {
        Map<String, Object> datas = new HashMap<String, Object>();
        datas.put("trp_data", trainingProjectService.getTRP(trpId));
        datas.put("trs_data", trainingProjectService.getTRS(trsId));
        datas.put("tug_data", trainingUserService.getTUG(tugId));
        Object result = datas;
        return result;
    }


    @RequestMapping(value = "/sendMails.do", method = RequestMethod.POST)
    public @ResponseBody
    void sendMails(@RequestParam("trpId") Long trpId,
                   @RequestParam("trsId") Long trsId,
                   @RequestParam("tugId") Long tugId) throws IOException, MessagingException {


        StringBuilder contentBuilder = new StringBuilder();
        try {
            BufferedReader in = new BufferedReader(new FileReader(trainingProjectService.getTRS(trsId).getMfi_file_nm()));
            String str;
            while ((str = in.readLine()) != null) {
                contentBuilder.append(str);
            }
            in.close();
        } catch (IOException e) {
        }
        String content = contentBuilder.toString();

        Map<String, String> mp = new HashMap<String, String>();
        String rootPath = FileSystemView.getFileSystemView().getHomeDirectory().toString();
        mp.put("mail_title", trainingProjectService.getTRS(trsId).getMfi_mail_title());
        mp.put("mail_sender_nm", trainingProjectService.getTRS(trsId).getMfi_mail_nm());
        mp.put("mail_sender_addr", trainingProjectService.getTRS(trsId).getMfi_mail_addr());
        mp.put("mail_content", content);
        mp.put("attach_nm", trainingProjectService.getTRS(trsId).getTrsAttachNm());
        mp.put("property", rootPath + "/spam.properties");

        executeService.sendMail(mp, trainingUserService.getTUIemail(tugId), trpId);
        trainingProjectService.updateTRP(trpId, "완료");
    }

    @RequestMapping(value = "/thisisjihjo", method = RequestMethod.GET)
    public @ResponseBody
    String thisisjiho(@RequestParam("test") String test) {

        //파일의 생성
        //파일은 이시점에 생성되는 것이 아니다
        File newFile = new File(FileSystemView.getFileSystemView().getHomeDirectory().toString());
        try {
          if(newFile.createNewFile()){    //파일이 생성되는 시점
            return"파일이 생성되었습니다.";
          }else {
            return"파일을 생성하지 못했습니다.";
          }
        } catch (IOException e) {
          e.printStackTrace();
          return"예외 처리 파일을 생성하는 과정에서 오류가 발생했습니다.";
        }


    }


}

