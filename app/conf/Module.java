package conf;

import com.google.inject.AbstractModule;
import repositories.FailureModeRepository;
import repositories.JPAFailureModeRepository;
import repositories.JPATagRepository;
import repositories.TagRepository;

public class Module extends AbstractModule {
    @Override
    protected void configure() {
        bind(FailureModeRepository.class).to(JPAFailureModeRepository.class).asEagerSingleton();
        bind(TagRepository.class).to(JPATagRepository.class).asEagerSingleton();
    }
}
