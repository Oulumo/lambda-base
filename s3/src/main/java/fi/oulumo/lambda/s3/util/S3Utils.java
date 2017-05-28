package fi.oulumo.lambda.s3.util;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import fi.oulumo.lambda.core.util.StringUtils;
import org.joda.time.DateTime;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class S3Utils {
    private static final String AWS_S3_PROTOCOL = "https://";
    private static final String AWS_S3_BASE_URL = ".s3.amazonaws.com/";

    private S3Utils() {
        // Utility class, no instances are allowed
    }

    public static InputStream getObjectStream(AmazonS3 s3, String bucketName, String objectKey) {
        S3Object s3Object = s3.getObject(bucketName, objectKey);

        return s3Object.getObjectContent();
    }

    public static boolean putObject(AmazonS3 s3, String bucketName, String objectKey, byte[] content, String mimeType) {
        if ((content != null) && (content.length > 0)) {
            try(InputStream contentInputStream = new ByteArrayInputStream(content)) {
                ObjectMetadata objectMetadata = new ObjectMetadata();
                objectMetadata.setContentLength(content.length);
                objectMetadata.setContentType(mimeType);

                s3.putObject(bucketName, objectKey, contentInputStream, objectMetadata);

                return true;
            }
            catch (IOException e) {
                return false;
            }
        }

        return false;
    }

    public static boolean putObject(AmazonS3 s3, String bucketName, String objectKey, byte[] content, ObjectMetadata metadata) {
        if ((content != null) && (content.length > 0)) {
            try(InputStream contentInputStream = new ByteArrayInputStream(content)) {
                ObjectMetadata objectMetadata = metadata.clone();
                objectMetadata.setContentLength(content.length);

                s3.putObject(bucketName, objectKey, contentInputStream, objectMetadata);

                return true;
            }
            catch (IOException e) {
                return false;
            }
        }

        return false;
    }

    public static boolean doesBucketExist(AmazonS3 s3, String bucketName) {
        return s3.doesBucketExist(bucketName);
    }

    public static boolean doesObjectExist(AmazonS3 s3, String bucketName, String objectKey) {
        return s3.doesObjectExist(bucketName, objectKey);
    }

    public static String getPreauthenticatedUrl(AmazonS3 amazonS3, String bucketName, String objectKey, boolean upload, int validityMinutes) {
        GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucketName, objectKey);
        if (upload) {
            generatePresignedUrlRequest.setMethod(HttpMethod.PUT);
        }
        else {
            generatePresignedUrlRequest.setMethod(HttpMethod.GET);
        }
        DateTime expiryTime = DateTime.now().plusMinutes(validityMinutes);
        generatePresignedUrlRequest.setExpiration(expiryTime.toDate());

        URL retValue = amazonS3.generatePresignedUrl(generatePresignedUrlRequest);

        return retValue.toString();
    }

    public static String getObjectEtag(AmazonS3 s3, String bucketName, String objectKey) {
        try {
            ObjectMetadata objectMetadata = s3.getObjectMetadata(bucketName, objectKey);

            if (objectMetadata != null) {
                return objectMetadata.getETag();
            }
        }
        catch (AmazonServiceException e) {
            // Do nothing, we will return the default "null"
        }

        return null;
    }

    public static String getPublicAccessUrl(String bucketName, String objectKey) {
        if (StringUtils.allHasText(bucketName, objectKey)) {
            StringBuilder retValue = new StringBuilder();

            retValue.append(AWS_S3_PROTOCOL);
            retValue.append(bucketName);
            retValue.append(AWS_S3_BASE_URL);
            retValue.append(objectKey);

            return retValue.toString();
        }

        return null;
    }
}
