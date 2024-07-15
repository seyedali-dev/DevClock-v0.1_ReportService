package com.seyed.ali.reportsservice.service;

import com.seyed.ali.reportsservice.client.ProjectServiceClient;
import com.seyed.ali.reportsservice.client.TaskServiceClient;
import com.seyed.ali.reportsservice.client.TimeEntryServiceClient;
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
        List<TimeEntry> timeEntriesForLastMonth = this.timeEntryServiceClient.getTimeEntriesForLastMonth();
        Project project = this.getProject(timeEntriesForLastMonth);
        List<Task> allTasksForProject = this.getTasks(project.getProjectId());

        return this.timeEntryReportBuilder(timeEntriesForLastMonth, project, allTasksForProject);
    }

}
