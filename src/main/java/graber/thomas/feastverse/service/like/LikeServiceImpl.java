package graber.thomas.feastverse.service.like;

import graber.thomas.feastverse.model.like.RecipeLike;
import graber.thomas.feastverse.model.recipes.Recipe;
import graber.thomas.feastverse.model.user.User;
import graber.thomas.feastverse.repository.recipeLike.RecipeLikeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class LikeServiceImpl implements LikeService {
    private final RecipeLikeRepository recipeLikeRepository;

    public LikeServiceImpl(RecipeLikeRepository recipeLikeRepository) {
        this.recipeLikeRepository = recipeLikeRepository;
    }

    @Override
    public Optional<RecipeLike> addLikeToRecipe(Recipe recipe, User user) {
        Optional<RecipeLike> existingLike = recipeLikeRepository.findByRecipeAndUser(recipe, user);

        if (existingLike.isPresent()) {
            return existingLike;
        }

        RecipeLike newLike = new RecipeLike();
        newLike.setRecipe(recipe);
        newLike.setUser(user);
        newLike.setLikedAt(LocalDateTime.now());

        return Optional.of(recipeLikeRepository.save(newLike));
    }


    @Override
    public void deleteLikeFromRecipe(Recipe recipe, User user) {
        Optional<RecipeLike> existingLike = recipeLikeRepository.findByRecipeAndUser(recipe, user);

        if (existingLike.isEmpty()) {
            return;
        }

        recipeLikeRepository.delete(existingLike.get());
    }

    @Transactional(readOnly = true)
    public Page<RecipeLike> getLikesForUser(User user, Pageable pageable) {
        return recipeLikeRepository.findAllByUser(user, pageable);
    }

}
