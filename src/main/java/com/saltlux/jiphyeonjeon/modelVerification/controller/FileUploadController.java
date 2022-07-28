package com.saltlux.jiphyeonjeon.modelVerification.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.util.List;



@Controller
public class FileUploadController {

    private static final String UPLOAD_PATH = "C:\\upload";

    @GetMapping("/upload/form")
    public String uploadPage(){
        return "upload";
    }

    @PostMapping("/upload/files")
    public String insertUploadFiles(MultipartHttpServletRequest multiRequest){
        String save_path = "C:\\upload\\";
        File dir = new File(save_path);
        if(!dir.isDirectory()){
            dir.mkdirs();
        }

        //넘어온 파일을 리스트로 저장
        List<MultipartFile> fileList = multiRequest.getFiles("file");


        for (MultipartFile mf : fileList) {
            String originFileName = mf.getOriginalFilename(); //원본 파일 명
            long fileSize = mf.getSize(); //파일 사이즈

            System.out.println("originFileName : " + originFileName);
            System.out.println("fileSize : " + fileSize);

            String safeFile = save_path+originFileName; //저장 경로 지정
            try{
                mf.transferTo(new File(safeFile)); //파일 업로드
            }catch (IllegalStateException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

        return "redirect:/upload/form";
    }
}
