package model.database;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"text"})})
@NamedQuery(name = Tag.FIND_BY_TEXT, query = "SELECT t FROM Tag t WHERE t.text = :ptext")
public class Tag {

    public static final String FIND_BY_TEXT = "Tag.findByText";

    @Id
    @GeneratedValue(strategy = AUTO)
    @JsonIgnore
    private Long id;

    private String text;

    private String colorCode;

    @ManyToMany(mappedBy = "tags", cascade = {PERSIST, MERGE}, fetch = FetchType.EAGER)
    private Set<FailureMode> failureModes = new HashSet<>();

}
