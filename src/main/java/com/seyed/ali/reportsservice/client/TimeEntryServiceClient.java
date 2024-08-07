package com.seyed.ali.reportsservice.client;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.seyed.ali.reportsservice.model.payload.dto.TimeEntry;
import com.seyed.ali.reportsservice.model.payload.response.Result;
import com.seyed.ali.reportsservice.util.KeycloakSecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.util.List;

@SuppressWarnings("FieldCanBeLocal")
@Slf4j
@Component
public class TimeEntryServiceClient extends ServiceClient{

    private final String timeEntryServiceBaseURL = "http://localhost:8082/api/v1/time/filter"; // TODO: remember to change the host and port when dockerizing the application
    private final ObjectMapper objectMapper;

    public TimeEntryServiceClient(KeycloakSecurityUtil keycloakSecurityUtil, WebClient.Builder webClientBuilder, ObjectMapper objectMapper) {
        super(keycloakSecurityUtil, webClientBuilder);
        this.objectMapper = objectMapper;
    }

    private List<TimeEntry> getTimeEntriesAndConstructToList(String url) {
        Result result = this.sendRequest(url, HttpMethod.GET, new ParameterizedTypeReference<>() {
        });

        JavaType constructedCollectionType = this.objectMapper.getTypeFactory().constructCollectionType(List.class, TimeEntry.class);
        return this.objectMapper.convertValue(result.getData(), constructedCollectionType);
    }

    public List<TimeEntry> getTimeEntryByProject(String projectCriteria) {
        String url = this.timeEntryServiceBaseURL + "/project/" + projectCriteria;
        return getTimeEntriesAndConstructToList(url);
    }

    public List<TimeEntry> getTimeEntryByTask(String taskName) {
        String url = this.timeEntryServiceBaseURL + "/task/" + taskName;
        return getTimeEntriesAndConstructToList(url);
    }

    public List<TimeEntry> getTimeEntriesForLastMonth() {
        String url = this.timeEntryServiceBaseURL + "/date/last-month";
        return getTimeEntriesAndConstructToList(url);
    }

    public List<TimeEntry> getTimeEntriesForLastDay() {
        String url = this.timeEntryServiceBaseURL + "/date/last-day";
        return getTimeEntriesAndConstructToList(url);
    }

    public List<TimeEntry> getTimeEntriesForLastWeek() {
        String url = this.timeEntryServiceBaseURL + "/date/last-week";
        return getTimeEntriesAndConstructToList(url);
    }

    public List<TimeEntry> getTimeEntriesForToday() {
        String url = this.timeEntryServiceBaseURL + "/date/today";
        return getTimeEntriesAndConstructToList(url);
    }

    public List<TimeEntry> getTimeEntriesForSpecifiedDateRange(LocalDate startDate, LocalDate endDate) {
        String url = this.timeEntryServiceBaseURL + "/date/range?startDate=" + startDate + "&endDate=" + endDate;
        return getTimeEntriesAndConstructToList(url);
    }

}
