package phishy.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import phishy.service.OrgService;
import phishy.util.ExcelRead;
import phishy.util.ExcelReadOption;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    @PostMapping(value = "/updateOrg.do")
    public @ResponseBody
    Object update(
            @RequestParam("o_id") Long o_id,
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
        orgService.updateOrg(o_id, list);

        return list;
    }

    @RequestMapping(value = "/OrgexcelUpload.do", method = RequestMethod.POST)
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
        excelReadOption.setOutputColumns("A","B","C","D","E");
        excelReadOption.setStartRow(3);

        List<Map<String, String>> excelContent = ExcelRead.read(excelReadOption);
        List<Map<String, String>> totallist = new ArrayList<Map<String, String>>();
        Map<String, String> map = null;

        for(int rpt = 0; rpt < excelContent.size(); rpt++) {
            map = new HashMap<String, String>();
            map.put("corp_nm",excelContent.get(rpt).get("A"));
            map.put("corp_cd",excelContent.get(rpt).get("B"));
            map.put("dept_nm",excelContent.get(rpt).get("C"));
            map.put("dept_cd",excelContent.get(rpt).get("D"));
            map.put("upper_cd",excelContent.get(rpt).get("E"));

            totallist.add(map);
        }
        orgService.insertMultiOrg(totallist);

        Object result = totallist;
        return result;
    }

    @RequestMapping(value = "/getOrgDatatable.do", method = RequestMethod.POST)
    public @ResponseBody Object getDatatable(HttpServletRequest request,
                                             HttpServletResponse response) {
        Map<String, Object> mp = new HashMap<String, Object>();
        mp.put("data", orgService.getOrglist());
        Object result = mp;
        return result;
    }

    @RequestMapping(value = "/getOrg.do", method = RequestMethod.POST)
    public @ResponseBody Object getOrg(@RequestParam("o_id") Long o_id) {
        Map<String, Object> mp = new HashMap<String, Object>();
        mp.put("data", orgService.getOrg(o_id));
        Object result = mp;
        return mp;
    }

    @RequestMapping(value = "/getUpperCorpnm.do", method = RequestMethod.POST)
    public @ResponseBody Object getUpperCorpnm(@RequestParam("upper_cd") String upper_cd) {
        Map<String, Object> mp = new HashMap<String, Object>();
        mp.put("data", orgService.getUpperCorp_nm(upper_cd));
        Object result = mp;
        return mp;
    }

    @RequestMapping(value = "/getUppers.do", method = RequestMethod.POST)
    public @ResponseBody Object getUppers() {
        Map<String, Object> mp = new HashMap<String, Object>();
        mp.put("data", orgService.getUppers());
        Object result = mp;
        return mp;
    }

    @RequestMapping(value = "/deleteOrg.do", method = RequestMethod.POST)
    public String deleteOrg(@RequestParam("o_id") Long o_id) {
        orgService.deleteOrg(o_id);
        return "redirect:/org";
    }

    @RequestMapping(value = "/deleteOrgs.do", method = RequestMethod.POST)
    public String deleteOrgs(@RequestParam(value = "oid[]") List<Long> oid) {
         orgService.deleteOrgs(oid);
         return "redirect:/user";
    }


}
