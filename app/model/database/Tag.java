package model.database;

import java.util.HashSet;
import java.util.Set;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import lombok.Data;

@Entity
@Data
public class Tag {

    @Id
    private Long id;

    private String text;

    private String colorCode;

    private Set<FailureMode> failureModes = new HashSet<>();

}
