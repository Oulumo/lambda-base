package fi.oulumo.lambda.security.dagger;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.kms.AWSKMS;
import com.amazonaws.services.kms.AWSKMSClientBuilder;
import dagger.Module;
import dagger.Provides;
import fi.oulumo.lambda.security.KmsDecryptor;

import javax.inject.Singleton;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
@Module
public class KmsModule {
    @Provides
    @Singleton
    public AWSKMS provideKmsClient(Regions region) {
        AWSKMS retValue = AWSKMSClientBuilder.standard().withRegion(region).build();

        return retValue;
    }

    @Provides
    @Singleton
    public KmsDecryptor provideKmsDecryptor(AWSKMS kmsClient) {
        return new KmsDecryptor(kmsClient);
    }
}
