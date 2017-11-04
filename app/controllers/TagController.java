package controllers;

import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Result;
import services.TagService;

import javax.inject.Inject;

public class TagController {

    private TagService tagService;
    private HttpExecutionContext executionContext;

    @Inject
    public TagController(TagService tagService, HttpExecutionContext executionContext) {
        this.tagService = tagService;
        this.executionContext = executionContext;
    }

    public Result search(String tag) {
        return play.mvc.Results.TODO;
    }


    public Result create(long id) {
        return play.mvc.Results.TODO;
    }
}
