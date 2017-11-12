package model.database;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.GenerationType.AUTO;

@Data
@Entity
@NamedQueries({
        @NamedQuery(name = FailureMode.FIND_BY_ID, query = "SELECT fm FROM FailureMode fm LEFT JOIN FETCH fm.tags WHERE fm.id = :pid"),
        @NamedQuery(name = FailureMode.FIND_ALL, query = "SELECT fm FROM FailureMode fm LEFT JOIN FETCH fm.tags")
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

    @ManyToMany(cascade = {PERSIST, MERGE})
    @JoinTable(name = "FailureModeTags",
            joinColumns = {@JoinColumn(name = "failureMode_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "tag_id", referencedColumnName = "id")}
    )
    private List<Tag> tags = new ArrayList<>();


}
