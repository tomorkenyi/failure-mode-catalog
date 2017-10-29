package conf;

import com.google.inject.AbstractModule;
import repositories.FailureModeRepository;
import repositories.JPAFailureModeRepository;

public class Module extends AbstractModule {
    @Override
    protected void configure() {
        bind(FailureModeRepository.class).to(JPAFailureModeRepository.class).asEagerSingleton();
    }
}
