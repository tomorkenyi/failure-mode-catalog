package model.presentation;

import java.util.List;

public class FailureModeResource {

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

    private Long lastUpdated;

    private List<TagResource> tags;

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

    public Long getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Long lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public List<TagResource> getTags() {
        return tags;
    }

    public void setTags(List<TagResource> tags) {
        this.tags = tags;
    }
}
