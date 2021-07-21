package phishy.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import phishy.service.TrainingUserService;

import java.util.HashMap;
import java.util.Map;

@Controller
@AllArgsConstructor
public class TrainingUserContoller {
    private TrainingUserService trainingUserService;

    @RequestMapping(value = "/getUsergroupDatatable.do", method = RequestMethod.POST)
    public @ResponseBody Object getUsergroupDatatable() {
        Map<String, Object> mp = new HashMap<String, Object>();
        mp.put("data", trainingUserService.getUsergroupList());
        Object result = mp;
        return result;
    }

    @RequestMapping(value = "/getUserinfoDatatable.do", method = RequestMethod.POST)
    public @ResponseBody Object getUserinfoDatatable() {
        Map<String, Object> mp = new HashMap<String, Object>();
        mp.put("data", trainingUserService.getUserinfoList());
        Object result = mp;
        return result;
    }
}
