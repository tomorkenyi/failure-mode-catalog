package model.presentation;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class FailureModeResource {

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

    private Long lastUpdated;

    private List<TagResource> tags;

}
