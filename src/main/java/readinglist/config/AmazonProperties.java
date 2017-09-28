package readinglist.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by naaminicharles on 9/28/17.
 */
@Component
@ConfigurationProperties("amazon")
public class AmazonProperties {

    private String associateId;

    public void setAssociateId(String associateId) {
        this.associateId = associateId;
    }

    public String getAssociateId() {
        return associateId;
    }

}
