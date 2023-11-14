package org.example.controller.request;

import lombok.Data;
import org.example.model.ExportStrategyName;
import org.example.service.ExportStrategy;

import javax.validation.constraints.NotNull;

@Data
public class ExportRequest {
    @NotNull
    private ExportStrategyName strategy;
}
