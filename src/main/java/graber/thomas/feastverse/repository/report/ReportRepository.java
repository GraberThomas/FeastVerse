package graber.thomas.feastverse.repository.report;

import graber.thomas.feastverse.model.report.Report;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface ReportRepository extends JpaRepository<Report, UUID>, JpaSpecificationExecutor<Report> {
    Page<Report> findAllByTargetId(UUID targetId, Pageable pageable);
}
