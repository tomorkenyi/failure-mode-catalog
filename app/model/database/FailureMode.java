package model.database;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.GenerationType.AUTO;

@Entity
public class FailureMode {

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

//     TODO user entity later on
//    private User creator;

    @Version
    private Long version;

    private Long lastUpdated;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {PERSIST, MERGE})
    @JoinTable(name = "FailureModeTags",
            joinColumns = {
                    @JoinColumn(name = "failureMode_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "tag_id", referencedColumnName = "id")
            }
    )
    private List<Tag> tags = new ArrayList<>();


    public Long getId() {
        return id;
    }

    public String getFunctionalState() {
        return functionalState;
    }

    public void setFunctionalState(String functionalState) {
        this.functionalState = functionalState;
    }

    public String getServiceEffect() {
        return serviceEffect;
    }

    public void setServiceEffect(String serviceEffect) {
        this.serviceEffect = serviceEffect;
    }

    public String getPlatformEffect() {
        return platformEffect;
    }

    public void setPlatformEffect(String platformEffect) {
        this.platformEffect = platformEffect;
    }

    public String getPotentialCause() {
        return potentialCause;
    }

    public void setPotentialCause(String potentialCause) {
        this.potentialCause = potentialCause;
    }

    public Integer getProbability() {
        return probability;
    }

    public void setProbability(Integer probability) {
        this.probability = probability;
    }

    public String getDetectFailures() {
        return detectFailures;
    }

    public void setDetectFailures(String detectFailures) {
        this.detectFailures = detectFailures;
    }

    public String getResponseAction() {
        return responseAction;
    }

    public void setResponseAction(String responseAction) {
        this.responseAction = responseAction;
    }

    public String getMitigation() {
        return mitigation;
    }

    public void setMitigation(String mitigation) {
        this.mitigation = mitigation;
    }

    public Integer getDetectability() {
        return detectability;
    }

    public void setDetectability(Integer detectability) {
        this.detectability = detectability;
    }

    public Boolean getSafetyConcern() {
        return safetyConcern;
    }

    public void setSafetyConcern(Boolean safetyConcern) {
        this.safetyConcern = safetyConcern;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Long getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Long lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}
