package com.seyed.ali.reportsservice.model.payload.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TimeEntry implements Serializable {

    private String timeEntryId;
    private boolean billable = false;
    private BigDecimal hourlyRate = BigDecimal.ZERO;

    @JsonManagedReference // this is the forward part of the relationship – the one that gets serialized normally
    private List<TimeSegment> timeSegmentList = new ArrayList<>();

    private String userId;
    private String projectId;
    private String taskId;

}
