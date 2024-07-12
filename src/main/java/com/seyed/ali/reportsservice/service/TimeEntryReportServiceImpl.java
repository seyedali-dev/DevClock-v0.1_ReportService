package com.seyed.ali.reportsservice.service;

import com.seyed.ali.reportsservice.client.ProjectServiceClient;
import com.seyed.ali.reportsservice.client.TaskServiceClient;
import com.seyed.ali.reportsservice.client.TimeEntryServiceClient;
import com.seyed.ali.reportsservice.model.payload.dto.Project;
import com.seyed.ali.reportsservice.model.payload.dto.Task;
import com.seyed.ali.reportsservice.model.payload.dto.TimeEntry;
import com.seyed.ali.reportsservice.model.payload.response.TimeEntryReport;
import com.seyed.ali.reportsservice.service.interfaces.TimeEntryReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TimeEntryReportServiceImpl implements TimeEntryReportService {

    private final TimeEntryServiceClient timeEntryServiceClient;
    private final ProjectServiceClient projectServiceClient;
    private final TaskServiceClient taskServiceClient;

    @Override
    public TimeEntryReport timeEntryReportByProject(String projectCriteria) {
        Project project = null;
        String projectId = null;

        // get `TimeEntries`
        List<TimeEntry> timeEntriesByProject = this.timeEntryServiceClient.getTimeEntryByProject(projectCriteria);

        // get `Project`
        for (TimeEntry timeEntry : timeEntriesByProject) {
            projectId = timeEntry.getProjectId();
            project = this.projectServiceClient.getProjectById(projectId);
        }

        // get `Tasks`
        List<Task> allTasksForProject = this.taskServiceClient.findAllTasksForProject(projectId);

        return TimeEntryReport.builder()
                .timeEntry(timeEntriesByProject)
                .project(project)
                .task(allTasksForProject)
                .build();
    }

}
