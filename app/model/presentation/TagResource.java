package model.presentation;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TagResource {

    private String text;

    private String colorCode;

    private List<FailureModeResource> failureModes;


}
