package fi.oulumo.lambda.s3.dagger;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
@Module
public class S3Module {
    @Provides
    @Singleton
    public AmazonS3 provideAmazonS3(Regions region) {
        AmazonS3 retValue = AmazonS3ClientBuilder.standard().withRegion(region).build();

        return retValue;
    }
}
