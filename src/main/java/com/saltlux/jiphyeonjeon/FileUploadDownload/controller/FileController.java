package com.saltlux.jiphyeonjeon.FileUploadDownload.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
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

/*
    @GetMapping("/download")
    public String download() {
        String path = "C:\\upload\\jarzip.PNG";

        try {
            Path filePath = Paths.get(path);
            Resource resource = new InputStreamResource(Files.newInputStream(filePath)); // 파일 resource 얻기

            File file = new File(path);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentDisposition(ContentDisposition.builder("attachment").filename(file.getName()).build());  // 다운로드 되거나 로컬에 저장되는 용도로 쓰이는지를 알려주는 헤더

            return "redirect:/upload/form";
        } catch(Exception e) {
            return "redirect:/upload/form";
        }
    }
*/

}
