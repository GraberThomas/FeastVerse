package graber.thomas.feastverse.repository.comments;

import graber.thomas.feastverse.model.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CommentRepository  extends JpaRepository<Comment, UUID>, JpaSpecificationExecutor<Comment> {
}
