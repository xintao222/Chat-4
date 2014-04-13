package server;/*
 * Created by krantz on 2014-04-11.
 */

import java.util.HashMap;
import java.util.LinkedList;

public class GroupChatHandlerServer {

    private Server server;
    private String groupName;
    private String from;
    HashMap<String, LinkedList<String>> groups;

    public GroupChatHandlerServer(Server server) {
        this.server = server;
        groups = new HashMap<String, LinkedList<String>>();
    }

    public void addGroup(String groupName, String from) {
        this.groupName = groupName;
        this.from = from;
        System.out.println("From is: " + from);
        System.out.println("Group name is: " + groupName);
        LinkedList<String> inGroup = new LinkedList<String>();
        inGroup.add(from);
        groups.put(groupName, inGroup);


    }

    public void addToChat(String groupChatName, String from) {
        System.out.println("Paraetrar: " + groupChatName + " " + from);
        System.out.println("In addToChat: " + groups.values());
        groups.get(groupChatName).add(from);
        System.out.println("addToChat: " + groups.get(groupChatName));


    }


    public LinkedList<String> getGroupMembers(String to) {
        return groups.get(to);
    }
}
