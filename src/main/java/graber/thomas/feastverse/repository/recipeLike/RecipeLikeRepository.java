package graber.thomas.feastverse.repository.recipeLike;

import graber.thomas.feastverse.model.like.RecipeLike;
import graber.thomas.feastverse.model.like.RecipeLikeId;
import graber.thomas.feastverse.model.recipes.Recipe;
import graber.thomas.feastverse.model.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecipeLikeRepository extends JpaRepository<RecipeLike, RecipeLikeId> {
    Optional<RecipeLike> findByRecipeAndUser(Recipe recipe, User user);

    Page<RecipeLike> findAllByUser(User user, Pageable pageable);
}
