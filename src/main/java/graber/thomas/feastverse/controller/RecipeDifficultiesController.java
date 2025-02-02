package graber.thomas.feastverse.controller;

import graber.thomas.feastverse.model.recipes.RecipeDifficulty;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

@Tag(name = "Recipes difficulty category", description = "Endpoints for recipes enumerations values")
@RestController
@RequestMapping("/recipes/difficulties")
public class RecipeDifficultiesController {

    @Operation(
            summary = "Get all categories of difficulties",
            description = "Get all categories of difficulties",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Categories retrieved successfully.",
                            content = @io.swagger.v3.oas.annotations.media.Content(
                                    examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                                            "[\n" +
                                                    "  \"VERY_EASY\",\n" +
                                                    "  \"EASY\",\n" +
                                                    "  \"MEDIUM\",\n" +
                                                    "  \"HARD\",\n" +
                                                    "  \"VERY_HARD\"\n" +
                                                    "]"
                                    )
                            )
                    ),
            }
    )
    @GetMapping
    public List<String> getAllDifficultiesTypes(){
        return Stream.of(RecipeDifficulty.values()).map(RecipeDifficulty::name).toList();
    }
}
