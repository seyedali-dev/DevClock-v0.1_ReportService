package com.seyed.ali.reportsservice.service;

import com.seyed.ali.reportsservice.client.ProjectServiceClient;
import com.seyed.ali.reportsservice.client.TaskServiceClient;
import com.seyed.ali.reportsservice.client.TimeEntryServiceClient;
import com.seyed.ali.reportsservice.model.payload.dto.Project;
import com.seyed.ali.reportsservice.model.payload.dto.Task;
import com.seyed.ali.reportsservice.model.payload.dto.TimeEntry;
import com.seyed.ali.reportsservice.model.payload.response.TimeEntryReport;
import com.seyed.ali.reportsservice.service.interfaces.ReportStrategy;

import java.util.List;

public abstract class ReportStrategyBase implements ReportStrategy {

    protected final TimeEntryServiceClient timeEntryServiceClient;
    protected final ProjectServiceClient projectServiceClient;
    protected final TaskServiceClient taskServiceClient;

    public ReportStrategyBase(TimeEntryServiceClient timeEntryServiceClient, ProjectServiceClient projectServiceClient, TaskServiceClient taskServiceClient) {
        this.timeEntryServiceClient = timeEntryServiceClient;
        this.projectServiceClient = projectServiceClient;
        this.taskServiceClient = taskServiceClient;
    }

    protected Project getProject(List<TimeEntry> timeEntries) {
        String projectId = timeEntries.getFirst().getProjectId();
        return this.projectServiceClient.getProjectById(projectId);
    }

    protected List<Task> getTasks(String projectId) {
        return this.taskServiceClient.findAllTasksForProject(projectId);
    }

    protected TimeEntryReport timeEntryReportBuilder(List<TimeEntry> timeEntries, Project project, List<Task> taskList) {
        return TimeEntryReport.builder()
                .timeEntry(timeEntries)
                .project(project)
                .task(taskList)
                .build();
    }

}
