package com.seyed.ali.reportsservice.service.interfaces;

import com.seyed.ali.reportsservice.model.payload.response.TimeEntryReport;

public interface ReportStrategy {

    TimeEntryReport generateReport(String projectCriteria);

}
