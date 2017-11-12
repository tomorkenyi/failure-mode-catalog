package service;

import model.database.Tag;
import model.presentation.TagResource;
import repository.TagRepository;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

public class TagService {

    @Inject
    private TagRepository repository;

    public CompletionStage<Tag> create(TagResource resource) {
        Tag tag = new Tag();
        tag.setText(resource.getText());
        tag.setColorCode(resource.getColorCode());

        return repository.create(tag);
    }


}
