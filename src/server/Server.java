package server;

import commons.AcceptRequestMessage;
import commons.GroupMessage;
import commons.Message;
import commons.RequestMessage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;

public class Server implements Runnable {

    private ArrayList<ServerClient> clients; //Should be a HasMap<String, ServerClient>
    private ServerSocket serverSocket;
    private GroupChatHandlerServer groupChatHandler;

    public Server() {
        clients = new ArrayList<ServerClient>();
        groupChatHandler = new GroupChatHandlerServer(this);
        int port = 4446;
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        while (true) {
            try {
                Socket socketCon = serverSocket.accept();
                System.out.println("Connected clients: " + clients);
                ServerClient cl = new ServerClient(this, socketCon);
                clients.add(cl);
                new Thread(cl).start();
            } catch (Exception e) {
                e.printStackTrace(System.out);
            }

        }

    }

    public ArrayList<ServerClient> getServerClients() {
        return clients;
    }

    public static void main(String[] args) {
        Server server = new Server();
        new Thread(server).start();
    }

    public void passOnMessage(Message mess) {

        String to = mess.getTo();


        for (ServerClient sc : clients) {
            if (sc.getName().equals(to)) {
                sc.sendObject(mess);
            }

        }

    }


    public void passOnRequest(RequestMessage requestMessage) {
        String to = requestMessage.getTo();
        groupChatHandler.addGroup(requestMessage.getGroupName(), requestMessage.getFrom());
        for (ServerClient sc : clients) {
            if (sc.getName().equals(to)) {
                sc.sendObject(requestMessage);
            }
        }
    }

    public void passOnAccept(AcceptRequestMessage acceptRequestMessage) {
        if (acceptRequestMessage.getType().equals(AcceptRequestMessage.GROUP_REQUEST_ACCEPT)) {

            System.out.println("Server got accept from: " + acceptRequestMessage.getFrom());
            groupChatHandler.addToChat(acceptRequestMessage.getGroupChatName(), acceptRequestMessage.getFrom());
            LinkedList<String> members = groupChatHandler.getGroupMembers(acceptRequestMessage.getGroupChatName());
            for (String s : members) {
                for (ServerClient sc : clients) {
                    if (sc.getName().equals(s) && !s.equals(acceptRequestMessage.getFrom())) {
                        sc.sendObject(new GroupMessage(acceptRequestMessage.getFrom(), s, "Joined group chat ", acceptRequestMessage.getGroupChatName()));
                    }
                }
            }

        }
    }

    public void passOnGroupMessage(GroupMessage groupMessage) {
        LinkedList<String> groupNames = groupChatHandler.getGroupMembers(groupMessage.getGroupName());
        System.out.println("In group: " + groupNames);
        for (String s : groupNames) {
            for (ServerClient sc : clients) {
                if (sc.getName().equals(s) && !s.equals(groupMessage.getFrom())) {
                    sc.sendObject(groupMessage);
                }
            }
        }
    }

    public void exterminateMe(ServerClient serverClient) {
        System.out.println(clients);
        clients.remove(serverClient);
        System.out.println(clients);

        for (ServerClient s : clients) {
            System.out.println(s);
            s.updateOnlineClients();

        }

    }
}
