package concurrent;

import akka.actor.ActorSystem;
import play.libs.concurrent.CustomExecutionContext;

import javax.inject.Inject;

public class FailureModeCatalogExecutionContext extends CustomExecutionContext {

    @Inject
    public FailureModeCatalogExecutionContext(ActorSystem actorSystem) {
        super(actorSystem, "database.dispatcher");
    }
}
