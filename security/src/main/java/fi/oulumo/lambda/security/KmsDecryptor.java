package fi.oulumo.lambda.security;

import com.amazonaws.services.kms.AWSKMS;
import com.amazonaws.services.kms.model.DecryptRequest;
import com.amazonaws.services.kms.model.DecryptResult;
import com.amazonaws.util.Base64;

import javax.inject.Inject;
import java.nio.ByteBuffer;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public class KmsDecryptor {
    private AWSKMS kmsClient;

    @Inject
    public KmsDecryptor(AWSKMS kmsClient) {
        this.kmsClient = kmsClient;
    }

    public String decryptToString(String cipherText) {
        return decryptToString(cipherText, true);
    }

    public String decryptToString(String cipherText, boolean base64Encoded) {
        return new String(decryptToByteArray(cipherText, base64Encoded));
    }

    public byte[] decryptToByteArray(String cipherText, boolean base64Encoded) {
        ByteBuffer plaintextBuffer = decryptToByteBuffer(cipherText, base64Encoded);

        byte[] plaintextBytes = new byte[plaintextBuffer.remaining()];
        plaintextBuffer.get(plaintextBytes);

        return plaintextBytes;
    }

    public ByteBuffer decryptToByteBuffer(String cipherText, boolean base64Encoded) {
        ByteBuffer cipherTextBuffer;

        if (base64Encoded) {
            cipherTextBuffer = ByteBuffer.wrap(Base64.decode(cipherText));
        }
        else {
            cipherTextBuffer = ByteBuffer.wrap(cipherText.getBytes());
        }

        DecryptRequest decryptRequest = new DecryptRequest().withCiphertextBlob(cipherTextBuffer);
        DecryptResult decryptResult = kmsClient.decrypt(decryptRequest);

        return decryptResult.getPlaintext();
    }
}
