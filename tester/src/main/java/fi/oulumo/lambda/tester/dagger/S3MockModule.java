package fi.oulumo.lambda.tester.dagger;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.local.embedded.DynamoDBEmbedded;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import fi.oulumo.lambda.s3.dagger.S3Module;
import io.findify.s3mock.S3Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public class S3MockModule extends S3Module {
    private static Logger log = LoggerFactory.getLogger(S3MockModule.class);

    private static AtomicInteger MOCK_S3_PORT = new AtomicInteger(6060);

    @Override
    public AmazonS3 provideAmazonS3(Regions region) {
        try {
            Path tempDirPath = Files.createTempDirectory("s3mock");
            File tempDirectory = tempDirPath.toFile();
            tempDirectory.deleteOnExit();

            int port = MOCK_S3_PORT.getAndIncrement();

            S3Mock api = S3Mock.create(port, tempDirectory.getAbsolutePath());
            api.start();

            log.info("Started S3 Mock on port: {} (backed by directory: {})", port, tempDirectory.getAbsolutePath());

            AmazonS3 client = AmazonS3ClientBuilder
                    .standard()
                    .withEndpointConfiguration(
                            new AwsClientBuilder.EndpointConfiguration("http://127.0.0.1:" + port, region.getName())
                    )
                    .build();

            return client;
        }
        catch (IOException e) {
            throw new RuntimeException("Can't create temp directory for S3 mock");
        }
    }

}
