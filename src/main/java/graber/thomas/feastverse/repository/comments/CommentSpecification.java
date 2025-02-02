package graber.thomas.feastverse.repository.comments;

import graber.thomas.feastverse.model.comment.Comment;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public class CommentSpecification {

    public static Specification<Comment> isDeleted() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("isDeleted"), true);
    }

    public static Specification<Comment> isNotDeleted() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("isDeleted"), false);
    }

    public static Specification<Comment> hasOwner(UUID ownerId) {
        return (root, query, criteriaBuilder) -> {
            if (ownerId == null) {
                return criteriaBuilder.conjunction(); // Renvoie une condition toujours vraie
            }
            return criteriaBuilder.equal(root.get("owner").get("id"), ownerId);
        };
    }

    public static Specification<Comment> hasParent(UUID parentId) {
        return (root, query, criteriaBuilder) -> {
            if (parentId == null) {
                return criteriaBuilder.conjunction(); // Renvoie une condition toujours vraie
            }
            return criteriaBuilder.equal(root.get("parent").get("id"), parentId); // Correction ici
        };
    }
}
