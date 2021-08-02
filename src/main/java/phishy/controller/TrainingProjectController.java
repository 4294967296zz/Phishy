package phishy.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import phishy.service.MailformService;
import phishy.service.TrainingProjectService;
import phishy.service.TrainingUserService;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@AllArgsConstructor
public class TrainingProjectController {
    private TrainingProjectService trainingProjectService;
    private MailformService mailformService;
    private TrainingUserService trainingUserService;

    @RequestMapping(value = "/registerTRP.do", method = RequestMethod.POST)
    public @ResponseBody
    Object registerTPR(
            @RequestParam("mfi_id") Long mfi_id,
            @RequestParam("tug_id") Long tug_id,
            @RequestParam("trp_nm") String trp_nm,
            @RequestParam("trp_content") String trp_content,
            @RequestParam("trp_start") Date trp_start,
            @RequestParam("trp_end") Date trp_end,
            @RequestParam("trp_interval") String trp_interval,
            @RequestParam("trs_open") String trs_open,
            @RequestParam("trs_link") String trs_link,
            @RequestParam("trs_attach_click") String trs_attach_click,
            @RequestParam("trs_attach_open") String trs_attach_open,
            @RequestParam("trs_attach_nm") String trs_attach_nm,
            @RequestParam("trs_attach_size") String trs_attach_size,
            @RequestParam("trs_attach_type") String trs_attach_type,
            @RequestParam("trs_phishing") String trs_phishing,
            @RequestParam("trs_phishing_url") String trs_phishing_url,
            @RequestParam("trs_phishing_content") String trs_phishing_content) {

        Map<String, String> datas = new HashMap<String, String>();
        datas.put("trp_nm", trp_nm);
        datas.put("trp_content", trp_content);
        datas.put("trp_interval",trp_interval);
        datas.put("trs_open",trs_open);
        datas.put("trs_link",trs_link);
        datas.put("trs_attach_click",trs_attach_click);
        datas.put("trs_attach_open",trs_attach_open);
        datas.put("trs_attach_nm",trs_attach_nm);
        datas.put("trs_attach_size",trs_attach_size);
        datas.put("trs_attach_type",trs_attach_type);
        datas.put("trs_phishing",trs_phishing);
        datas.put("trs_phishing_url",trs_phishing_url);
        datas.put("trs_phishing_content",trs_phishing_content);
        datas.put("mfi_file_nm", mailformService.getMailform(mfi_id).getMfi_file_nm());
        datas.put("mfi_mail_nm", mailformService.getMailform(mfi_id).getMfi_mail_nm());
        datas.put("mfi_mail_addr", mailformService.getMailform(mfi_id).getMfi_mail_addr());
        datas.put("mfi_mail_title", mailformService.getMailform(mfi_id).getMfi_mail_title());

        trainingProjectService.registerTRP(datas,tug_id,mfi_id,trp_start,trp_end);

        return datas;
    }

    @RequestMapping(value = "/getTRPs.do", method = RequestMethod.POST)
    public @ResponseBody Object getTRPdetails() {
        Map<String, Object> mp = new HashMap<String, Object>();
        mp.put("trp_datas", trainingProjectService.getTRPs());
        mp.put("trs_datas", trainingProjectService.getTRSs());
        Object result = mp;
        return result;
    }


}
