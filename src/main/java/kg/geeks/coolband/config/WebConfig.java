package kg.geeks.coolband.config;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class WebConfig {

    @Getter
    private static String server;

    @Getter
    private static String frontServer;

    @Value("${front.server}")
    public void setFrontServer(String frontServer){
        WebConfig.frontServer = frontServer;
    }

    @Value("${server}")
    public void setServer(String server) {
        WebConfig.server = server;
    }

}
