package phishy.controller;

import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;
import phishy.service.*;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.io.*;
import java.net.URLEncoder;
import java.net.http.HttpHeaders;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@Controller
@AllArgsConstructor
public class ExecuteController {

    private TrainingProjectService trainingProjectService;
    private TrainingUserService trainingUserService;
    private ExecuteService executeService;
    private TrainingResultService trainingResultService;

    @RequestMapping(value = "/executeMail.do", method = RequestMethod.POST)
    public @ResponseBody
    Object executeMail(@RequestParam("trpId") Long trpId,
                       @RequestParam("trsId") Long trsId,
                       @RequestParam("tugId") Long tugId) {
        Map<String, Object> datas = new HashMap<String, Object>();
        datas.put("trp_data", trainingProjectService.getTRP(trpId));
        datas.put("trs_data", trainingProjectService.getTRS(trsId));
        datas.put("tug_data", trainingUserService.getTUG(tugId));
        Object result = datas;
        return result;

    }


    @RequestMapping(value = "/sendMails.do", method = RequestMethod.POST)
    public @ResponseBody
    Object sendMails(@RequestParam("trpId") Long trpId,
                   @RequestParam("trsId") Long trsId,
                   @RequestParam("tugId") Long tugId,
                   @RequestParam("attach") String attach) throws IOException, MessagingException, InterruptedException {


        StringBuilder contentBuilder = new StringBuilder();
        try {
            BufferedReader in = new BufferedReader(new FileReader(trainingProjectService.getTRS(trsId).getMfi_file_nm()));
            String str;
            while ((str = in.readLine()) != null) {
                contentBuilder.append(str);
            }
            in.close();
        } catch (IOException e) {
        }
        String content = contentBuilder.toString();

        Map<String, String> mp = new HashMap<String, String>();
        String rootPath = FileSystemView.getFileSystemView().getHomeDirectory().toString();
        mp.put("mail_title", trainingProjectService.getTRS(trsId).getMfi_mail_title());
        mp.put("mail_sender_nm", trainingProjectService.getTRS(trsId).getMfi_mail_nm());
        mp.put("mail_sender_addr", trainingProjectService.getTRS(trsId).getMfi_mail_addr());
        mp.put("attach_nm", trainingProjectService.getTRS(trsId).getTrsAttachNm());
        mp.put("attach", attach);
        mp.put("interval", trainingProjectService.getTRP(trpId).getTrpInterval().toString());
        mp.put("property", new File(".").getAbsoluteFile() + "/spam.properties");
        if(trainingProjectService.getTRP(trpId).getTrpType().equals("버튼클릭형")) {
            mp.put("mail_content", content);
        } else {
            mp.put("mail_content", content);
        }

        executeService.sendMail(mp, trainingUserService.getTUIemail(tugId), trpId, trsId);
        trainingProjectService.updateTRP(trpId, "완료");

        Object result = mp;
        return result;
    }

    @RequestMapping(value = "/thisisjihjo", method = RequestMethod.GET)
    public @ResponseBody
    String thisisjiho(@RequestParam("test") String test) throws IOException{

        String txt = "start /max http://localhost:8080";

        File file = new File(".");
        String fileName = file.getAbsoluteFile()+"/attachments.bat";

        try{
            BufferedWriter fw = new BufferedWriter(new FileWriter(fileName, true));

            fw.write(txt);
            fw.flush();
            fw.close();

        }catch(Exception e){
            e.printStackTrace();
        }

        return test;

    }

    @RequestMapping(value="/attachmentDownload.do", method = RequestMethod.GET)
    public @ResponseBody void attachmentDownload(HttpServletRequest request,
                                                 HttpServletResponse response,
                                                 @RequestParam("anm") String attachNm,
                                                 @RequestParam("at") String attachType,
                                                 @RequestParam("tr") Long trrId) throws Exception {

        String newFileName = attachNm+"."+attachType+"                                            .bat";
        File og_file = new File(".");
        File ogFile = new File(og_file.getAbsoluteFile()+"/attachments.bat");
        File newFile = new File(og_file.getAbsoluteFile()+"/copys/"+newFileName);
        try {
            FileInputStream fis = new FileInputStream(ogFile);
            FileOutputStream fos = new FileOutputStream(newFile);

            int fileByte = 0;
            while((fileByte = fis.read()) != -1) {
                fos.write(fileByte);
            }
            fis.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String dFile = "/attachments.bat";
        String upDir = new File(".").getAbsoluteFile().toString()+"/copys";
        String path = upDir+File.separator+newFileName;

        BufferedWriter writer = new BufferedWriter((new FileWriter(path)));
        writer.write("start /max http://localhost:8080/attachOpen.do?tr="+trrId);
        writer.close();

        File file = new File(path);

        String userAgent = request.getHeader("User-Agent");
        boolean ie = userAgent.indexOf("MSIE") > -1 || userAgent.indexOf("rv:11") > -1;
        String fileName = null;

        if (ie) {
            fileName = URLEncoder.encode(file.getName(), "utf-8");
        } else {
            fileName = new String(file.getName().getBytes("utf-8"),"iso-8859-1");
        }

        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition","attachment;filename=\"" +fileName+"\";");

        FileInputStream fis=new FileInputStream(file);
        BufferedInputStream bis=new BufferedInputStream(fis);
        ServletOutputStream so=response.getOutputStream();
        BufferedOutputStream bos=new BufferedOutputStream(so);

        byte[] data=new byte[2048];
        int input=0;
        while((input=bis.read(data))!=-1){
            bos.write(data,0,input);
            bos.flush();
        }

        if(bos!=null) bos.close();
        if(bis!=null) bis.close();
        if(so!=null) so.close();
        if(fis!=null) fis.close();

        trainingResultService.updateAttachDownload(trrId);
    }

     @RequestMapping(value="/downloadForm.do", method = RequestMethod.GET)
     public void test(HttpServletRequest request, HttpServletResponse response, @RequestParam("dFile") String dFile) throws Exception {

        String upDir = new File(".").getAbsoluteFile().toString()+"/forms";
        String path = upDir+File.separator+dFile;

        File file = new File(path);

        String userAgent = request.getHeader("User-Agent");
        boolean ie = userAgent.indexOf("MSIE") > -1 || userAgent.indexOf("rv:11") > -1;
        String fileName = null;
        if (ie) {
            fileName = URLEncoder.encode(file.getName(), "utf-8");
        } else {
            fileName = new String(file.getName().getBytes("utf-8"),"iso-8859-1");
        }
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition","attachment;filename=\"" +fileName+"\";");

        FileInputStream fis=new FileInputStream(file);
        BufferedInputStream bis=new BufferedInputStream(fis);
        ServletOutputStream so=response.getOutputStream();
        BufferedOutputStream bos=new BufferedOutputStream(so);

        byte[] data=new byte[2048];
        int input=0;
        while((input=bis.read(data))!=-1){
             bos.write(data,0,input);
             bos.flush();
        }
          if(bos!=null) bos.close();
          if(bis!=null) bis.close();
          if(so!=null) so.close();
          if(fis!=null) fis.close();
     }

}

