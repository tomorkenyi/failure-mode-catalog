package model.presentation;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class TagResource {

    private String text;

    private String colorCode;

    private List<Long> failureModesIds;

}
