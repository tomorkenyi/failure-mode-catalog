package model.presentation;

import java.util.List;

public class TagResource {

    private String text;

    private String colorCode;

    private List<Long> failureModesIds;

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

    public List<Long> getFailureModesIds() {
        return failureModesIds;
    }

    public void setFailureModesIds(List<Long> failureModesIds) {
        this.failureModesIds = failureModesIds;
    }
}
