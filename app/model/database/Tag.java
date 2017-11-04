package model.database;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.GenerationType.AUTO;

@Entity
public class Tag {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    private String text;

    private String colorCode;

    @ManyToMany(mappedBy = "tags", cascade = {PERSIST, MERGE})
    private List<FailureMode> failureModes = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    public List<FailureMode> getFailureModes() {
        return failureModes;
    }

    public void setFailureModes(List<FailureMode> failureModes) {
        this.failureModes = failureModes;
    }
}
