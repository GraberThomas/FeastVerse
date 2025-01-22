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
    public List<Report> getAll() {
        return List.of();
    }

    @Override
    public List<Report> getUnresolvedReports() {
        return List.of();
    }

    @Override
    public List<Report> getResolvedReports() {
        return List.of();
    }

    @Override
    public List<Report> getByType(ReportType type) {
        return List.of();
    }

    @Override
    public List<Report> getByTarget(UUID targetId) {
        return List.of();
    }

    @Override
    public List<Report> getByReporter(UUID reporterId) {
        return List.of();
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
