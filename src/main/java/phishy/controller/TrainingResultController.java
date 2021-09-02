package phishy.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;
import phishy.service.TrainingResultService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@AllArgsConstructor
public class TrainingResultController {
    private TrainingResultService trainingResultService;

    @RequestMapping(value = "/getTRRDatatable.do", method = RequestMethod.POST)
    public @ResponseBody
    Object getTRRDatatable(@RequestParam("trpId") Long trpId) {
        Map<String, Object> mp = new HashMap<String, Object>();
        mp.put("data", trainingResultService.getTRR(trpId));
        Object result = mp;
        return result;
    }

    @RequestMapping(value = "/getTRRbySent.do", method = RequestMethod.POST)
    public @ResponseBody
    Object getTRRbySent(@RequestParam("trpId") Long trpId,
                           @RequestParam("sent") Integer sent) {
        Map<String, Object> mp = new HashMap<String, Object>();
        mp.put("data", trainingResultService.getTRRbySent(trpId, sent));
        Object result = mp;
        return result;
    }

    @RequestMapping(value = "/checkMail.do", method = RequestMethod.GET)
    public @ResponseBody void checkMail(
            @RequestParam("trr") Long trrId,
            HttpServletRequest request) {
        String returnIP = request.getHeader("host");
        String returnInfo = request.getHeader("User-Agent");
        trainingResultService.updateMailOpen(trrId, returnIP, returnInfo);
    }

    @RequestMapping(value="/linkClicked.do", method = RequestMethod.GET)
    public RedirectView linkClicked(@RequestParam("tr") Long trrId) {
        trainingResultService.updateLinkClicked(trrId);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:8080/notice");
        return redirectView;
    }

    @RequestMapping(value="/attachDownload.do", method = RequestMethod.GET)
    public RedirectView attachDownload(@RequestParam("tr") Long trrId) {
        trainingResultService.updateAttachDownload(trrId);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:8080/notice");
        return redirectView;
    }

    @RequestMapping(value="/attachOpen.do", method = RequestMethod.GET)
    public RedirectView attachOpen(@RequestParam("tr") Long trrId) {
        trainingResultService.updateAttachOpen(trrId);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:8080/notice");
        return redirectView;
    }
}
