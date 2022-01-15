package com.example.demo.Controller;

import com.example.demo.dto.ArticleForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleController {

    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "articles/new";
    }

    @PostMapping("/articles/create") //form이 던진 데이터를 받는다. 객체(DTO)로 받는다
    public String createArticle(ArticleForm form) {

        System.out.println(form.toString());
        return "";
    }
}
