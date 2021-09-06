package phishy.controller;

import lombok.AllArgsConstructor;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import phishy.dto.TrainingProjectDto;
import phishy.dto.TrainingResultDto;
import phishy.service.*;

import javax.mail.MessagingException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class ExecuteController {

    private TrainingProjectService trainingProjectService;
    private TrainingUserService trainingUserService;
    private ExecuteService executeService;
    private TrainingResultService trainingResultService;
    private UserService userService;

    private String getBrowser(HttpServletRequest request) {
        String header =request.getHeader("User-Agent");
        if (header.contains("MSIE")) {
                return "MSIE";
         } else if(header.contains("Chrome")) {
                return "Chrome";
         } else if(header.contains("Opera")) {
                return "Opera";
         }
         return "Firefox";
    }


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

        executeService.sendMail(mp, trainingUserService.getTUIemail(tugId), trpId, trsId, tugId);
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

     @GetMapping("/excelReport.do")
     public void excelDownload(HttpServletResponse response,HttpServletRequest request, @RequestParam("tp") Long tp) throws IOException {

        TrainingProjectDto TRP = trainingProjectService.getTRP(tp);
        List<TrainingResultDto> TRR = trainingResultService.getTRR(tp);

        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("이메일모의훈련");
        Sheet sheet1 = wb.createSheet("통합 훈련결과");
        Sheet sheet2 = wb.createSheet("부서별 훈련결과");
        Sheet sheet3 = wb.createSheet("직급별 훈련결과");
        Row first = null;
        Row row = null;
        Cell cell = null;
        Cell first_cell = null;
        int rowNum = 0;

        CellStyle firstStyle = wb.createCellStyle();
        Font fonttest = wb.createFont();
        fonttest.setFontHeight((short) (16*40));
        fonttest.setBoldweight(Font.BOLDWEIGHT_BOLD);
        firstStyle.setFont(fonttest);
        first = sheet.createRow(0);
        first_cell = first.createCell(1);
        first_cell.setCellStyle(firstStyle);
        first_cell.setCellValue("마블시스템 피싱쉴드 이메일모의훈련");
        sheet.addMergedRegion(new CellRangeAddress(0,3,1,6));


        // Header
        row = sheet1.createRow(rowNum++);
        cell = row.createCell(0);
        cell.setCellValue("번호");
        cell = row.createCell(1);
        cell.setCellValue("이름");
        cell = row.createCell(2);
        cell.setCellValue("이메일");
        cell = row.createCell(3);
        cell.setCellValue("직급");
        cell = row.createCell(4);
        cell.setCellValue("소속");
        cell = row.createCell(5);
        cell.setCellValue("부서");
        cell = row.createCell(5);
        cell.setCellValue("메일열람");
        cell = row.createCell(6);
        cell.setCellValue("열람시간");
        cell = row.createCell(7);
        cell.setCellValue("버튼클릭");
        cell = row.createCell(8);
        cell.setCellValue("클릭시간");
        cell = row.createCell(9);
        cell.setCellValue("첨부파일 다운로드");
        cell = row.createCell(10);
        cell.setCellValue("다운로드 시간");
        cell = row.createCell(11);
        cell.setCellValue("첨부파일 실행");
        cell = row.createCell(12);
        cell.setCellValue("첨부파일 실행 시간");
        cell = row.createCell(13);
        cell.setCellValue("피싱사이트 접속");
        cell = row.createCell(14);
        cell.setCellValue("피싱사이트 접속 시간");
        cell = row.createCell(15);
        cell.setCellValue("피싱사이트 입력 정보");
        cell = row.createCell(16);
        cell.setCellValue("사고신고여부");


        // Body
        for (int i=0; i<TRR.size(); i++) {
            row = sheet1.createRow(rowNum++);
            cell = row.createCell(0);
            cell.setCellValue(TRR.get(i).getTrrId());
            cell = row.createCell(1);
            cell.setCellValue(TRR.get(i).getUserNm());
            cell = row.createCell(2);
            cell.setCellValue(userService.getUserByEmail(TRR.get(i).getUserId()).getUserEmail());
            cell = row.createCell(3);
            cell.setCellValue(TRR.get(i).getUserRank());
            cell = row.createCell(4);
            cell.setCellValue(userService.getUserByEmail(TRR.get(i).getUserId()).getCorpNm());
            cell = row.createCell(5);
            cell.setCellValue(userService.getUserByEmail(TRR.get(i).getUserId()).getDeptNm());
            cell = row.createCell(5);
            cell.setCellValue(TRR.get(i).getTrrOpen());
            cell = row.createCell(6);
            if(TRR.get(i).getTrrOpenDate() == null) {
                cell.setCellValue(" ");
            } else {
                cell.setCellValue(TRR.get(i).getTrrOpenDate().toString());
            }
            cell = row.createCell(7);
            cell.setCellValue(TRR.get(i).getTrrLink());
            cell = row.createCell(8);
            if(TRR.get(i).getTrrLinkDate() == null) {
                cell.setCellValue(" ");
            } else {
                cell.setCellValue(TRR.get(i).getTrrLinkDate().toString());
            }
            cell = row.createCell(9);
            cell.setCellValue(TRR.get(i).getTrrAttachClick());
            cell = row.createCell(10);
            if(TRR.get(i).getTrrAttachClickDate() == null) {
                cell.setCellValue(" ");
            } else {
                cell.setCellValue(TRR.get(i).getTrrAttachClickDate().toString());
            }
            cell = row.createCell(11);
            cell.setCellValue(TRR.get(i).getTrrAttachOpen());
            cell = row.createCell(12);
            if(TRR.get(i).getTrrAttachOpenDate() == null) {
                cell.setCellValue(" ");
            } else {
                cell.setCellValue(TRR.get(i).getTrrAttachOpenDate().toString());
            }
            cell = row.createCell(13);
            cell.setCellValue(TRR.get(i).getTrrPhishingclick());
            cell = row.createCell(14);
            if(TRR.get(i).getTrrPhishingclickDate() == null) {
                cell.setCellValue(" ");
            } else {
                cell.setCellValue(TRR.get(i).getTrrPhishingclickDate().toString());
            }
            cell = row.createCell(15);
            cell.setCellValue(TRR.get(i).getTrrPhishingContent());
            cell = row.createCell(16);
            cell.setCellValue(TRR.get(i).getTrrReport());

        }


        // 컨텐츠 타입과 파일명 지정 & 한글 인코딩
        String fileName = TRP.getTrpNm()+"_결과보고서.xlsx";
        String header = getBrowser(request);
        if (header.contains("MSIE")) {
               String docName = URLEncoder.encode(fileName,"UTF-8").replaceAll("\\+", "%20");
               response.setHeader("Content-Disposition", "attachment;filename=" + docName + ";");
        } else if (header.contains("Firefox")) {
               String docName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
               response.setHeader("Content-Disposition", "attachment; filename=\"" + docName + "\"");
        } else if (header.contains("Opera")) {
               String docName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
               response.setHeader("Content-Disposition", "attachment; filename=\"" + docName + "\"");
        } else if (header.contains("Chrome")) {
               String docName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
               response.setHeader("Content-Disposition", "attachment; filename=\"" + docName + "\"");
        }
        response.setHeader("Content-Type", "application/octet-stream");
        response.setHeader("Content-Transfer-Encoding", "binary;");
        response.setHeader("Pragma", "no-cache;");
        response.setHeader("Expires", "-1;");

        // Excel File Output
        wb.write(response.getOutputStream());
        wb.close();
    }

}

