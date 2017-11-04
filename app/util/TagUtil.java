package util;

import model.presentation.TagResource;
import play.libs.Json;
import play.mvc.Http;

public class TagUtil {

    public static TagResource extractTagResourceFromJson(Http.Request request) {
        return Json.fromJson(request.body().asJson(), TagResource.class);
    }

}
