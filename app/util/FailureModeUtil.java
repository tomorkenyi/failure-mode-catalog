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
        failureMode.setDetectability((Integer)document.get("detectability"));
        failureMode.setFunctionalState((String)document.get("functionalState"));
        return failureMode;
    }

}
