package fi.oulumo.lambda.apigateway.dagger;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dagger.Module;
import dagger.Provides;
import fi.oulumo.lambda.apigateway.dto.response.HttpStatus;
import fi.oulumo.lambda.apigateway.exception.DefaultErrorTranslator;
import fi.oulumo.lambda.apigateway.exception.IErrorTranslator;
import fi.oulumo.lambda.apigateway.gson.HttpStatusConverter;
import fi.oulumo.lambda.apigateway.gson.JodaDateTimeConverter;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.joda.time.DateTime;

import javax.inject.Singleton;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
@Module
public class ApiGatewayModule {
    @Provides
    @Singleton
    public IErrorTranslator provideErrorTranslator() {
        return new DefaultErrorTranslator();
    }

    /**
     * Configures Gson to use a common format all around the application. Configures: lower-case with
     * underscores mode (e.g.: field names are like "api_status") and a JodaTime DateTime converter
     * that uses ISO8601 compatible date-time format (basic date-time without milliseconds: yyyyMMdd'T'HHmmssZ).
     */
    @Provides
    @Singleton
    public GsonBuilder provideGsonBuilder() {
        return new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .registerTypeAdapter(DateTime.class, new JodaDateTimeConverter())
                .registerTypeAdapter(HttpStatus.class, new HttpStatusConverter());
    }

    @Provides
    @Singleton
    public Gson provideGson(GsonBuilder gsonBuilder) {
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    public CloseableHttpClient provideHttpClient() {
        return HttpClients.createDefault();
    }
}
