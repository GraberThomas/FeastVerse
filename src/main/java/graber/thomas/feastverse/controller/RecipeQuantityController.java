package graber.thomas.feastverse.controller;

import graber.thomas.feastverse.model.recipes.QuantityState;
import graber.thomas.feastverse.model.recipes.QuantityType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Stream;

@Tag(name = "Recipes quantity categories", description = "Endpoints for recipes enumerations values")
@RestController
@RequestMapping("/recipes/quantities")
public class RecipeQuantityController {

    @Operation(
            summary = "Get all categories of quantity units",
            description = "Get all categories of quantity units",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Quantity units retrieved successfully.",
                            content = @io.swagger.v3.oas.annotations.media.Content(
                                    examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                                            "[\n" +
                                                    "  \"G\",\n" +
                                                    "  \"MG\",\n" +
                                                    "  \"LITER\",\n" +
                                                    "  \"BOTTLE\",\n" +
                                                    "  \"PINCH\"\n" +
                                                    "  \"...\"\n" +
                                                    "]"
                                    )
                            )
                    ),
            }
    )
    @GetMapping("/types")
    public List<String> getAllQuantityTypes(){
        return Stream.of(QuantityType.values()).map(QuantityType::name).toList();
    }

    @Operation(
            summary = "Get all categories of quantity states",
            description = "Get all categories of quantity states",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Quantity states retrieved successfully.",
                            content = @io.swagger.v3.oas.annotations.media.Content(
                                    examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                                            "[\n" +
                                                    "  \"WHOLE\",\n" +
                                                    "  \"CHOPPED\",\n" +
                                                    "  \"DICED\",\n" +
                                                    "  \"MINCED\",\n" +
                                                    "  \"GRATED\"\n" +
                                                    "  \"...\"\n" +
                                                    "]"
                                    )
                            )
                    ),
            }
    )
    @GetMapping("/states")
    public List<String> getAllQuantitySates(){
        return Stream.of(QuantityState.values()).map(QuantityState::name).toList();
    }
}
