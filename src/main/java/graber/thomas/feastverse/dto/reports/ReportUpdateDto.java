package graber.thomas.feastverse.dto.reports;

import graber.thomas.feastverse.dto.validation.ValidUUID;
import graber.thomas.feastverse.model.report.ReportType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.UUID;

public class ReportUpdateDto{
        @Length(max = 1000, message = "Message must not exceed 1000 characters")
        private String message;

        private ReportType type;

        private UUID reporterId;

        private UUID targetId;

        private Boolean resolved;

        private boolean isMessageProvided;
        private boolean isTypeProvided;
        private boolean isReporterIdProvided;
        private boolean isTargetIdProvided;

        public ReportUpdateDto(){}

        public String getMessage() {
                return message;
        }

        public void setMessage(String message) {
                this.message = message;
                this.isMessageProvided = true;
        }

        public ReportType getType() {
                return type;
        }

        public void setType(ReportType type) {
                this.type = type;
                this.isTypeProvided = true;
        }

        public UUID getReporterId() {
                return reporterId;
        }

        public void setReporterId(UUID reporterId) {
                this.reporterId = reporterId;
                this.isReporterIdProvided = true;
        }

        public UUID getTargetId() {
                return targetId;
        }

        public void setTargetId(UUID targetId) {
                this.targetId = targetId;
                this.isTargetIdProvided = true;
        }

        public Boolean getResolved() {
                return resolved;
        }

        public void setResolved(Boolean resolved) {
                this.resolved = resolved;
        }

        public boolean isMessageProvided() {
                return isMessageProvided;
        }

        public boolean isTypeProvided() {
                return isTypeProvided;
        }

        public boolean isReporterIdProvided() {
                return isReporterIdProvided;
        }

        public boolean isTargetIdProvided() {
                return isTargetIdProvided;
        }
}

