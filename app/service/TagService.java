package service;

import mapper.CycleAvoidingMappingContext;
import model.database.Tag;
import model.presentation.TagResource;
import repository.TagRepository;

import javax.inject.Inject;
import java.util.Optional;
import java.util.concurrent.CompletionStage;

import static mapper.TagMapper.INSTANCE;

public class TagService {

    @Inject
    private TagRepository repository;
    @Inject
    private CycleAvoidingMappingContext mappingContext;

    public CompletionStage<Optional<Tag>> create(Long failureModeId, TagResource resource) {
        Tag tag = new Tag();
        tag.setText(resource.getText());
        tag.setColorCode(resource.getColorCode());
        return repository.create(failureModeId, tag);
    }

    public CompletionStage<Optional<TagResource>> search(String text) {
        return repository.search(text).thenApplyAsync(optionalTag ->
                optionalTag.map(tag ->
                        INSTANCE.toTagResource(tag, mappingContext)));
    }

    public CompletionStage<Void> delete(Long failureId, Long tagId) {
        return repository.delete(failureId, tagId);
    }
}
