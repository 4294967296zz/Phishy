package phishy.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import phishy.service.TrainingGroupService;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@AllArgsConstructor
public class TrainingGroupController {
    private TrainingGroupService trainingGroupService;

    @RequestMapping(value = "/registerTRG.do", method = RequestMethod.POST)
    public @ResponseBody
    Object registerTRG(
            @RequestParam("trgNm") String trgNm,
            @RequestParam("trgDesc") String trgDesc,
            @RequestParam("trgStart") Date trgStart,
            @RequestParam("trgEnd") Date trgEnd) {

        Map<String, String> datas = new HashMap<String, String>();
        datas.put("trgNm",trgNm);
        datas.put("trgDesc",trgDesc);

        trainingGroupService.registerTRG(datas,trgStart,trgEnd);
        return datas;
    }

    @RequestMapping(value = "/getTRGs.do", method = RequestMethod.POST)
    public @ResponseBody Object getTRGs() {
        return trainingGroupService.getTRGs();
    }
}
