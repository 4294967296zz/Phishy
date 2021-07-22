package phishy.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import phishy.service.TrainingUserService;
import phishy.service.UserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
public class TrainingUserContoller {
    private TrainingUserService trainingUserService;
    private UserService userService;

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

    @PostMapping(value = "/registerTUG.do")
    public @ResponseBody Object register(
            @RequestParam("tugNm") String tugNm,
            @RequestParam("userIds") String userIds) {
        Map<String, String> list = new HashMap<String, String>();
        list.put("tugNm",tugNm);

        Long tugId = trainingUserService.registerTUG(list);

        String str = userIds;
        List<Long> userlist = new ArrayList<Long>();
        for (String s : str.split(", "))
            userlist.add(Long.parseLong(s));

        Map<String, Object> mp = new HashMap<String, Object>();
        mp.put("deptData", userService.getUserlistsforTUI(userlist));
//
//        List<Map<String, String>> totallist = new ArrayList<Map<String, String>>();
//        Map<String, String> map = null;
//
//        for(int rpt = 0; rpt < mp.size(); rpt++) {
//            map = new HashMap<String, String>();
//            map.put("user_nm",mp.get(rpt).getClass());
//
//            totallist.add(map);
//        }
//

        Map<String, Object> test = new HashMap<String, Object>();
        test.put("registerTUI", trainingUserService.registerTUI(mp, tugId));

        return test;
    }
}
