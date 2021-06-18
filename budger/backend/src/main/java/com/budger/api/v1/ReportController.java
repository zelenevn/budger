package com.budger.api.v1;

import com.budger.data.dto.GetReportDto;
import com.budger.data.dto.ReportDto;
import com.budger.data.dto.TransactionDto;
import com.budger.services.ReportService;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping(
        path = "/report"
)
@CrossOrigin("*")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping
    public List<TransactionDto> getReport(@RequestBody GetReportDto reportDto) {
        String login = reportDto.getLogin();
        Date startDate = reportDto.getStartDate();
        Date endDate = reportDto.getEndDate();
        return reportService.getReport(login, startDate, endDate);
    }
}
