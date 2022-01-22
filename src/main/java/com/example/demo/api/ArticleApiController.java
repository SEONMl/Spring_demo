package com.example.demo.api;

import com.example.demo.dto.ArticleForm;
import com.example.demo.entity.Article;
import com.example.demo.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j //로그 찍기
@RestController // RestAPI 용 컨트롤러 데이터(JSON)을 반환한다.
public class ArticleApiController {

    @Autowired // DI, 생성 객체를 가져와 연결
    private ArticleService articleService;

//    @Autowired
//    private ArticleRepository articleRepository;
//
    // GET
    @GetMapping("/api/articles")
    public List<Article> index() {
        return articleService.index();
    }

    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id){
        return articleService.show(id);
    }

    // POST 생성요청 != 수정
    @PostMapping("/api/articles")
    public ResponseEntity<Article> create(@RequestBody ArticleForm dto){
        Article created = articleService.create(dto);
        return (created != null) ?
                ResponseEntity.status(HttpStatus.OK).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }


    // PATCH | 수정
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> upadate(@PathVariable Long id,
                                           @RequestBody ArticleForm dto){

        // 코드 간결 | 컨트롤러는 보조 (입력값 전달, 결과 출력) 처리는 서비스가
        Article updated = articleService.update(id, dto);
        return (updated != null) ?
                ResponseEntity.status(HttpStatus.OK).body(updated) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }


    // DELETE
    @DeleteMapping("/api/article/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id){
        Article deleted = articleService.delete(id);
        return (deleted != null) ?
                ResponseEntity.status(HttpStatus.NO_CONTENT).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // 트랜잭션 -> 실패 -> 롤백
    @PostMapping("/api/transaction-test")
    public ResponseEntity<List<Article>> transactionTest(@RequestBody List<ArticleForm> dtos) {
        List<Article> createdList = articleService.createArticles(dtos);
        return (createdList != null) ?
                ResponseEntity.status(HttpStatus.OK).body(createdList) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }


}
