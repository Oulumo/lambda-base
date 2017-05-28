package fi.oulumo.lambda.dynamodb.dagger;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
@Module
public class DynamoDbModule {
    @Provides
    @Singleton
    public AmazonDynamoDB provideAmazonDynamoDb(Regions region) {
        AmazonDynamoDB retValue = AmazonDynamoDBClientBuilder.standard().withRegion(region).build();

        return retValue;
    }

    @Provides
    @Singleton
    public DynamoDB provideDynamoDb(AmazonDynamoDB client) {
        return new DynamoDB(client);
    }

    @Provides
    @Singleton
    public DynamoDBMapper provideDynamoDbMapper(AmazonDynamoDB client) {
        return new DynamoDBMapper(client);
    }
}
