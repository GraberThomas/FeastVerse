package graber.thomas.feastverse.dto.reports;

import graber.thomas.feastverse.model.report.ReportType;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.UUID;

public record ReportCreateDto(
        @NotNull
        UUID targetId,

        @Length(max = 1000)
        String message,

        @NotNull
        ReportType type
) {
}
