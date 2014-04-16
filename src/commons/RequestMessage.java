package commons;

import java.io.Serializable;

/**
 * Created by krantz on 2014-04-10.
 */
public class RequestMessage implements Serializable {

    public static final String GROUP_REQUEST = "groupRequest";
    public static final String GROUP_LEAVE = "groupLeaveRequest";
    public static final String GROUP_ALL_LEAVE = "leaveAllGroups";

    private String to;
    private String from;
    private String requestType;
    private String groupName;

    public RequestMessage(String to, String from, String groupName,  String requestType) {
        this.to = to;
        this.from = from;
        this.requestType = requestType;
        this.groupName = groupName;

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

    public  String getGroupName(){
        return groupName;
    }
}
