package fi.oulumo.lambda.tester.apigateway;

import com.amazonaws.services.lambda.runtime.ClientContext;
import com.amazonaws.services.lambda.runtime.CognitoIdentity;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;

import java.util.List;
import java.util.UUID;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public class TestContext implements Context{
    public static final int DEFAULT_REMAINING_TIME = 3000;
    public static final int DEFAULT_MEMORY_LIMIT = 50;
    public static final String LOG_GROUP = "TestLogGroup";
    public static final String LOG_STREAM = "TestLogStream";

    private String awsRequestId;
    private String functionName;
    private String functionVersion;
    private String invokedFunctionArn;
    private int remainingTimeInMillis;
    private int memoryLimitInMB;
    private TestLogCollector logger;

    public TestContext(String functionName, String functionVersion) {
        this(
                UUID.randomUUID().toString(),
                functionName,
                functionVersion,
                generateArnFrom(functionName, functionVersion),
                DEFAULT_REMAINING_TIME,
                DEFAULT_MEMORY_LIMIT
        );
    }

    public TestContext(String awsRequestId, String functionName, String functionVersion, String invokedFunctionArn, int remainingTimeInMillis, int memoryLimitInMB) {
        this.awsRequestId = awsRequestId;
        this.functionName = functionName;
        this.functionVersion = functionVersion;
        this.invokedFunctionArn = invokedFunctionArn;
        this.remainingTimeInMillis = remainingTimeInMillis;
        this.memoryLimitInMB = memoryLimitInMB;
        this.logger = new TestLogCollector();
    }

    @Override
    public String getAwsRequestId() {
        return awsRequestId;
    }

    @Override
    public String getLogGroupName() {
        return LOG_GROUP;
    }

    @Override
    public String getLogStreamName() {
        return LOG_STREAM;
    }

    @Override
    public String getFunctionName() {
        return functionName;
    }

    @Override
    public String getFunctionVersion() {
        return functionVersion;
    }

    @Override
    public String getInvokedFunctionArn() {
        return invokedFunctionArn;
    }

    @Override
    public CognitoIdentity getIdentity() {
        return null;
    }

    @Override
    public ClientContext getClientContext() {
        return null;
    }

    @Override
    public int getRemainingTimeInMillis() {
        return remainingTimeInMillis;
    }

    @Override
    public int getMemoryLimitInMB() {
        return memoryLimitInMB;
    }

    @Override
    public LambdaLogger getLogger() {
        return logger;
    }

    public List<String> getLogMessages() {
        return logger.getLogMessages();
    }

    public void clearLogMessages() {
        logger.clearLogMessages();
    }

    private static String generateArnFrom(String functionName, String functionVersion) {
        return String.format("arn:aws:lambda:eu-central1:123456789012:function:%s:%s", functionName, functionVersion);
    }
}
