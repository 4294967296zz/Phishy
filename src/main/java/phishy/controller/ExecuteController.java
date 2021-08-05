package phishy.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import phishy.service.TrainingProjectService;
import phishy.service.TrainingUserService;

import java.util.HashMap;
import java.util.Map;

@Controller
@AllArgsConstructor
public class ExecuteController {

    private TrainingProjectService trainingProjectService;
    private TrainingUserService trainingUserService;

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

}
