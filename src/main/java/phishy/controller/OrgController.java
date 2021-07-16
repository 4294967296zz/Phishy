package phishy.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import phishy.service.OrgService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Controller
public class OrgController {

    private OrgService orgService;

    @PostMapping(value = "/registerOrg.do")
    public @ResponseBody
    Object register(
            @RequestParam("corp_cd") String corp_cd,
            @RequestParam("corp_nm") String corp_nm,
            @RequestParam("dept_cd") String dept_cd,
            @RequestParam("dept_nm") String dept_nm,
            @RequestParam("upper_cd") String upper_cd) {
        Map<String, String> list = new HashMap<String, String>();
        list.put("corp_cd",corp_cd);
        list.put("corp_nm",corp_nm);
        list.put("dept_cd",dept_cd);
        list.put("dept_nm",dept_nm);
        list.put("upper_cd",upper_cd);
        orgService.registerOrg(list);

        return list;
    }

    @RequestMapping(value = "/getOrgDatatable.do", method = RequestMethod.POST)
    public @ResponseBody Object getDatatable(HttpServletRequest request,
                                             HttpServletResponse response) {
        Map<String, Object> mp = new HashMap<String, Object>();
        mp.put("data", orgService.getOrglist());
        Object result = mp;
        return result;
    }

    @RequestMapping(value = "/getUser.do", method = RequestMethod.POST)
    public @ResponseBody Object getUser(@RequestParam("o_id") Long o_id) {
        Map<String, Object> mp = new HashMap<String, Object>();
        mp.put("data", orgService.getOrg(o_id));
        Object result = mp;
        return mp;
    }

    @RequestMapping(value = "/deleteuser.do", method = RequestMethod.POST)
    public String deleteuser(@RequestParam("o_id") Long o_id) {
        orgService.deleteOrg(o_id);
        return "redirect:/user";
    }
}
