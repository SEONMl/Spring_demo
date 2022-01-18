package com.example.demo.repository;

import com.example.demo.entity.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface ArticleRepository extends CrudRepository<Article, Long> {
    //JPA에서 제공하는 interface 사용 CrudRepository<관리대상, 대표값(id)의 타입>

    //CrudRepository 메소드 오버라이딩


    @Override
    ArrayList<Article> findAll();
}
