package graber.thomas.feastverse.service.like;

import graber.thomas.feastverse.model.like.RecipeLike;
import graber.thomas.feastverse.model.recipes.Recipe;
import graber.thomas.feastverse.model.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface LikeService {
    Optional<RecipeLike> addLikeToRecipe(Recipe recipe, User user);

    void deleteLikeFromRecipe(Recipe recipe, User user);

    Page<RecipeLike> getLikesForUser(User user, Pageable pageable);
}
