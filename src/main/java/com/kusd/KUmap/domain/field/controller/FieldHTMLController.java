package com.kusd.KUmap.domain.field.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class FieldHTMLController {
    @GetMapping("/search")
    public String getFields(Model model) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/api/v1/fields/all";

        // API에서 데이터 가져오기
        ResponseEntity<String[]> response = restTemplate.getForEntity(url, String[].class);
        String[] fields = response.getBody();

        // 데이터를 모델에 추가
        model.addAttribute("fields", fields);
        return "fieldSearch";
    }
}
