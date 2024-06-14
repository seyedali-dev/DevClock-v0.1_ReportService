package com.seyed.ali.reportsservice.model.payload.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.io.Serializable;

/**
 * DTO for {@link com.seyed.ali.timeentryservice.model.domain.TimeSegment} in {@code TimeEntry-Service}
 */
@SuppressWarnings("JavadocReference")
@Schema
@Builder
public class TimeSegmentDTO implements Serializable {

    @Schema(description = "Unique identifier for the time segment", example = "12345")
    private String timeSegmentId;

    @Schema(description = "Start time of the time entry in the format yyyy-MM-dd HH:mm", example = "2024-05-12 08:00:00")
    private String startTime;

    @Schema(description = "End time of the time entry in the format yyyy-MM-dd HH:mm", example = "2024-05-12 10:00:00")
    private String endTime;

    @Schema(description = "Duration of the time entry in the format HH:mm:ss", example = "02:00:00")
    private String duration;

    @Schema(description = "Unique identifier for the user associated with time entry", example = "12345")
    private String userId;

}