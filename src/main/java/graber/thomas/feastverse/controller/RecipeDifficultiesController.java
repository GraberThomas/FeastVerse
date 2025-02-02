package graber.thomas.feastverse.controller;

import graber.thomas.feastverse.model.recipes.RecipeDifficulty;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Stream;

@Tag(name = "Recipes difficulty category", description = "Endpoints for recipes enumerations values")
@RestController
@RequestMapping("/recipes/difficulties")
public class RecipeDifficultiesController {
    @GetMapping
    public List<String> getAllQuantityTypes(){
        return Stream.of(RecipeDifficulty.values()).map(RecipeDifficulty::name).toList();
    }
}
