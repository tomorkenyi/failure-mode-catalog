package it;

import model.database.FailureMode;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.mvc.Http;
import play.mvc.Result;
import play.test.WithApplication;
import repository.FailureModeRepository;

import java.util.Date;

import static play.test.Helpers.*;

public class IntegrationTest extends WithApplication {

    @Override
    protected Application provideApplication() {
        return new GuiceApplicationBuilder().build();
    }

    @Test
    public void testList() {
        FailureModeRepository repository = app.injector().instanceOf(FailureModeRepository.class);

        FailureMode failureMode = createFailureMode();
        repository.create(failureMode);

        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/failures");

        Result result = route(app, request);
        String content = contentAsString(result);

        // TODO convert to json and assert results
        Assertions.assertThat(content).contains("detect failures");
    }

    private FailureMode createFailureMode() {
        FailureMode failureMode = new FailureMode();
        failureMode.setDetectability(2);
        failureMode.setDetectFailures("detect failures");
        failureMode.setFunctionalState("functional state");
        failureMode.setLastUpdated(new Date().getTime());
        failureMode.setMitigation("mitigation");
        failureMode.setPlatformEffect("the platform has an effect");
        failureMode.setPotentialCause("this is a serious potential cause");
        failureMode.setProbability(3);
        failureMode.setSafetyConcern(true);
        failureMode.setResponseAction("response action");
        failureMode.setServiceEffect("it has a service effect");
        return failureMode;
    }


}
