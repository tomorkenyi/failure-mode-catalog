package model.database;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.GenerationType.AUTO;

@Getter
@Setter
@Entity
public class Tag {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    private String text;

    private String colorCode;

    @ManyToMany(mappedBy = "tags", cascade = {PERSIST, MERGE})
    private List<FailureMode> failureModes = new ArrayList<>();

}
