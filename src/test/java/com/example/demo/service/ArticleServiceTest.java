package com.example.demo.service;

import com.example.demo.dto.ArticleForm;
import com.example.demo.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest // 해당 클래스는 스프링부트와 연동되어 테스팅된다.
class ArticleServiceTest {

    @Autowired
    ArticleService articleService;

    @Test
    void index() {
        // 예상 시나리오
        Article a = new Article(1L, "aaaa", "1111");
        Article b = new Article(2L, "bbbb", "2222");
        Article c = new Article(3L, "cccc", "3333");
        List<Article> expected = new ArrayList<Article>(Arrays.asList(a, b, c));

        // 실제
        List<Article> articles = articleService.index();

        // 비교
        assertEquals(expected.toString(), articles.toString());

        /*
        테스트 실패 시
        Expected : ~~~
        Actual : ~~~
         */
    }

    @Test
    void show_성공___존재하는_id_입력() {
        // 예상
        Long id = 1L;
        Article expected = new Article(id, "aaaa", "1111");

        // 실패
        Article article= articleService.show(id);

        // 비교
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    void show_실패___존재하지_않는_id_입력() {
        // 예상
        Long id = -1L;
        Article expected = null;

        // 실패
        Article article= articleService.show(id);

        // 비교
        assertEquals(expected, article);
    }

    @Test
    @Transactional // 트랜잭션 선언 안 할 시 다음 테스트에도 영향이 가기 때문 | 테스트가 끝남과 동시에 롤백됨
    void create_성공___title과_content만_있는_dto_입력() {
        // 예상
        String title="dddd";
        String content="4444";
        ArticleForm dto= new ArticleForm(null, title, content);
        Article expected = new Article(4L, title, content);

        // 실패
        Article article = articleService.create(dto);

        // 비교
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    @Transactional // 데이터가 생성, 삭제, 변경될 때 필요함
    void create_실패___id가_포함된_dto_입력() {
        // 예상
        String title="";
        String content="";
        ArticleForm dto= new ArticleForm(4L, title, content);
        Article expected = null;

        // 실패
        Article article = articleService.create(dto);

        // 비교
        assertEquals(expected, article);
    }

    //나머지 테스트는 과제...
    @Test
    @Transactional
    void update_성공___존재하는_id와_title_content가_있는_dto_입력() {

    }

    @Test
    @Transactional
    void update_성공___존재하는_id와_title만_있는_dto_입력() {

    }

    @Test
    @Transactional
    void update_실패___존재하지_않는_id의_dto_입력() {

    }

    @Test
    @Transactional
    void update_실패___id만_있는_dto_입력() {

    }

    @Test
    @Transactional
    void delete_성공___존재하는_id_입력() {

    }

    @Test
    @Transactional
    void delete_실패___존재하지_않는_id_입력() {

    }
}