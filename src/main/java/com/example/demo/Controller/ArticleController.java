package com.example.demo.Controller;

import com.example.demo.dto.ArticleForm;
import com.example.demo.entity.Article;
import com.example.demo.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j // 로깅을 위한 골뱅이(annotation)
public class ArticleController {

    @Autowired  //스프링 부트가 미리 생성해 놓은 객체를 가져다가 자동 연결!
    private ArticleRepository articleRepository; //객체를 만들지 않아도 스프링부트가 자동적으로 생성

    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "articles/new";
    }

    @PostMapping("/articles/create") //form이 던진 데이터를 받는다. 객체(DTO)로 받는다
    public String createArticle(ArticleForm form) {
        log.info(form.toString());
//        System.out.println(form.toString()); --> 로깅기능으로 대체!

        //1. Dto를 Entity로 변환 | id=null
        Article article = form.toEntity();
        log.info(article.toString());
//        System.out.println(article.toString());

        //2. Repository에게 Entity를 DB안에 저장하게 함 | id 자동 할당
        Article saved = articleRepository.save(article);
        log.info(saved.toString());
//        System.out.println(saved.toString());

        return "";
    }
}
