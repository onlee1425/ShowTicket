package zb.Team.showticket.main;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class MainController {

    @RequestMapping("/")
    public String index(Model model){

        return "index";
    }

    //에러페이지
    @RequestMapping("/error/denied")
    public String errorDenied(){

        return "error/denied";
    }

}
