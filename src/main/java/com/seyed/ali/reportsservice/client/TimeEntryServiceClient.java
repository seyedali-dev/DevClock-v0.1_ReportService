package com.seyed.ali.reportsservice.client;

import com.seyed.ali.reportsservice.util.KeycloakSecurityUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class TimeEntryServiceClient extends ServiceClient{

    private final String projectServiceBaseURL = "http://localhost:8082/api/v1/time"; // TODO: remember to change the host and port when dockerizing the application

    public TimeEntryServiceClient(KeycloakSecurityUtil keycloakSecurityUtil, WebClient.Builder webClientBuilder) {
        super(keycloakSecurityUtil, webClientBuilder);
    }

}
