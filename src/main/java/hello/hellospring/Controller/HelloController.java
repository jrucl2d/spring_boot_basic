package hello.hellospring.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello") // get 방식으로 /hello 주소를 받음
    public String hello(Model model){
        // 템플릿 엔진으로 변수 넘겨주기
        model.addAttribute("data", "hello!!");
        return "hello"; // hello라는 이름의 템플릿 엔진 찾아서 렌더링
    }
}
