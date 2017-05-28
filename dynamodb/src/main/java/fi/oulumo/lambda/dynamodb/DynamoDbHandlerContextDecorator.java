package fi.oulumo.lambda.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import fi.oulumo.lambda.core.context.IHandlerContext;
import fi.oulumo.lambda.core.context.IHandlerContextDecorator;
import fi.oulumo.lambda.dynamodb.util.PrefixUtils;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public class DynamoDbHandlerContextDecorator implements IHandlerContextDecorator {
    @Override
    public void decorateContext(IHandlerContext context) {
        String stageName = context.getStageName();

        DynamoDBMapperConfig.Builder builder = new DynamoDBMapperConfig.Builder();
        builder.setTableNameOverride(
                DynamoDBMapperConfig.TableNameOverride.withTableNamePrefix(
                        PrefixUtils.getDynamoDbTablePrefixFrom(stageName)
                )
        );

        context.registerDecoration(DynamoDBMapperConfig.class, builder.build());
    }
}
