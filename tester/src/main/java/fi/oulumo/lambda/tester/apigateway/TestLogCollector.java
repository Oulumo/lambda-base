package fi.oulumo.lambda.tester.apigateway;

import com.amazonaws.services.lambda.runtime.LambdaLogger;
import fi.oulumo.lambda.core.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public class TestLogCollector implements LambdaLogger{
    private static final Logger log = LoggerFactory.getLogger(TestLogCollector.class);

    private List<String> logMessages = new ArrayList<>();

    @Override
    public void log(String string) {
        if (StringUtils.hasText(string)) {
            logMessages.add(string);
            log.debug(string);
        }
    }

    public List<String> getLogMessages() {
        return logMessages;
    }

    public void clearLogMessages() {
        logMessages.clear();
    }
}
