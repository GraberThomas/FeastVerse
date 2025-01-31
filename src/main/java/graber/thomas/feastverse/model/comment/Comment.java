package graber.thomas.feastverse.model.comment;

import graber.thomas.feastverse.model.OwnableEntity;
import graber.thomas.feastverse.model.user.User;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "comments")
public class Comment extends OwnableEntity {
    @Id
    @GeneratedValue
    @ColumnDefault(value = "gen_random_uuid()")
    private UUID id;

    @Column(nullable = false, length = 500)
    private String text;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "parent_id", nullable = false)
    private Commentable parent;

    public Comment() {
        this.createdAt = LocalDateTime.now();
    }

    public Comment(String text, Commentable parent) {
        this.text = text;
        this.parent = parent;
        this.createdAt = LocalDateTime.now();
    }

    public UUID getId() { return id; }
    public String getText() { return text; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}
