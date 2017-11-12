package util;

import model.presentation.FailureModeResource;
import play.libs.Json;
import play.mvc.Http;

public class FailureModeUtil {

//    public static FailureModeResource initializeFailureModeResource(FailureMode data) {
//        FailureModeResource resource = new FailureModeResource();
//        resource.setId(data.getId());
//        resource.setDetectability(data.getDetectability());
//        resource.setDetectFailures(data.getDetectFailures());
//        resource.setFunctionalState(data.getFunctionalState());
//        resource.setLastUpdated(data.getLastUpdated());
//        resource.setMitigation(data.getMitigation());
//        resource.setPlatformEffect(data.getPlatformEffect());
//        resource.setPotentialCause(data.getPotentialCause());
//        resource.setProbability(data.getProbability());
//        resource.setResponseAction(data.getResponseAction());
//        resource.setSafetyConcern(data.getSafetyConcern());
//        resource.setServiceEffect(data.getServiceEffect());
//        resource.setTags(getTagResourceStream(data).collect(Collectors.toList()));
//        return resource;
//    }
//
//    private static Stream<TagResource> getTagResourceStream(FailureMode data) {
//        return data.getTags().stream().map(tag -> {
//            TagResource tagResource = new TagResource();
//            tagResource.setText(tag.getText());
//            tagResource.setColorCode(tag.getColorCode());
//            tagResource.setFailureModesIds(updatedFailureModesIds(tagResource.getFailureModesIds(), data.getId()));
//            return tagResource;
//        });
//    }
//
//    private static List<Long> updatedFailureModesIds(final List<Long> failureModesIds, final Long id) {
//        if (failureModesIds == null || failureModesIds.isEmpty()) {
//            return Collections.singletonList(id);
//        } else {
//            failureModesIds.add(id);
//        }
//        return failureModesIds;
//    }
//    public static void updateFailureMode(FailureModeResource resource, FailureMode failureMode) {
//        failureMode.setDetectability(resource.getDetectability());
//        failureMode.setDetectFailures(resource.getDetectFailures());
//        failureMode.setFunctionalState(resource.getFunctionalState());
//        failureMode.setLastUpdated(resource.getLastUpdated());
//        failureMode.setMitigation(resource.getMitigation());
//        failureMode.setPlatformEffect(resource.getPlatformEffect());
//        failureMode.setPotentialCause(resource.getPotentialCause());
//        failureMode.setProbability(resource.getProbability());
//        failureMode.setResponseAction(resource.getResponseAction());
//        failureMode.setSafetyConcern(resource.getSafetyConcern());
//        failureMode.setServiceEffect(resource.getServiceEffect());
//    }

    public static FailureModeResource extractFailureModeResourceFromJson(Http.Request request) {
        return Json.fromJson(request.body().asJson(), FailureModeResource.class);
    }

}
