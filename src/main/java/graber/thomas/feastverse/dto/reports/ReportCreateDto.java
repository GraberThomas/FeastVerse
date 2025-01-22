package graber.thomas.feastverse.dto.reports;

import graber.thomas.feastverse.dto.validation.ValidUUID;
import graber.thomas.feastverse.model.report.ReportType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record ReportCreateDto(
        @NotEmpty
        @ValidUUID
        String targetId,
        String message,

        @NotNull
        ReportType type
) {
}
