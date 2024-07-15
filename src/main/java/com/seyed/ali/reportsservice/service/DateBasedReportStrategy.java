package com.seyed.ali.reportsservice.service;

import com.seyed.ali.reportsservice.client.ProjectServiceClient;
import com.seyed.ali.reportsservice.client.TaskServiceClient;
import com.seyed.ali.reportsservice.client.TimeEntryServiceClient;
import com.seyed.ali.reportsservice.exceptions.OperationNotSupportedException;
import com.seyed.ali.reportsservice.model.payload.dto.Project;
import com.seyed.ali.reportsservice.model.payload.dto.Task;
import com.seyed.ali.reportsservice.model.payload.dto.TimeEntry;
import com.seyed.ali.reportsservice.model.payload.response.TimeEntryReport;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DateBasedReportStrategy extends ReportStrategyBase {

    public DateBasedReportStrategy(TimeEntryServiceClient timeEntryServiceClient, ProjectServiceClient projectServiceClient, TaskServiceClient taskServiceClient) {
        super(timeEntryServiceClient, projectServiceClient, taskServiceClient);
    }

    @Override
    public TimeEntryReport generateReport(ReportContext reportContext) {
        List<TimeEntry> timeEntryList = new ArrayList<>();
        switch (reportContext.getTimePeriod()) {
            case DAY -> timeEntryList = this.timeEntryServiceClient.getTimeEntriesForLastDay();
            case WEEK -> {}
            case MONTH -> timeEntryList = this.timeEntryServiceClient.getTimeEntriesForLastMonth();
            default -> throw new OperationNotSupportedException("Invalid time period: " + reportContext.getTimePeriod());
        }
        Project project = this.getProject(timeEntryList);
        List<Task> allTasksForProject = this.getTasks(project.getProjectId());

        return this.timeEntryReportBuilder(timeEntryList, project, allTasksForProject);
    }

}
