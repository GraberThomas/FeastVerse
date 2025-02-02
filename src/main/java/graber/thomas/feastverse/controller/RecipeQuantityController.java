package graber.thomas.feastverse.controller;

import graber.thomas.feastverse.model.recipes.QuantityState;
import graber.thomas.feastverse.model.recipes.QuantityType;
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
    @GetMapping("/types")
    public List<String> getAllQuantityTypes(){
        return Stream.of(QuantityType.values()).map(QuantityType::name).toList();
    }

    @GetMapping("/states")
    public List<String> getAllQuantitySates(){
        return Stream.of(QuantityState.values()).map(QuantityState::name).toList();
    }
}
