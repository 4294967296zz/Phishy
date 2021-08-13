package phishy.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import phishy.service.TrainingResultService;

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
}
