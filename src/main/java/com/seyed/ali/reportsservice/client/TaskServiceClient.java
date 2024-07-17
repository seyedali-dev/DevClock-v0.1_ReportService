package com.seyed.ali.reportsservice.client;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.seyed.ali.reportsservice.model.payload.dto.Task;
import com.seyed.ali.reportsservice.model.payload.response.Result;
import com.seyed.ali.reportsservice.util.KeycloakSecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@SuppressWarnings("FieldCanBeLocal")
@Slf4j
@Component
public class TaskServiceClient extends ServiceClient{

    private final String taskServiceBaseURL = "http://localhost:8084/api/v1/task"; // TODO: remember to change the host and port when dockerizing the application
    private final ObjectMapper objectMapper;

    public TaskServiceClient(KeycloakSecurityUtil keycloakSecurityUtil, WebClient.Builder webClientBuilder, ObjectMapper objectMapper) {
        super(keycloakSecurityUtil, webClientBuilder);
        this.objectMapper = objectMapper;
    }

    public List<Task> findAllTasksForProject(String projectId) {
        String url = this.taskServiceBaseURL + "/" + projectId;
        Result result = this.sendRequest(url, HttpMethod.GET, new ParameterizedTypeReference<>() {
        });

        JavaType constructedCollectionType = this.objectMapper.getTypeFactory().constructCollectionType(List.class, Task.class);
        return this.objectMapper.convertValue(result.getData(), constructedCollectionType);
    }

}
