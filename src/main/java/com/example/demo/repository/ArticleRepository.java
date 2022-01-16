package com.example.demo.repository;

import com.example.demo.entity.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, Long> {
    //JPA에서 제공하는 interface 사용 CrudRepository<관리대상, 대표값(id)의 타입>


}
