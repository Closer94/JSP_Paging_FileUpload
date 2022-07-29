package com.saltlux.jiphyeonjeon.FileUploadDownload.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


@Controller
public class FileController {


    @GetMapping("/upload/form")
    public String uploadPage(){
        return "upload";
    }

    @PostMapping("/upload/files")
    public String uploadFiles(MultipartHttpServletRequest multiRequest){
        uploadFilesTest(multiRequest);

        System.out.println("controller 는 종료합니다.");
        return "redirect:/upload/form";
    }

    @Async
    public static void uploadFilesTest(MultipartHttpServletRequest multiRequest) {
        //넘어온 파일을 리스트로 저장
        List<MultipartFile> fileList = multiRequest.getFiles("file");
        int result = 1;
        String save_path = "C:\\upload\\";

        File dir = new File(save_path);
        if (!dir.isDirectory()) {
            dir.mkdirs();
        }

        for (MultipartFile mf : fileList) {
            String originFileName = mf.getOriginalFilename(); //원본 파일 명
            long fileSize = mf.getSize(); //파일 사이즈

            System.out.println("originFileName : " + originFileName);
            System.out.println("fileSize : " + fileSize);

            String safeFile = save_path + originFileName; //저장 경로 지정
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

        try {
            System.out.println("비동기 테스트 시작합니다.");
            Thread.sleep(10000);
            System.out.println("비동기 테스트 종료합니다.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (result != 1) {
            System.out.println("파일 업로드 실패");
            //DB에 업로드 상태값 = 업로드 실패 -> mapper
        } else {
            System.out.println("파일 업로드 성공");
            //DB에 업로드 상태값 = 업로드 완료 -> mapper
        }
    }


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


}
