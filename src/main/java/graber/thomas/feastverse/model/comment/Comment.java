package graber.thomas.feastverse.model.comment;

import graber.thomas.feastverse.model.OwnableEntity;
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

    public void setId(UUID id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Commentable getParent() {
        return parent;
    }

    public void setParent(Commentable parent) {
        this.parent = parent;
    }
}
