package com.seyed.ali.reportsservice.model.payload.response;

import com.seyed.ali.reportsservice.model.payload.dto.Project;
import com.seyed.ali.reportsservice.model.payload.dto.Task;
import com.seyed.ali.reportsservice.model.payload.dto.TimeEntry;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TimeEntryReport {

    @Schema(description = "TimeEntry dto", implementation = TimeEntry.class)
    private List<TimeEntry> timeEntry = new ArrayList<>();

    @Schema(description = "Project dto", implementation = Project.class)
    private Project project;

    @Schema(description = "Task dto", implementation = Task.class)
    private List<Task> task = new ArrayList<>();

}
