package graber.thomas.feastverse.service.report;

import graber.thomas.feastverse.dto.reports.ReportCreateDto;
import graber.thomas.feastverse.model.report.Report;
import graber.thomas.feastverse.model.report.ReportType;
import graber.thomas.feastverse.model.report.Reportable;
import graber.thomas.feastverse.model.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ReportService {

    List<ReportType> getReportTypes();

    Optional<Report> get(UUID id);


    public Optional<Report> create(ReportCreateDto reportCreateDto, User reporter , Reportable reportableTarget);


    Optional<Report> update(UUID id, Report report);


    void delete(UUID id);


    Page<Report> getAll(Boolean resolved, ReportType type, UUID targetId, User reporter, Pageable pageable);


    Page<Report> getUnresolvedReports(Pageable pageable);

    Page<Report> getResolvedReports(Pageable pageable);


    Page<Report> getByType(Pageable pageable, ReportType type);


    Page<Report> getByTarget(Pageable pageable, UUID targetId);


    Page<Report> getByReporter(Pageable pageable, UUID reporterId);

    Report markAsResolved(UUID id);

    Report markAsUnresolved(UUID id);
}
