package graber.thomas.feastverse.dto.reports;

import graber.thomas.feastverse.model.report.ReportType;

import java.util.UUID;

public record ReportViewDTO(
        UUID id,
        String message,
        ReportType type,
        UUID reporterId,
        UUID targetId,
        String targetType
) {}