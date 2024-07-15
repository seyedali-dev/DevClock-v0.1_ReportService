package com.seyed.ali.reportsservice.service;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ReportContext {

    private String criteria;
    private LocalDate date;

}
