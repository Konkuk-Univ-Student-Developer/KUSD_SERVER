package com.kusd.KUmap.domain.field.controller;

import com.kusd.KUmap.domain.field.repository.FieldRepository;
import com.kusd.KUmap.domain.field.service.FieldService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Controller
@RequiredArgsConstructor
public class FieldHTMLController {

    private final FieldService fieldService;

    @GetMapping("/search")
    public String getFields(Model model) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://203.252.168.41:8080/api/v1/fields/all";

        // API에서 데이터 가져오기
        String[] fields = fieldService.getAllFieldList().toArray(new String[0]);

        // 데이터를 모델에 추가
        model.addAttribute("fields", fields);
        return "fieldSearch";
    }
}
