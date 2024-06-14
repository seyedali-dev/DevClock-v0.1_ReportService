package com.seyed.ali.reportsservice.model.payload.response;

import com.seyed.ali.reportsservice.model.payload.dto.TimeSegmentDTO;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TimeEntryResponse implements Serializable {

    @Schema(description = "Unique identifier for the time entry", example = "12345")
    private String timeEntryId;

    @ArraySchema(schema = @Schema(implementation = TimeSegmentDTO.class))
    private List<TimeSegmentDTO> timeSegmentDTOList;

    @Schema(description = "A flag determining this time entry is billable", example = "true")
    private boolean billable;

    @Schema(description = "The hourly rate in BigDecimal format", example = "10.0")
    private String hourlyRate;

    @Schema(description = "Total time recorded", example = "00:00:18")
    private String totalDuration;

    @Schema(description = "Unique identifier for associated project with the time entry", example = "12345")
    private String projectId;

    @Schema(description = "Unique identifier for the task to associate the task with time entry", example = "12345")
    private String taskId;

}
