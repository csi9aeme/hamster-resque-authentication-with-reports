package hamsterresqueauth.controller;

import hamsterresqueauth.dto.report.CreateReportCommand;
import hamsterresqueauth.dto.report.ReportDto;
import hamsterresqueauth.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/api/default")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService service;

    @GetMapping("/")
    public String getDemoResponse() {
        return "Hello";
    }


    @PostMapping("/auth/create")
    public ReportDto createReport(@RequestBody CreateReportCommand command) {
        return service.createReport(command);
    }

}
