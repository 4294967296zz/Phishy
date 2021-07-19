package phishy.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import phishy.service.MailformService;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

@RestController
@Controller
@AllArgsConstructor
public class MailformController {
    private MailformService mailformService;

    @PostMapping("/registerMailform.do")
    Object register(
            @RequestParam("mfi_nm") String mfi_nm,
            @RequestParam("mfi_mail_addr") String mfi_mail_addr,
            @RequestParam("mfi_mail_nm") String mfi_mail_nm,
            @RequestParam("mfi_mail_title") String mfi_mail_title,
            @RequestParam("mfi_file")MultipartFile mfi_file) throws Exception {

        String rootPath = FileSystemView.getFileSystemView().getHomeDirectory().toString();
        String basePath = rootPath + "/" + "phishy_contents";
        String filePath = basePath + "/" + mfi_file.getOriginalFilename();
        File dest = new File(filePath);
        mfi_file.transferTo(dest);

        Map<String, String> list = new HashMap<String, String>();
        list.put("mfi_nm",mfi_nm);
        list.put("mfi_mail_addr",mfi_mail_addr);
        list.put("mfi_mail_nm",mfi_mail_nm);
        list.put("mfi_file_nm",filePath);
        list.put("mfi_mail_title",mfi_mail_title);
        mailformService.registerMailform(list);

        return list;
    }

    @PostMapping("/updateMailform.do")
    public @ResponseBody Object update(
            @RequestParam("mfi_id") Long mfi_id,
            @RequestParam("mfi_nm") String mfi_nm,
            @RequestParam("mfi_mail_addr") String mfi_mail_addr,
            @RequestParam("mfi_mail_nm") String mfi_mail_nm,
            @RequestParam("mfi_file_nm") String mfi_file_nm,
            @RequestParam("mfi_file_title") String mfi_file_title) {
        Map<String, String> list = new HashMap<String, String>();
        list.put("mfi_nm",mfi_nm);
        list.put("mfi_mail_addr",mfi_mail_addr);
        list.put("mfi_mail_nm",mfi_mail_nm);
        list.put("mfi_file_nm",mfi_file_nm);
        list.put("mfi_file_title",mfi_file_title);
        mailformService.updateMailform(mfi_id,list);
        return list;
    }
}

