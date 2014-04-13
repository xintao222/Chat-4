package commons;/*
 * Created by krantz on 2014-04-13.
 */

import java.io.Serializable;

public class AcceptRequestMessage implements Serializable {
    private final String groupChatName;
    private final String from;
    private final String type;
    public final static String GROUP_REQUEST_ACCEPT = "GroupRequestAccept";

    public AcceptRequestMessage(String groupChatName, String from, String type) {
        this.groupChatName = groupChatName;
        this.from = from;
        this.type = type;
    }

    public String getGroupChatName() {
        return groupChatName;
    }

    public String getFrom() {
        return from;
    }

    public String getType() {
        return type;
    }
}
