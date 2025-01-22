package graber.thomas.feastverse.model.report;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Reportable {
    @Id
    @GeneratedValue
    @ColumnDefault(value = "gen_random_uuid()")
    private UUID id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
