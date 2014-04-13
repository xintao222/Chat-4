package commons;/*
 * Created by krantz on 2014-04-13.
 */

public class GroupMessage extends Message {
    private final String from;
    private final String to;
    private final String message;
    private final String groupName;

    public GroupMessage(String from, String to, String message, String GroupName) {

        super(from, to, message);
        this.from = from;
        this.to = to;
        this.message = message;
        groupName = GroupName;
    }

    public String getGroupName(){
        return groupName;
    }
}
