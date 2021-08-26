package phishy.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import phishy.service.TrainingUserService;
import phishy.service.UserService;

import javax.transaction.Transactional;
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

        String str = userIds;
        List<Long> userlist = new ArrayList<Long>();
        for (String s : str.split(", "))
            userlist.add(Long.parseLong(s));

        Long tugId = trainingUserService.registerTUG(tugNm, userlist.size());

        Map<String, Object> mp = new HashMap<String, Object>();
        mp.put("deptData", userService.getUserlistsforTUI(userlist));

        trainingUserService.registerTUI(tugId,userlist);

        return userlist;
    }

    @PostMapping(value = "/updateTUG.do")
    public Object pdateTUG(@RequestParam("tugId") Long tugId,
                            @RequestParam("tugNm") String tugNm,
                            @RequestParam("userIds") String userIds) {
        String str = userIds;
        List<Long> userlist = new ArrayList<Long>();
        for (String s : str.split(", "))
            userlist.add(Long.parseLong(s));

        trainingUserService.updateTUG(tugId,tugNm, userlist.size());
        trainingUserService.updateTUI(tugId,userlist);

        return userlist;
    }

    @PostMapping(value = "/deleteTUG.do")
    public void deleteTUG(@RequestParam("tugId") Long tugId) {
        trainingUserService.deleteTUG(tugId);
    }

    @RequestMapping(value = "/getTUG.do", method = RequestMethod.POST)
    public @ResponseBody Object getTUG(@RequestParam("tugId") Long tugId) {
        Map<String, Object> mp = new HashMap<String, Object>();
        mp.put("tug_data", trainingUserService.getTUG(tugId));
        mp.put("tui_data", trainingUserService.getTUI(tugId));
        Object result = mp;
        return mp;
    }

    @RequestMapping(value = "/getTUIBytugId.do", method = RequestMethod.POST)
    public @ResponseBody Integer getTUIBytugId(@RequestParam("tugId") Long tugId) {
        return trainingUserService.getTUI(tugId).size();
    }

    @RequestMapping(value = "/getTUGCounts.do", method = RequestMethod.POST)
    public @ResponseBody Object getTUGCounts(@RequestParam("tugId") Long tugId) {
        return trainingUserService.getTUGCounts(tugId).getTugCount();
    }

    @RequestMapping(value = "/getTUIdetails.do", method = RequestMethod.POST)
    public @ResponseBody Object getTUIdetails(@RequestParam("tugId") Long tugId) {
        Map<String, Object> mp = new HashMap<String, Object>();
        mp.put("data", trainingUserService.getTUIdetails(tugId));
        Object result = mp;
        return mp;
    }
}
