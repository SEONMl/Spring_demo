package com.example.demo.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller //클라이언트의 요청은 controller가 받는다
public class FirstController {

    @GetMapping("/hi") //접속할 url | 해당 메소드가 hi라는 요청과 동시에 실행된다
    public String niceToMeetYou(Model model){
        model.addAttribute("username", "선미"); //보여줄 페이지의 변수값을 모델을 통해서 보여준다
        return "greetings"; // 리턴값이 보여줄 페이지 | templates/greetings.mustache -> 브라우저로 전송
    }

    @GetMapping("/bye")
    public String seeYouNext(Model model) {
        model.addAttribute("nickname", "홍길동");
        return "goodbye";
    }

}

