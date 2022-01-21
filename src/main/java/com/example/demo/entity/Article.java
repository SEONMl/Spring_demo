package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@AllArgsConstructor //모든 생성자 사용가능
@NoArgsConstructor //디폴트 생성자를 추가하는 롬복
@ToString //롬복으로 코드 리팩토링
@Entity //DB가 해당 객체를 인식 가능
@Getter //모든 게터 추가
public class Article {

    @Id //대표값을 지정 like a 주민등록번호
    @GeneratedValue(strategy = GenerationType.IDENTITY) //1, 2, 3, ... 자동 생성 어노테이션 ( DB가 id를 자동 생성 어노테이션 )
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    public void patch(Article article) {
        if(article.title != null){
            this.title = article.title;
        }
        if (article.content != null) this.content=article.content;
    }

    /*
    게터를 만들어도 되지만 우리는? 롬복을 쓴다.
    public Long getId() {
        return this.id;
    }

     */
}
