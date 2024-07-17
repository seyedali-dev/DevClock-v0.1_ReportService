package com.seyed.ali.reportsservice.service;

import com.seyed.ali.reportsservice.model.enums.TimePeriod;
import lombok.Data;

@Data
public class ReportContext {

    private String criteria;
    private TimePeriod timePeriod;

}
