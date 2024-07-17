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

import java.util.List;

@Service
public class DateBasedReportStrategy extends ReportStrategyBase {

    public DateBasedReportStrategy(TimeEntryServiceClient timeEntryServiceClient, ProjectServiceClient projectServiceClient, TaskServiceClient taskServiceClient) {
        super(timeEntryServiceClient, projectServiceClient, taskServiceClient);
    }

    @Override
    public TimeEntryReport generateReport(ReportContext reportContext) {
        List<TimeEntry> timeEntryList;
        if (reportContext.getTimePeriod() != null) {
            switch (reportContext.getTimePeriod()) {
                case TODAY -> timeEntryList = this.timeEntryServiceClient.getTimeEntriesForToday();
                case LAST_DAY -> timeEntryList = this.timeEntryServiceClient.getTimeEntriesForLastDay();
                case LAST_WEEK -> timeEntryList = this.timeEntryServiceClient.getTimeEntriesForLastWeek();
                case LAST_MONTH -> timeEntryList = this.timeEntryServiceClient.getTimeEntriesForLastMonth();
                default ->
                        throw new OperationNotSupportedException("Invalid time period: " + reportContext.getTimePeriod());
            }
        } else
            timeEntryList = this.timeEntryServiceClient.getTimeEntriesForSpecifiedDateRange(reportContext.getStartDate(), reportContext.getEndDate());

        Project project = this.getProject(timeEntryList);
        List<Task> allTasksForProject = this.getTasks(project.getProjectId());

        return this.timeEntryReportBuilder(timeEntryList, project, allTasksForProject);
    }

}
