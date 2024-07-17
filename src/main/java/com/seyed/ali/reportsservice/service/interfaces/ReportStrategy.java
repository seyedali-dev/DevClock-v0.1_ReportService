package com.seyed.ali.reportsservice.service.interfaces;

import com.seyed.ali.reportsservice.model.payload.response.TimeEntryReport;
import com.seyed.ali.reportsservice.service.ReportContext;

public interface ReportStrategy {

    TimeEntryReport generateReport(ReportContext reportContext);

}
