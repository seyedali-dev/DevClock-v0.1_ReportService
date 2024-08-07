package com.seyed.ali.reportsservice.service;

import com.seyed.ali.reportsservice.model.enums.TimePeriod;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ReportContext {

    private String criteria;
    private TimePeriod timePeriod;
    private LocalDate startDate;
    private LocalDate endDate;

}
