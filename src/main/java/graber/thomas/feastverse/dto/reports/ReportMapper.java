package graber.thomas.feastverse.dto.reports;

import graber.thomas.feastverse.model.recipes.Recipe;
import graber.thomas.feastverse.model.recipes.RecipeStep;
import graber.thomas.feastverse.model.report.Report;
import graber.thomas.feastverse.model.report.Reportable;
import graber.thomas.feastverse.model.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReportMapper {

    @Mapping(source = "reporter.id", target = "reporterId")
    @Mapping(source = "target.id", target = "targetId")
    @Mapping(target = "targetType", expression = "java(getTargetType(report.getTarget()))")
    ReportViewDTO toReportView(Report report);

    default String getTargetType(Reportable target) {
        if (target == null) {
            return null;
        }
        if (target instanceof User) {
            return "USER";
        }else if(target instanceof Recipe){
            return "RECIPE";
        }else if(target instanceof RecipeStep){
            return "RECIPE_STEP";
        }
        return "UNKNOWN";
    }
}
