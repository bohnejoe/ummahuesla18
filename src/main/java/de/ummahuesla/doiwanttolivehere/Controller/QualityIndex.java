package de.ummahuesla.doiwanttolivehere.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QualityIndex {

    @RequestMapping("/test")
    String test() {
        return "Test";
    }

}
