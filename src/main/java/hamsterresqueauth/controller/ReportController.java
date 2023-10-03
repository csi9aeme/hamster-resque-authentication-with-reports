package hamsterresqueauth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/default")
public class ReportController {

    @GetMapping("/")
    public String getDemoResponse() {
        return "Hello";
    }
}
