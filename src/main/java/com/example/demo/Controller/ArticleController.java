package com.example.demo.Controller;

import com.example.demo.dto.ArticleForm;
import com.example.demo.entity.Article;
import com.example.demo.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@Slf4j // 로깅을 위한 골뱅이(annotation)
public class ArticleController {

    @Autowired  //스프링 부트가 미리 생성해 놓은 객체를 가져다가 자동 연결!
    private ArticleRepository articleRepository; //객체를 만들지 않아도 스프링부트가 자동적으로 생성

    @GetMapping("/articles/new")
    public String newArticleForm() {
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

        return "redirect:/articles/" + saved.getId() ;
    }

    @GetMapping("/articles/{id}") //id에 들어가는 건 변수다.
    public String show(@PathVariable Long id, Model model) {
        log.info("id = "+ id);
        // 1. id로 데이터를 가져옴  (데이터를 가져오는 주체=repository | Autowired를 통해 가져온 articleRepository)
        // Optional<Article> articleEntity = articleRepository.findById(id); ---> 이 코드는 지양
        Article articleEntity = articleRepository.findById(id).orElse(null); // id에 해당하는 값이 없다면 null을 반환

        // 2. 가져온 데이터를 모델에 등록
        model.addAttribute("article", articleEntity);

        // 3. 보여줄 페이지를 설정
        return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model) {
        // 1. 모든 Article을 가져온다
        List<Article> articleEntityList = articleRepository.findAll(); //데이터 전체 조회

        // 2. 가져온 Article 묶음을 뷰로 전달
        model.addAttribute("articleList", articleEntityList);

        // 3. 뷰 페이지를 설정
        return "articles/index"; //articles/index.mustache
    }

    @GetMapping("/articles/{id}/edit") //PathVariable - {value} 값 이름 동일하게 해야 됨
    public String edit(@PathVariable Long id, Model model) {
        // 수정할 데이터를 가져오기
        // 리포지터리를 사용해 데베에 있는 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);

        // 모델에 데이터 등록하기
        model.addAttribute("article", articleEntity);

        // 뷰 페이지 설정
        return "articles/edit";
    }

    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){
        log.info("삭제 요청이 들어왔습니다!!");

        // 1. 삭제 대상을 가져온다.
        Article target = articleRepository.findById(id).orElse(null);
        log.info(target.toString());

        // 2. 그 대상을 삭제한다.
        if (target != null){
            articleRepository.delete(target);
            rttr.addFlashAttribute("msg","삭제가 완료 되었습니다."); //일회성 데이터 등록 ->index..mustache로 보내짐
        }

        // 3. 결과 페이지로 리다이렉트 한다.
        return "redirect:/articles";
    }
}
