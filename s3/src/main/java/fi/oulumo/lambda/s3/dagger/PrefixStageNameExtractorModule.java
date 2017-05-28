package fi.oulumo.lambda.s3.dagger;

import dagger.Module;
import dagger.Provides;
import fi.oulumo.lambda.s3.handler.stage.IStageNameExtractor;
import fi.oulumo.lambda.s3.handler.stage.PrefixStageNameExtractor;

import javax.inject.Singleton;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
@Module
public class PrefixStageNameExtractorModule {
    @Provides
    @Singleton
    public IStageNameExtractor provideStageNameExtractor() {
        return new PrefixStageNameExtractor();
    }
}
