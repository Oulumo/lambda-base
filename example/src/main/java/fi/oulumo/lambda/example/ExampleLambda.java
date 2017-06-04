package fi.oulumo.lambda.example;

import fi.oulumo.lambda.apigateway.AbstractLambda;
import fi.oulumo.lambda.example.dagger.DaggerExampleRequestManagerProvider;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public class ExampleLambda extends AbstractLambda<ExampleRequestManager> {
    @Override
    protected ExampleRequestManager createRequestManager() {
        return DaggerExampleRequestManagerProvider.builder().build().manager();
    }
}
