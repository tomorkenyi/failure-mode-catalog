package util;

import org.bson.Document;

import model.database.FailureMode;
import model.presentation.FailureModeResource;
import play.libs.Json;
import play.mvc.Http;

public class FailureModeUtil {

    public static FailureModeResource extractFailureModeResourceFromJson(Http.Request request) {
        return Json.fromJson(request.body().asJson(), FailureModeResource.class);
    }

    public static FailureMode convertToFailureMode(Document document) {
        FailureMode failureMode = new FailureMode();
        failureMode.setId(document.getObjectId(document));
        failureMode.setDetectability(document.getInteger("detectability"));
        failureMode.setDetectFailures(document.getString("detectFailures"));
        failureMode.setFunctionalState(document.getString("functionalState"));
        failureMode.setLastUpdated(document.getLong("lastUpdated"));
        failureMode.setMitigation(document.getString("mitigation"));
        failureMode.setPlatformEffect(document.getString("platformEffect"));
        failureMode.setPotentialCause(document.getString("potentialCause"));
        failureMode.setProbability(document.getInteger("probability"));
        failureMode.setResponseAction(document.getString("responseAction"));
        failureMode.setSafetyConcern(document.getBoolean("safetyConcern"));
        failureMode.setServiceEffect(document.getString("serviceEffect"));
        //        failureMode.setTags((Set<Tag>)document.get("tags"));
        return failureMode;
    }

}
