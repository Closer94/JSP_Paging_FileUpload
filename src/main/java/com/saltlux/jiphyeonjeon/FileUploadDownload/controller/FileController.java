package com.saltlux.jiphyeonjeon.FileUploadDownload.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.zeroturnaround.zip.ZipUtil;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;


@Controller
public class FileController {


    @GetMapping("/upload/form")
    public String uploadPage(){
        return "upload";
    }

    //파일 업로드 
    @PostMapping("/upload/files")
    public String uploadFiles(HttpServletRequest request, MultipartHttpServletRequest multiRequest){
//        String folderName = request.getParameter("folderName");
        String folderName = "test";
        uploadFilesTest(folderName, multiRequest);

        System.out.println("controller 는 종료합니다.");
        return "redirect:/upload/form";
    }

    // 비동기 파일 업로드 후 압축 폴더 만들기
    @Async
    public static void uploadFilesTest(String folderName, MultipartHttpServletRequest multiRequest) {
        //넘어온 파일을 리스트로 저장
        List<MultipartFile> fileList = multiRequest.getFiles("file");
        int result = 1;
        String save_path = "C:\\upload\\" + folderName;

        File dir = new File(save_path);
        if (!dir.isDirectory()) {
            dir.mkdirs();
        }

        for (MultipartFile mf : fileList) {
            String originFileName = mf.getOriginalFilename(); //원본 파일 명
            long fileSize = mf.getSize(); //파일 사이즈

            System.out.println("originFileName : " + originFileName);
            System.out.println("fileSize : " + fileSize);

            String safeFile = save_path + "\\" + originFileName; //저장 경로 지정
            try {
                mf.transferTo(new File(safeFile)); //파일 업로드
            } catch (IllegalStateException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                result = -1;
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                result = -1;
            }
        }

        if (result != 1) {
            System.out.println("파일 업로드 실패");
            //DB에 업로드 상태값 = 업로드 실패 -> mapper
        } else {
            //파일을 Zip 으로 만들기
            ZipUtil.pack(new File("C:\\upload\\"+folderName), new File("C:\\zip\\"+ folderName +".zip"));

            System.out.println("압축 파일 생성 완료");

            System.out.println("파일 업로드 프로세스 성공");
            //DB에 업로드 상태값 = 업로드 완료 -> mapper
        }
    }


    // 일반 파일 다운로드
    @GetMapping("/download")
    public void download(String fileName, HttpServletResponse response) throws IOException{
        System.out.println("fileName: " + fileName);
        String path = "C:\\upload\\"+fileName+".pptx";

        byte[] fileByte = FileUtils.readFileToByteArray(new File(path));

        //response 객체를 사용하는 이유: 아래에서 Front가 파일을 다운받을 수 있게 HttpServletResponse객체에 Contet-Type, Content-dispositon 등 정보를 담아 전송 한다.
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(fileName+".pptx", "UTF-8")+"\";");
        response.setHeader("Content-Transfer-Encoding", "binary");

        response.getOutputStream().write(fileByte);
        response.getOutputStream().flush();
        response.getOutputStream().close();

    }

    
    //zip 파일로 다운로드
    @GetMapping("/download/zip")
    public void zipDownload(String folderName, HttpServletResponse response) throws IOException{
        String filePath = "C:\\zip\\";
        
        //다운로드 folder 여부 체크
        File file = new File(filePath);

        if(file.isDirectory()){ //디렉토리가 존재하면 압축파일 다운로드
            filePath = filePath + folderName + ".zip";

            response.setContentType("application/zip");
            response.setHeader("Content-Disposition", "attachment; filename=\""+ URLEncoder.encode("test.zip", "UTF-8") + "\";");

            FileInputStream fis = new FileInputStream(filePath);
            BufferedInputStream bis = new BufferedInputStream(fis);
            ServletOutputStream so = response.getOutputStream();
            BufferedOutputStream bos = new BufferedOutputStream(so);

            byte[] data = new byte[2048];
            int input = 0;

            while ((input = bis.read(data)) != -1) {
                bos.write(data, 0, input);
                bos.flush();
            }

            if (bos != null)
                bos.close();
            if (bis != null)
                bis.close();
            if (so != null)
                so.close();
            if (fis != null)
                fis.close();

        }else{ //해당 디렉토리가 존재하지 않는다면 예외처리
            throw new IOException();
        }


    }

}
