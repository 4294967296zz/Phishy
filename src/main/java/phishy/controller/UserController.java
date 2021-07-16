package phishy.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import phishy.service.UserService;
import phishy.util.ExcelRead;
import phishy.util.ExcelReadOption;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @PostMapping(value = "/registerUser.do")
    public @ResponseBody Object register(
            @RequestParam("user_id") String user_id,
            @RequestParam("user_email") String user_email,
            @RequestParam("user_nm") String user_nm,
            @RequestParam("user_pwd") String user_pwd,
            @RequestParam("user_rank") String user_rank,
            @RequestParam("corp_cd") String corp_cd,
            @RequestParam("corp_nm") String corp_nm,
            @RequestParam("org_cd") String org_cd,
            @RequestParam("org_nm") String org_nm,
            @RequestParam("level_gp") String level_gp,
            @RequestParam("level_lv") String level_lv) {
        Map<String, String> list = new HashMap<String, String>();
        list.put("user_id",user_id);
        list.put("user_email",user_email);
        list.put("user_nm",user_nm);
        list.put("user_pwd",user_pwd);
        list.put("user_rank",user_rank);
        list.put("corp_cd",corp_cd);
        list.put("corp_nm",corp_nm);
        list.put("org_cd",org_cd);
        list.put("org_nm",org_nm);
        list.put("level_gp",level_gp);
        list.put("level_lv",level_lv);
        userService.registerUser(list);

        return list;
    }

    @PostMapping(value = "/updateUser.do")
    public @ResponseBody Object update(
            @RequestParam("u_id") Long u_id,
            @RequestParam("user_id") String user_id,
            @RequestParam("user_email") String user_email,
            @RequestParam("user_nm") String user_nm,
            @RequestParam("user_pwd") String user_pwd,
            @RequestParam("user_rank") String user_rank,
            @RequestParam("corp_cd") String corp_cd,
            @RequestParam("corp_nm") String corp_nm,
            @RequestParam("org_cd") String org_cd,
            @RequestParam("org_nm") String org_nm,
            @RequestParam("level_gp") String level_gp,
            @RequestParam("level_lv") String level_lv) {
        Map<String, String> list = new HashMap<String, String>();
        list.put("user_id",user_id);
        list.put("user_email",user_email);
        list.put("user_nm",user_nm);
        list.put("user_pwd",user_pwd);
        list.put("user_rank",user_rank);
        list.put("corp_cd",corp_cd);
        list.put("corp_nm",corp_nm);
        list.put("org_cd",org_cd);
        list.put("org_nm",org_nm);
        list.put("level_gp",level_gp);
        list.put("level_lv",level_lv);
        userService.updateUser(u_id, list);

        return list;
    }

    @RequestMapping(value = "/excelUpload.do", method = RequestMethod.POST)
    public @ResponseBody
    Object excelUploadAjax(MultipartHttpServletRequest request)  throws Exception{
        MultipartFile excelFile =request.getFile("excelFile");
        System.out.println("엑셀 파일 업로드 컨트롤러");
        if(excelFile==null || excelFile.isEmpty()){
            throw new RuntimeException("엑셀파일을 선택 해 주세요.");
        }

        File destFile = new File("D:\\"+excelFile.getOriginalFilename());
        try{
            excelFile.transferTo(destFile);
        }catch(IllegalStateException | IOException e){
            throw new RuntimeException(e.getMessage(),e);
        }

        ExcelReadOption excelReadOption = new ExcelReadOption();
        excelReadOption.setFilePath(destFile.getAbsolutePath());
        excelReadOption.setOutputColumns("A","B","C","D","E","F","G","H");
        excelReadOption.setStartRow(3);

        List<Map<String, String>> excelContent = ExcelRead.read(excelReadOption);
        List<Map<String, String>> totallist = new ArrayList<Map<String, String>>();
        Map<String, String> map = null;

        for(int rpt = 0; rpt < excelContent.size(); rpt++) {
            map = new HashMap<String, String>();
            map.put("user_nm",excelContent.get(rpt).get("A"));
            map.put("corp_nm",excelContent.get(rpt).get("B"));
            map.put("corp_cd",excelContent.get(rpt).get("C"));
            map.put("org_nm",excelContent.get(rpt).get("D"));
            map.put("org_cd",excelContent.get(rpt).get("E"));
            map.put("user_rank",excelContent.get(rpt).get("F"));
            map.put("user_email",excelContent.get(rpt).get("G"));
            map.put("level_gp",excelContent.get(rpt).get("H"));

            totallist.add(map);
        }
        userService.insertMultiUsers(totallist);

        Object result = totallist;
        return result;
    }

    @RequestMapping(value = "/getUserDatatable.do", method = RequestMethod.POST)
    public @ResponseBody Object getDatatable(HttpServletRequest request,
                                             HttpServletResponse response) {
        Map<String, Object> mp = new HashMap<String, Object>();
        mp.put("data", userService.getUserlist());
        Object result = mp;
        return result;
    }

    @RequestMapping(value = "/getUser.do", method = RequestMethod.POST)
    public @ResponseBody Object getUser(@RequestParam("u_id") Long u_id) {
        Map<String, Object> mp = new HashMap<String, Object>();
        mp.put("data", userService.getUser(u_id));
        Object result = mp;
        return mp;
    }

    @RequestMapping(value = "/deleteuser.do", method = RequestMethod.POST)
    public String deleteuser(@RequestParam("u_id") Long u_id) {
        userService.deleteUser(u_id);
        return "redirect:/user";
    }


}
