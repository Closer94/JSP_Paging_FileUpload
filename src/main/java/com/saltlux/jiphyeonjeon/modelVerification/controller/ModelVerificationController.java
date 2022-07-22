package com.saltlux.jiphyeonjeon.modelVerification.controller;

import com.saltlux.jiphyeonjeon.modelVerification.vo.ModelVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ModelVerificationController {

    private List<ModelVO> modelList = null;

    public ModelVerificationController() {
        modelList = new ArrayList<>();
        ModelVO modelObj = null;

        //테스트 데이터 세팅
        for (int i = 1; i < 53; i++) {
            modelObj = new ModelVO(i, i + "번째 이갑성");
            modelList.add(modelObj);
        }

    }

    //페이징 처리하는 컨트롤러
    @GetMapping("/paging")
    public String pagingTest(String page, Model model) {
        int pageNum = -1;
        //page의 값이 없을때 처리
        if (page == null || page == "") {
            pageNum = 1;
        } else {
            pageNum = Integer.parseInt(page);
        }

        //jsp 페이지에서 사용할 값 세팅
        List<ModelVO> modelResultList = new ArrayList<>();
        ModelVO modelObj = null;
        int pageNumber = pageNum;
        int pageSize = 5;
        int startPage = (pageNumber / pageSize) * pageSize + 1;
        int totalPages = modelList.size() / pageSize;

        if (modelList.size() % pageSize > 0) {
            totalPages++;
        }

        for (int i = 0; i < 5; i++) {
            modelResultList.add(modelList.get(i));
        }

        model.addAttribute("modelList", modelResultList);
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("startPage", startPage);
        model.addAttribute("totalPages", totalPages);


        return "pagingTest";

    }
}

/*
    @PostMapping("/paging/result")
    public String showPage(Model model){
        List<ModelVO> modelResultList = null;
        ModelVO modelObj = null;
        int pageNumber = 1;
        int pageSize = 5;
        int startPage = (pageNumber / pageSize) * pageSize + 1;
        int totalPages = modelList.size() / pageSize;

        if(totalPages % pageSize > 0){
            totalPages++;
        }

        for(int i = 0; i < 5; i++){
            modelObj = new ModelVO(i, i + "번째 이갑성");
            modelResultList.add(modelObj);
        }

        model.addAttribute("modelList", modelResultList);
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("startPage", startPage);
        model.addAttribute("totalPage", totalPages);





        return "pagingTest";
    }
*/

