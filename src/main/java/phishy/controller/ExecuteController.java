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
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
public class ExecuteController {

    private TrainingProjectService trainingProjectService;
    private TrainingUserService trainingUserService;
    private ExecuteService executeService;

    @RequestMapping(value = "/executeMail.do", method = RequestMethod.POST)
    public @ResponseBody Object executeMail(@RequestParam("trpId") Long trpId,
                                                  @RequestParam("trsId") Long trsId,
                                                  @RequestParam("tugId") Long tugId) {
        Map<String, Object> datas = new HashMap<String, Object>();
        datas.put("trp_data", trainingProjectService.getTRP(trpId));
        datas.put("trs_data", trainingProjectService.getTRS(trsId));
        datas.put("tug_data", trainingUserService.getTUG(tugId));
        Object result = datas;
        return result;
    }

    @RequestMapping(value = "/ptest.do", method = RequestMethod.POST)
    public @ResponseBody void ptest(@RequestParam("trpId") Long trpId,
                                    @RequestParam("trsId") Long trsId,
                                    @RequestParam("tugId") Long tugId) throws IOException, MessagingException {
        String rootPath = FileSystemView.getFileSystemView().getHomeDirectory().toString();
//        executeService.sendMail(rootPath+"/spam.properties");
    }

    @RequestMapping(value = "/pptest.do", method = RequestMethod.POST)
    public @ResponseBody void pptest(@RequestParam("trpId") Long trpId,
                                       @RequestParam("trsId") Long trsId,
                                       @RequestParam("tugId") Long tugId) throws IOException, MessagingException{
        Map<String, String> mp = new HashMap<String, String>();
        String rootPath = FileSystemView.getFileSystemView().getHomeDirectory().toString();
        mp.put("mail_title", trainingProjectService.getTRS(trsId).getMfi_mail_title());
        mp.put("mail_sender_nm", trainingProjectService.getTRS(trsId).getMfi_mail_nm());
        mp.put("mail_sender_addr", trainingProjectService.getTRS(trsId).getMfi_mail_addr());
        mp.put("mail_file", trainingProjectService.getTRS(trsId).getMfi_file_nm());
        mp.put("attach_nm", trainingProjectService.getTRS(trsId).getTrsAttachNm());
        mp.put("property", rootPath+"/spam.properties");

        executeService.sendMail(mp, trainingUserService.getTUIemail(tugId));
    }

}
