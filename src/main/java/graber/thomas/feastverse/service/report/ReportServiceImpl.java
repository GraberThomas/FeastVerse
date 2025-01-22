package graber.thomas.feastverse.service.report;

import graber.thomas.feastverse.dto.reports.ReportCreateDto;
import graber.thomas.feastverse.model.report.Report;
import graber.thomas.feastverse.model.report.ReportType;
import graber.thomas.feastverse.model.report.Reportable;
import graber.thomas.feastverse.model.user.User;
import graber.thomas.feastverse.repository.report.ReportRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;

    public ReportServiceImpl(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @Override
    public List<ReportType> getReportTypes() {
        return List.of(ReportType.values());
    }

    @Override
    public Optional<Report> get(UUID id) {
        return this.reportRepository.findById(id);
    }

    @Override
    public Optional<Report> create(ReportCreateDto reportCreateDto, User reporter ,Reportable reportableTarget) {
        Report report = new Report();
        report.setTarget(reportableTarget);
        report.setReporter(reporter);
        report.setType(reportCreateDto.type());
        report.setCreatedDate(LocalDate.now());
        report.setResolved(false);
        report.setMessage(reportCreateDto.message());
        return Optional.of(reportRepository.save(report));
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
