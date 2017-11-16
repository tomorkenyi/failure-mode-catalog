package model.database;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.GenerationType.AUTO;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"text"})})
@NamedQuery(name = Tag.FIND_BY_TEXT, query = "SELECT t FROM Tag t WHERE t.text = :ptext")
public class Tag {

    public static final String FIND_BY_TEXT = "Tag.findByText";

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    private String text;

    private String colorCode;

    @ManyToMany(mappedBy = "tags", cascade = {PERSIST, MERGE}, fetch = FetchType.EAGER)
    private Set<FailureMode> failureModes = new HashSet<>();

}
