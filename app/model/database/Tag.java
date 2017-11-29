package model.database;

import lombok.Getter;
import lombok.Setter;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Tag {

    public static final String FIND_BY_TEXT = "Tag.findByText";

    @Id
    private Long id;

    private String text;

    private String colorCode;

    private Set<FailureMode> failureModes = new HashSet<>();

}
