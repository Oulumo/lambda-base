package fi.oulumo.lambda.tester.dagger;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.local.embedded.DynamoDBEmbedded;
import fi.oulumo.lambda.dynamodb.dagger.DynamoDbModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public class DynamoDbLocalModule extends DynamoDbModule {
    private static Logger log = LoggerFactory.getLogger(DynamoDbLocalModule.class);

    @Override
    public AmazonDynamoDB provideAmazonDynamoDb(Regions region) {
        log.info("Starting embedded DynamoDB...");

        return DynamoDBEmbedded.create();
    }
}
