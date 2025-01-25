package graber.thomas.feastverse.model.report;

import graber.thomas.feastverse.model.user.User;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "reports")
public class Report {
    @Id
    @GeneratedValue
    @ColumnDefault(value = "gen_random_uuid()")
    private UUID id;

    @Column(length = 1000)
    private String message;

    @Column(nullable = false)
    private ReportType type;

    @ManyToOne
    @JoinColumn(name = "reportable_id", nullable = false)
    private Reportable target;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", nullable = false)
    private User reporter;

    @Column(nullable = false)
    private boolean resolved;

    private LocalDate createdDate;
    private LocalDate updatedDate;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ReportType getType() {
        return type;
    }

    public void setType(ReportType type) {
        this.type = type;
    }

    public User getReporter() {
        return reporter;
    }

    public void setReporter(User reporter) {
        this.reporter = reporter;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Reportable getTarget() {
        return target;
    }

    public void setTarget(Reportable target) {
        this.target = target;
    }

    public boolean isResolved() {
        return resolved;
    }

    public void setResolved(boolean resolved) {
        this.resolved = resolved;
    }
}
