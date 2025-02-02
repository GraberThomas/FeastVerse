package graber.thomas.feastverse.service.report;

import graber.thomas.feastverse.dto.reports.ReportCreateDto;
import graber.thomas.feastverse.dto.reports.ReportUpdateDto;
import graber.thomas.feastverse.exception.ForbiddenActionException;
import graber.thomas.feastverse.exception.InvalidNullProperty;
import graber.thomas.feastverse.exception.SelfReportingException;
import graber.thomas.feastverse.model.report.Report;
import graber.thomas.feastverse.model.report.ReportType;
import graber.thomas.feastverse.model.report.Reportable;
import graber.thomas.feastverse.model.user.User;
import graber.thomas.feastverse.model.user.UserType;
import graber.thomas.feastverse.repository.report.ReportRepository;
import graber.thomas.feastverse.repository.report.ReportSpecifications;
import graber.thomas.feastverse.repository.report.ReportableRepository;
import graber.thomas.feastverse.service.security.SecurityService;
import graber.thomas.feastverse.service.user.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;
    private final SecurityService securityService;
    private final UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(ReportServiceImpl.class);
    private final ReportableRepository reportableRepository;

    public ReportServiceImpl(ReportRepository reportRepository, SecurityService securityService, UserService userService, ReportableRepository reportableRepository) {
        this.reportRepository = reportRepository;
        this.securityService = securityService;
        this.userService = userService;
        this.reportableRepository = reportableRepository;
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
    public Optional<Report> create(ReportCreateDto dto) {
        UUID reporterId = securityService.getCurrentUserId();

        if (dto.targetId().equals(reporterId)) {
            throw new SelfReportingException("Users cannot report themselves.");
        }

        User reporter = userService.getById(reporterId).orElseThrow(() ->
                new EntityNotFoundException("Reporter not found for ID: " + reporterId)
        );

        Reportable target = this.getReportable(dto.targetId()).orElseThrow(() ->
                new EntityNotFoundException("Target not found for ID: " + dto.targetId())
        );
        
        Report report = new Report();
        report.setTarget(target);
        report.setReporter(reporter);
        report.setType(dto.type());
        report.setCreatedDate(LocalDate.now());
        report.setResolved(false);
        report.setMessage(dto.message());

        return Optional.of(reportRepository.save(report));
    }

    @Override
    public Optional<Report> update(UUID reportId, ReportUpdateDto reportUpdateDto) {
        logger.info("Updating report with ID: {}. Update details: message={}, messageProvided={}, type={}, reporterId={}, targetId={}, resolved={}",
                reportId,
                reportUpdateDto.getMessage(),
                reportUpdateDto.isMessageProvided(),
                reportUpdateDto.getType(),
                reportUpdateDto.getReporterId(),
                reportUpdateDto.getTargetId(),
                reportUpdateDto.getResolved()
        );

        if(securityService.hasRole(UserType.MODERATOR)) {
            if(
                    reportUpdateDto.isTargetIdProvided() ||
                    reportUpdateDto.isReporterIdProvided() ||
                    reportUpdateDto.isTypeProvided() ||
                    reportUpdateDto.isMessageProvided()
            ){
                throw new ForbiddenActionException("Moderator can only modify resolved status.");
            }
        }

        Report report = reportRepository.findById(reportId).orElseThrow(
                () -> new EntityNotFoundException("Report not found for ID: " + reportId)
        );

        if (reportUpdateDto.isResolvedProvided()) {
            if(reportUpdateDto.getResolved() == null){
                throw new InvalidNullProperty("resolved");
            }
            report.setResolved(reportUpdateDto.getResolved());
        }

        if (reportUpdateDto.isMessageProvided()) {
            report.setMessage(reportUpdateDto.getMessage());
        }

        if (reportUpdateDto.isTypeProvided()) {
            report.setType(reportUpdateDto.getType());
        }

        if (reportUpdateDto.isReporterIdProvided()) {
            User reporter = userService.getById(reportUpdateDto.getReporterId()).orElseThrow(
                    () -> new EntityNotFoundException("Reporter not found for ID: " + reportUpdateDto.getReporterId())
            );
            report.setReporter(reporter);
        }

        if (reportUpdateDto.isTargetIdProvided()) {
            Reportable target = this.getReportable(reportUpdateDto.getTargetId()).orElseThrow(
                    () -> new EntityNotFoundException("Target not found for ID: " + reportUpdateDto.getTargetId())
            );
            report.setTarget(target);
        }

        report.setUpdatedDate(LocalDate.now());

        return Optional.of(reportRepository.save(report));
    }

    @Override
    public void delete(UUID id) {

    }

    @Override
    public Page<Report> getAll(Boolean resolved, ReportType type, UUID targetId, User reporter, Pageable pageable) {
        Specification<Report> spec = Specification.where(ReportSpecifications.isResolved(resolved))
                .and(ReportSpecifications.hasType(type))
                .and(ReportSpecifications.hasTargetId(targetId))
                .and(ReportSpecifications.hasReporter(reporter));

        return reportRepository.findAll(spec, pageable);
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
        if(targetId == null){
            throw new InvalidNullProperty("targetId");
        }
        User user = userService.getById(targetId).orElseThrow(
                () -> new EntityNotFoundException("User not found for ID: " + targetId)
        );

        return reportRepository.findAllByTargetId(targetId, pageable);
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

    @Override
    public Optional<Reportable> getReportable(UUID id) {
        return reportableRepository.findById(id);
    }
}
