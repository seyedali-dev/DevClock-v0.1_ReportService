package com.seyed.ali.reportsservice.model.payload;

import com.seyed.ali.reportsservice.model.payload.dto.ProjectDTO;
import com.seyed.ali.reportsservice.model.payload.dto.TimeEntryDTO;
import com.seyed.ali.reportsservice.model.payload.dto.TimeSegmentDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TimeEntryReport {

    private TimeEntryDTO timeEntryDTO;
    private TimeSegmentDTO timeSegmentDTO;
    private ProjectDTO projectDTO;

}
