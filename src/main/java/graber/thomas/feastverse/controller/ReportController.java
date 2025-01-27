package graber.thomas.feastverse.controller;

import graber.thomas.feastverse.dto.reports.ReportCreateDto;
import graber.thomas.feastverse.dto.reports.ReportUpdateDto;
import graber.thomas.feastverse.dto.reports.ReportViewDTO;
import graber.thomas.feastverse.dto.reports.ReportMapper;
import graber.thomas.feastverse.model.report.Report;
import graber.thomas.feastverse.model.report.ReportType;
import graber.thomas.feastverse.model.user.User;
import graber.thomas.feastverse.service.report.ReportService;
import graber.thomas.feastverse.service.security.SecurityService;
import graber.thomas.feastverse.service.user.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@Tag(name = "Report", description = "Endpoints for reports")
@RestController
@RequestMapping("/reports")
public class ReportController {
    private static final Logger logger = LoggerFactory.getLogger(ReportController.class);
    private final ReportService reportService;
    private final ReportMapper reportMapper;
    private final UserService userService;

    public ReportController(ReportService reportService, SecurityService securityService, ReportMapper reportMapper, UserService userService) {
        this.reportService = reportService;
        this.reportMapper = reportMapper;
        this.userService = userService;
    }


    @PreAuthorize("hasAnyRole('ROLE_MODERATOR', 'ROLE_ADMINISTRATOR')")
    @GetMapping
    public Page<ReportViewDTO> getAll(
            @RequestParam(required = false) Boolean resolved,
            @RequestParam(required = false) ReportType type,
            @RequestParam(required = false) UUID targetId,
            @RequestParam(required = false) UUID reporterId,
            Pageable pageable
    ) {
        User reporter = reporterId != null ? userService.getById(reporterId).orElse(null) : null;
        return reportService.getAll(resolved, type, targetId, reporter, pageable).map(reportMapper::toReportView);
    }

    @PreAuthorize("hasAnyRole('ROLE_MODERATOR', 'ROLE_ADMINISTRATOR')")
    @GetMapping("/{reportId}")
    public ReportViewDTO getById(@PathVariable UUID reportId) {
        return reportMapper.toReportView(
                reportService.get(reportId).orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Report not found for ID: " + reportId))
        );
    }


    @GetMapping("/types")
    public List<ReportType> getReportTypes() {
        return reportService.getReportTypes();
    }

    @PreAuthorize("hasRole('ROLE_STANDARD')")
    @PostMapping
    public ResponseEntity<Void> createReport(@Valid @RequestBody ReportCreateDto reportCreateDto) {
        logger.info("Received request to create report with targetId: {} and type: {}",
                reportCreateDto.targetId(), reportCreateDto.type());

        Report newReport = reportService.create(reportCreateDto).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to create report.")
        );

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newReport.getId())
                .toUri();

        logger.info("Report created successfully with ID: {}. Returning HTTP 201 Created.", newReport.getId());
        return ResponseEntity.created(location).build();
    }

    @PreAuthorize("hasAnyRole('ROLE_MODERATOR', 'ROLE_ADMINISTRATOR')")
    @PatchMapping("/{reportId}")
    public ReportViewDTO updateReport(@Valid @RequestBody ReportUpdateDto reportUpdateDto, @PathVariable UUID reportId) {

        Report updatedReport = reportService.update(reportId, reportUpdateDto).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to update report.")
        );

        return reportMapper.toReportView(updatedReport);

    }

    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    @DeleteMapping("/{reportId}")
    public ResponseEntity<Void> deleteReport(@PathVariable UUID reportId) {
        reportService.delete(reportId);
        return ResponseEntity.noContent().build();
    }
}
