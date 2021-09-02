package phishy.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import phishy.service.MailformService;
import phishy.service.TrainingGroupService;
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
    private TrainingGroupService trainingGroupService;

    @RequestMapping(value = "/registerTRP.do", method = RequestMethod.POST)
    public @ResponseBody
    Object registerTPR(
            @RequestParam("mfi_id") Long mfi_id,
            @RequestParam("tug_id") Long tug_id,
            @RequestParam("trg_id") Long trg_id,
            @RequestParam("trp_nm") String trp_nm,
            @RequestParam("trp_content") String trp_content,
            @RequestParam("trp_start") Date trp_start,
            @RequestParam("trp_end") Date trp_end,
            @RequestParam("trp_type") String trp_type,
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
        datas.put("trp_type", trp_type);
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
        datas.put("mfi_nm", mailformService.getMailform(mfi_id).getMfi_nm());

        trainingProjectService.registerTRP(datas,tug_id,trg_id,mfi_id,trp_start,trp_end,trainingUserService.getTUGCounts(tug_id).getTugCount());
        trainingGroupService.updateTRGcount(trg_id);

        return datas;
    }

    @RequestMapping(value = "/updateTRP.do", method = RequestMethod.POST)
    public @ResponseBody
    Object updateTPR(
            @RequestParam("trp_id") Long trp_id,
            @RequestParam("trs_id") Long trs_id,
            @RequestParam("mfi_id") Long mfi_id,
            @RequestParam("tug_id") Long tug_id,
            @RequestParam("trg_id") Long trg_id,
            @RequestParam("trp_nm") String trp_nm,
            @RequestParam("trp_content") String trp_content,
            @RequestParam("trp_start") Date trp_start,
            @RequestParam("trp_end") Date trp_end,
            @RequestParam("trp_type") String trp_type,
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
        datas.put("trp_type", trp_type);
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
        datas.put("mfi_nm", mailformService.getMailform(mfi_id).getMfi_nm());

        trainingProjectService.updateTRP(datas,trp_id,trs_id,tug_id,trg_id,mfi_id,trp_start,trp_end,trainingUserService.getTUGCounts(tug_id).getTugCount());
        trainingGroupService.updateTRGcount(trg_id);

        return datas;
    }

    @RequestMapping(value = "/getTRPDatatable.do", method = RequestMethod.POST)
    public @ResponseBody Object getTRPDatatable() {
        Map<String, Object> mp = new HashMap<String, Object>();
        mp.put("data", trainingProjectService.getTRPs());
        Object result = mp;
        return result;
    }

    @RequestMapping(value = "/getTRPsByTrgId.do", method = RequestMethod.POST)
    public @ResponseBody Object getTRPsByTrgId(@RequestParam("trgId") Long trgId) {
        Map<String, Object> mp = new HashMap<String, Object>();
        mp.put("data", trainingProjectService.getTRPsByTrgId(trgId));
        Object result = mp;
        return result;
    }

    @RequestMapping(value = "/getTRPs.do", method = RequestMethod.POST)
    public @ResponseBody Object getTRPdetails() {
        Map<String, Object> mp = new HashMap<String, Object>();
        mp.put("trp_datas", trainingProjectService.getTRPs());
        mp.put("trs_datas", trainingProjectService.getTRSs());
        Object result = mp;
        return result;
    }

    @RequestMapping(value = "/getProjectDetails.do", method = RequestMethod.POST)
    public @ResponseBody Object getProjectDetails(@RequestParam("trpId") Long trpId,
                                                  @RequestParam("trsId") Long trsId,
                                                  @RequestParam("tugId") Long tugId,
                                                  @RequestParam("trgId") Long trgId) {
        Map<String, Object> mp = new HashMap<String, Object>();
        mp.put("trp_data", trainingProjectService.getTRP(trpId));
        mp.put("trs_data", trainingProjectService.getTRS(trsId));
        mp.put("tug_data", trainingUserService.getTUG(tugId));
        mp.put("tui_data", trainingUserService.getTUIdetails(tugId));
        mp.put("trg_data", trainingGroupService.getTRG(trgId));
        Object result = mp;
        return result;
    }

    @RequestMapping(value = "/getAttach.do", method = RequestMethod.POST)
    public @ResponseBody Object getAttach(@RequestParam("trsId") Long trsId) {
        Map<String, String> mp = new HashMap<String, String>();
        mp.put("attachNm", trainingProjectService.getTRS(trsId).getTrsAttachNm());
        mp.put("attachSize", trainingProjectService.getTRS(trsId).getTrsAttachSize());
        mp.put("attachType", trainingProjectService.getTRS(trsId).getTrsAttachType());
        Object result = mp;
        return result;
    }

    @RequestMapping(value = "/getTotalTU.do", method = RequestMethod.POST)
    public @ResponseBody Object getTotalTU(@RequestParam("trgId") Long trgId) {
        return trainingProjectService.getTotalTU(trgId);
    }

    @RequestMapping(value = "/deleteTRP.do", method = RequestMethod.POST)
    public @ResponseBody void deleteTRP(@RequestParam("trpId") Long trpId) {
        trainingProjectService.deleteTRP(trpId);
    }

    @RequestMapping(value = "/getTRPsent.do", method = RequestMethod.POST)
    public @ResponseBody Integer getTRPsent(@RequestParam("trpId") Long trpId) {
        return trainingProjectService.getTRPSent(trpId);
    }

}
