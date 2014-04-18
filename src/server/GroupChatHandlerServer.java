package server;/*
 * Created by krantz on 2014-04-11.
 */

import commons.GroupMessage;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;

public class GroupChatHandlerServer {

    private Server server;
    private String groupName;
    private String from;
    HashMap<String, LinkedList<String>> groups; //< GroupName, inTheGroup >

    public GroupChatHandlerServer(Server server) {
        this.server = server;
        groups = new HashMap<String, LinkedList<String>>();
    }

    public void addGroup(String groupName, String from) {
        this.groupName = groupName;
        this.from = from;
//        System.out.println("From is: " + from);
//        System.out.println("Group name is: " + groupName);
        if (groups.containsKey(groupName)) {
            if (!groups.get(groupName).contains(from)) {
                groups.get(groupName).add(from);
            }
        } else {
            LinkedList<String> inGroup = new LinkedList<String>();
            inGroup.add(from);
            groups.put(groupName, inGroup);

        }
    }

    public void addToChat(String groupChatName, String from) {
//        System.out.println("Paraetrar: " + groupChatName + " " + from);
//        System.out.println("In addToChat: " + groups.values());
        groups.get(groupChatName).add(from);
//        System.out.println("addToChat: " + groups.get(groupChatName));


    }


    public LinkedList<String> getGroupMembers(String to) {
        return groups.get(to);
    }

    public void removeFromGroup(String from, String groupName) {
//        System.out.println("GroupChatHandler removeFromgroup: " + groupName);
        LinkedList<String> tempGroup = groups.get(groupName);

        tempGroup.remove(from);
        server.sendToAll(tempGroup, new GroupMessage(from, null, "Left the group", groupName));
    }

    public void removeFromAllGroup(String from) {
        Set<String> set = groups.keySet();
        for (String s : set) {
            LinkedList<String> temp = groups.get(s);
            if (temp.contains(s)) {
                temp.remove(from);
                server.sendToAll(temp, new GroupMessage(from, null, "Left the group", groupName));
            }
        }
    }
}
