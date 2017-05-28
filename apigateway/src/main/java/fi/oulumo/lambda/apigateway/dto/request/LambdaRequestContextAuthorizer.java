package fi.oulumo.lambda.apigateway.dto.request;

import com.google.gson.annotations.SerializedName;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public class LambdaRequestContextAuthorizer {
    @SerializedName("principalId")
    private String principalId;

    public String getPrincipalId() {
        return principalId;
    }

    public void setPrincipalId(String principalId) {
        this.principalId = principalId;
    }
}
