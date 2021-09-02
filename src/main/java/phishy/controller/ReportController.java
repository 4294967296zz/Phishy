package phishy.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import phishy.service.ReportService;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@AllArgsConstructor
public class ReportController {
    private ReportService reportService;

    @RequestMapping(value = "/registerReport.do", method = RequestMethod.POST)
    public @ResponseBody Object registerReport(
            @RequestParam("trrId") Long trrId,
            @RequestParam("rp_username") String rp_username,
            @RequestParam("rp_deptnm") String rp_deptnm,
            @RequestParam("rp_ip") String rp_ip,
            @RequestParam("rp_mail_title") String rp_mail_title,
            @RequestParam("rp_mail_from") String rp_mail_from,
            @RequestParam("rp_mail_addr") String rp_mail_addr,
            @RequestParam("rp_rcpt_date") Date rp_rcpt_date,
            @RequestParam("rp_open_date") Date rp_open_date) {
        Map<String, String> list = new HashMap<String, String>();
        list.put("rp_username",rp_username);
        list.put("rp_deptnm",rp_deptnm);
        list.put("rp_ip",rp_ip);
        list.put("rp_mail_title",rp_mail_title);
        list.put("rp_mail_from",rp_mail_from);
        list.put("rp_mail_addr",rp_mail_addr);
        reportService.registerReport(trrId,list,rp_rcpt_date, rp_open_date);

        return list;
    }
}
