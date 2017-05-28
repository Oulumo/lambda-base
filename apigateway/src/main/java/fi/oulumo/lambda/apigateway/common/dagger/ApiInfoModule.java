package fi.oulumo.lambda.apigateway.common.dagger;

import dagger.Module;
import dagger.Provides;
import fi.oulumo.lambda.apigateway.common.dto.ApiInfo;

import javax.inject.Singleton;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
@Module
public class ApiInfoModule {
    private ApiInfo apiInfo;

    public ApiInfoModule(String description, String contactPerson, String license) {
        this(new ApiInfo(description, contactPerson, license));
    }

    public ApiInfoModule(String description, String contactPerson, String license, String licenseUrl, String termsOfServiceUrl, String privacyPolicyUrl) {
        this(new ApiInfo(description, contactPerson, license, licenseUrl, termsOfServiceUrl, privacyPolicyUrl));
    }

    public ApiInfoModule(String name, String version, String description, String contactPerson, String license) {
        this(new ApiInfo(name, version, description, contactPerson, license));
    }

    public ApiInfoModule(String name, String version, String description, String contactPerson, String license, String licenseUrl, String termsOfServiceUrl, String privacyPolicyUrl) {
        this(new ApiInfo(name, version, description, contactPerson, license, licenseUrl, termsOfServiceUrl, privacyPolicyUrl));
    }

    public ApiInfoModule(ApiInfo apiInfo) {
        this.apiInfo = apiInfo;
    }

    @Provides
    @Singleton
    public ApiInfo provideApiInfo() {
        return apiInfo;
    }
}
