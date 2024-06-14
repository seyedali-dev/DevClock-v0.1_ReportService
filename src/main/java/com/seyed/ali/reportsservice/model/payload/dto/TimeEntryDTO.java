package com.seyed.ali.reportsservice.model.payload.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link com.seyed.ali.timeentryservice.model.domain.TimeEntry} in {@code TimeEntry-Service}
 */
@SuppressWarnings("JavadocReference")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TimeEntryDTO implements Serializable {

    @Schema(description = "Unique identifier for the time entry", example = "12345")
    private String timeEntryId;

    @Schema(description = "Start time of the time entry in the format yyyy-MM-dd HH:mm", example = "2024-05-12 08:00:00")
    private String startTime;

    @Schema(description = "End time of the time entry in the format yyyy-MM-dd HH:mm", example = "2024-05-12 10:00:00")
    private String endTime;

    @Schema(description = "A flag determining this time entry is billable", example = "true")
    boolean billable;

    @Schema(description = "The hourly rate in BigDecimal format", example = "10.0")
    private String hourlyRate;

    @Schema(description = "Duration of the time entry in the format HH:mm:ss", example = "02:00:00")
    private String duration;

    @Schema(description = "Unique identifier for the associated time entry", example = "12345")
    private String projectId;

    @Schema(description = "Unique identifier for the task to assign the time entry with", example = "12345")
    private String taskId;

}
