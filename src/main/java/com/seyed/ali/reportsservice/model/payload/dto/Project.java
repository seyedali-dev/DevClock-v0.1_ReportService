package com.seyed.ali.reportsservice.model.payload.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * DTO for {@link com.seyed.ali.projectservice.model.domain.Project} in {@code Project-Service}
 */
@SuppressWarnings({"JavadocReference", "unused"})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Project implements Serializable {

        @Schema(requiredMode = Schema.RequiredMode.NOT_REQUIRED, description = "Unique identifier for the project", example = "12345")
        private String projectId;

        @Schema(requiredMode = Schema.RequiredMode.NOT_REQUIRED, description = "The project name", example = "Microservices-Springboot")
        private String projectName;

        @Schema(requiredMode = Schema.RequiredMode.NOT_REQUIRED, description = "The project name", example = "Learning microservices is really exciting and HARD ;)")
        private String projectDescription;

        @Schema(requiredMode = Schema.RequiredMode.NOT_REQUIRED, description = "The task associated with the project", implementation = Task.class)
        private List<Task> task = new ArrayList<>();

        public Project(String projectId, String projectName, String projectDescription) {
                this.projectId = projectId;
                this.projectName = projectName;
                this.projectDescription = projectDescription;
                this.task = new ArrayList<>();
        }

}