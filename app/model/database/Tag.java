package model.database;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Collection;

import static javax.persistence.GenerationType.AUTO;

@Entity
public class Tag {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    private String text;

    private String colorCode;

    @ManyToMany(mappedBy = "tags")
    private Collection<FailureMode> failureModes;

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

    public Collection<FailureMode> getFailureModes() {
        return failureModes;
    }

    public void setFailureModes(Collection<FailureMode> failureMode) {
        this.failureModes = failureMode;
    }
}
