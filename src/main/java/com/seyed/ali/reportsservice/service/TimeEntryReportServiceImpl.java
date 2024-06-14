package com.seyed.ali.reportsservice.service;

import com.seyed.ali.reportsservice.client.ServiceClient;
import com.seyed.ali.reportsservice.service.interfaces.TimeEntryReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TimeEntryReportServiceImpl implements TimeEntryReportService {

    private final ServiceClient serviceClient;

    @Override
    public Object timeEntryReportByProject(String projectCriteria) {
        return null;
    }

}
