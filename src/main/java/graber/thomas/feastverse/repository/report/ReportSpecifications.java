package graber.thomas.feastverse.repository.report;

import graber.thomas.feastverse.model.report.Report;
import graber.thomas.feastverse.model.report.ReportType;
import graber.thomas.feastverse.model.user.User;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public class ReportSpecifications {

    public static Specification<Report> isResolved(Boolean resolved) {
        return (root, query, criteriaBuilder) -> {
            if (resolved == null) {
                return criteriaBuilder.conjunction();
            }
            return resolved
                    ? criteriaBuilder.isTrue(root.get("resolved"))
                    : criteriaBuilder.isFalse(root.get("resolved"));
        };
    }

    public static Specification<Report> hasType(ReportType type) {
        return (root, query, criteriaBuilder) -> {
            if (type == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("type"), type);
        };
    }

    public static Specification<Report> hasReporter(User reporter) {
        return (root, query, criteriaBuilder) -> {
            if (reporter == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("reporter"), reporter);
        };
    }

    public static Specification<Report> hasTargetId(UUID targetId) {
        return (root, query, criteriaBuilder) -> {
            if (targetId == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("target").get("id"), targetId);
        };
    }
}
