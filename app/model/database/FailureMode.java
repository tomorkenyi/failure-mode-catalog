package model.database;

import lombok.Data;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Version;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class FailureMode {

    @Id
    private ObjectId id;

    private String functionalState;

    private String serviceEffect;

    private String platformEffect;

    private String potentialCause;

    private Integer probability;

    private String detectFailures;

    private String responseAction;

    private String mitigation;

    private Integer detectability;

    private Boolean safetyConcern;

    @Version
    private Long version;

    private Long lastUpdated;

    private Set<Tag> tags = new HashSet<>();

}
