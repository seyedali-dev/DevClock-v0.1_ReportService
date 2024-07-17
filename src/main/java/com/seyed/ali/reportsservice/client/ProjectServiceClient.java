package com.seyed.ali.reportsservice.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seyed.ali.reportsservice.model.payload.dto.Project;
import com.seyed.ali.reportsservice.model.payload.response.Result;
import com.seyed.ali.reportsservice.util.KeycloakSecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@SuppressWarnings({"FieldCanBeLocal"})
@Slf4j
@Component
public class ProjectServiceClient extends ServiceClient {

    private final String timeEntryServiceBaseURL = "http://localhost:8083/api/v1/project"; // TODO: remember to change the host and port when dockerizing the application
    private final ObjectMapper objectMapper;

    public ProjectServiceClient(KeycloakSecurityUtil keycloakSecurityUtil, WebClient.Builder webClientBuilder, ObjectMapper objectMapper) {
        super(keycloakSecurityUtil, webClientBuilder);
        this.objectMapper = objectMapper;
    }

    public Project getProjectById(String projectId) {
        String url = this.timeEntryServiceBaseURL + "/" + projectId;
        Result result = this.sendRequest(url, HttpMethod.GET, new ParameterizedTypeReference<>() {
        });
        return this.objectMapper.convertValue(result.getData(), Project.class);
    }

}
