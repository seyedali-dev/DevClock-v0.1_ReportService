package com.seyed.ali.reportsservice.controller;

import com.seyed.ali.reportsservice.model.payload.response.TimeEntryReport;
import com.seyed.ali.reportsservice.model.payload.response.Result;
import com.seyed.ali.reportsservice.service.interfaces.TimeEntryReportService;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    private final TimeEntryReportService timeEntryReportService;

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
        return ResponseEntity.ok(new Result(
                true,
                HttpStatus.OK,
                "TimeEntry Report - Criteria: Project",
                this.timeEntryReportService.timeEntryReportByProject(projectCriteria)
        ));
    }

}
