package graber.thomas.feastverse.model.comment;

import graber.thomas.feastverse.model.OwnableEntity;
import graber.thomas.feastverse.model.user.User;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Formula;

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
    private String content;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "parent_id", nullable = false)
    private Commentable parent;

    @Formula("(select count(r.id) from reports r where r.reportable_id = id)")
    private int reportCount;

    public Comment() {
        this.createdAt = LocalDateTime.now();
        this.setPublic(true);
    }

    public Comment(String content, Commentable parent, User owner) {
        this.content = content;
        this.parent = parent;
        this.createdAt = LocalDateTime.now();
        this.setOwner(owner);
        this.setPublic(true);
    }

    public UUID getId() { return id; }
    public String getContent() { return content; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setContent(String text) {
        this.content = text;
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
