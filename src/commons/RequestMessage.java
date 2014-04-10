package commons;

import java.io.Serializable;

/**
 * Created by krantz on 2014-04-10.
 */
public class RequestMessage implements Serializable {

    public static final String GROUPREQUEST = "groupRequest";
    private String to;
    private String from;
    private String requestType;

    public RequestMessage(String to, String from, String requestType) {
        this.to = to;
        this.from = from;
        this.requestType = requestType;

    }


    public String getRequestType() {
        return requestType;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }
}
