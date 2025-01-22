package graber.thomas.feastverse.service.report;

import graber.thomas.feastverse.model.report.Report;
import graber.thomas.feastverse.model.report.ReportType;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ReportServiceImpl implements ReportService {

    @Override
    public Optional<Report> get(UUID id) {
        return Optional.empty();
    }

    @Override
    public Optional<Report> create(Report report) {
        return Optional.empty();
    }

    @Override
    public Optional<Report> update(UUID id, Report report) {
        return Optional.empty();
    }

    @Override
    public void delete(UUID id) {

    }

    @Override
    public Page<Report> getAll(Pageable pageable) {
        return this.reportRepository.findAll(pageable);
    }

    @Override
    public Page<Report> getUnresolvedReports(Pageable pageable) {
        return null;
    }

    @Override
    public Page<Report> getResolvedReports(Pageable pageable) {
        return null;
    }

    @Override
    public Page<Report> getByType(Pageable pageable, ReportType type) {
        return null;
    }

    @Override
    public Page<Report> getByTarget(Pageable pageable, UUID targetId) {
        return null;
    }

    @Override
    public Page<Report> getByReporter(Pageable pageable, UUID reporterId) {
        return null;
    }

    @Override
    public Report markAsResolved(UUID id) {
        return null;
    }

    @Override
    public Report markAsUnresolved(UUID id) {
        return null;
    }
}
