package graber.thomas.feastverse.service.report;

import graber.thomas.feastverse.model.report.Report;
import graber.thomas.feastverse.model.report.ReportType;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ReportService {

    Optional<Report> get(UUID id);


    Optional<Report> create(Report report);


    Optional<Report> update(UUID id, Report report);


    void delete(UUID id);


    List<Report> getAll();


    List<Report> getUnresolvedReports();

    List<Report> getResolvedReports();


    List<Report> getByType(ReportType type);


    List<Report> getByTarget(UUID targetId);


    List<Report> getByReporter(UUID reporterId);

    Report markAsResolved(UUID id);

    Report markAsUnresolved(UUID id);
}
