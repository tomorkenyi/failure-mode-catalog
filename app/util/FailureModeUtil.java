package util;

import model.presentation.FailureModeResource;
import play.libs.Json;
import play.mvc.Http;

public class FailureModeUtil {

    public static FailureModeResource extractFailureModeResourceFromJson(Http.Request request) {
        return Json.fromJson(request.body().asJson(), FailureModeResource.class);
    }

}
