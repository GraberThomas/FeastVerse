package graber.thomas.feastverse.repository.report;

import graber.thomas.feastverse.model.report.Reportable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ReportableRepository extends JpaRepository<Reportable, UUID> {
}
