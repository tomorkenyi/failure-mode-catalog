package service;

import static mapper.TagMapper.INSTANCE;

import java.util.Optional;
import java.util.concurrent.CompletionStage;

import javax.inject.Inject;

import mapper.CycleAvoidingMappingContext;
import model.database.Tag;
import model.presentation.TagResource;
import repository.TagRepository;

public class TagService {

    @Inject
    private TagRepository repository;
    @Inject
    private CycleAvoidingMappingContext mappingContext;

    public CompletionStage<Tag> create(TagResource resource) {
        Tag tag = new Tag();
        tag.setText(resource.getText());
        tag.setColorCode(resource.getColorCode());
        return repository.create(tag);
    }

    public CompletionStage<Optional<TagResource>> search(String text) {
        return repository.search(text).thenApplyAsync(optionalTag ->
                optionalTag.map(tag ->
                        INSTANCE.toTagResource(tag, mappingContext)));
    }
}
