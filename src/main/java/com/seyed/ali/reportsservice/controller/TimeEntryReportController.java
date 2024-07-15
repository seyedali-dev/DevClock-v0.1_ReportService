package com.seyed.ali.reportsservice.controller;

import com.seyed.ali.reportsservice.model.enums.TimePeriod;
import com.seyed.ali.reportsservice.model.payload.response.TimeEntryReport;
import com.seyed.ali.reportsservice.model.payload.response.Result;
import com.seyed.ali.reportsservice.service.DateBasedReportStrategy;
import com.seyed.ali.reportsservice.service.ProjectBasedReportStrategy;
import com.seyed.ali.reportsservice.service.ReportContext;
import com.seyed.ali.reportsservice.service.TaskBasedReportStrategy;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/report/timeentry")
@SecurityRequirement(name = "Keycloak")
@Tag(
        name = "TimeEntry Report",
        description = """
                 A user can see his time tracked report based on filters that he'll provide. The filter can be by:
                                \s
                 - Project; meaning can filter the time tracked by project.
                 - Task; same as project.
                 - Date(s); meaning time tracked can be filtered for a specified date(s).
                 - Month, Week, Yesterday, Today; the names are self-explanatory.
                \s"""
)
public class TimeEntryReportController {

    private final ProjectBasedReportStrategy projectReport;
    private final TaskBasedReportStrategy taskReport;
    private final DateBasedReportStrategy dateBasedReportStrategy;

    @GetMapping("/project/{projectCriteria}")
    @Operation(
            summary = "TimeEntry Report - \"PROJECT\"",
            description = "Retrieves all the time entries by either 'projectId' or 'projectName'",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful operation",
                            content = @Content(array = @ArraySchema(schema = @Schema(implementation = TimeEntryReport.class)))
                    )
            })
    public ResponseEntity<Result> filterReportByProject(@PathVariable String projectCriteria) {
        ReportContext reportContext = new ReportContext();
        reportContext.setCriteria(projectCriteria);
        return ResponseEntity.ok(new Result(
                true,
                HttpStatus.OK,
                "TimeEntry Report - Criteria: Project",
                this.projectReport.generateReport(reportContext)
        ));
    }

    @GetMapping("/task/{taskName}")
    @Operation(
            summary = "TimeEntry Report - \"TASK\"",
            description = "Retrieves all the time entries by 'TaskName'",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful operation",
                            content = @Content(array = @ArraySchema(schema = @Schema(implementation = TimeEntryReport.class)))
                    )
            })
    public ResponseEntity<Result> filterReportByTask(@PathVariable String taskName) {
        ReportContext reportContext = new ReportContext();
        reportContext.setCriteria(taskName);
        return ResponseEntity.ok(new Result(
                true,
                HttpStatus.OK,
                "TimeEntry Report - Criteria: Task",
                this.taskReport.generateReport(reportContext)
        ));
    }

    @GetMapping("/date")
    @Operation(
            summary = "TimeEntry Report - \"DATE\"",
            description = "Retrieves all the time entries for the specified 'Date'",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful operation",
                            content = @Content(array = @ArraySchema(schema = @Schema(implementation = TimeEntryReport.class)))
                    )
            })
    public ResponseEntity<Result> filterReportByDate(@RequestParam TimePeriod timePeriod) {
        ReportContext reportContext = new ReportContext();
        reportContext.setTimePeriod(timePeriod);
        return ResponseEntity.ok(new Result(
                true,
                HttpStatus.OK,
                "TimeEntry Report - Criteria: Date::LastMonth",
                this.dateBasedReportStrategy.generateReport(reportContext)
        ));
    }

}
