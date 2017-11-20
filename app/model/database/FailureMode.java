package model.database;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.GenerationType.AUTO;

@Getter
@Setter
@Entity
@NamedQueries({
        @NamedQuery(name = FailureMode.FIND_BY_ID, query = "SELECT DISTINCT fm FROM FailureMode fm WHERE fm.id = :pid"),
        @NamedQuery(name = FailureMode.FIND_ALL, query = "SELECT DISTINCT fm FROM FailureMode fm")
})
public class FailureMode {
    public static final String FIND_BY_ID = "FailureMode.findById";
    public static final String FIND_ALL = "FailureMode.findAll";

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

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

    @ManyToMany(cascade = {PERSIST, MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name = "FailureModeTags",
            joinColumns = {@JoinColumn(name = "failureMode_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "tag_id", referencedColumnName = "id")}
    )
    private Set<Tag> tags = new HashSet<>();

}
