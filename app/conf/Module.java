package conf;

import com.google.inject.AbstractModule;

public class Module extends AbstractModule {
    @Override
    protected void configure() {
//        bind(FailureModeRepository.class).to(JPAFailureModeRepository.class).asEagerSingleton();
//        bind(TagRepository.class).to(JPATagRepository.class).asEagerSingleton();
    }
}
