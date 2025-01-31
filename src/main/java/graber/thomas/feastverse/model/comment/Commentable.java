package graber.thomas.feastverse.model.comment;

import graber.thomas.feastverse.model.report.Reportable;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Commentable extends Reportable {
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    public List<Comment> getComments() { return comments; }
    public void addComment(Comment comment) { comments.add(comment); }
}
