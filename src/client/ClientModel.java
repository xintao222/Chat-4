package client;

import commons.ClientListFromServer;
import commons.Message;
import commons.RequestMessage;
import commons.SharedClient;

import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;

public class ClientModel extends Observable implements Runnable {
    public static final String mainChatName = "Forever alone";
    private HashMap<String, SavedChatHistory> history;
    private String loginName;
    private String serverIp;
    private int serverPort;
    private Socket clientSocket;
    private InputStream inStream;
    private ObjectInputStream ObjInputStream;
    private OutputStream outStream;
    private ObjectOutputStream objectOutStream;
    private boolean running = false;
    private ArrayList<SharedClient> connectedClients;
    private ArrayList<String> allConnectedNames;
    private String chatWith = "Forever alone";
    private String recentlyReceivedFrom = "null";

    public ClientModel(String loginName, String ip, int port) {
        this.loginName = loginName;
        this.serverIp = ip;
        this.serverPort = port;
        running = true;
        connectedClients = new ArrayList<SharedClient>();
        allConnectedNames = new ArrayList<String>();
        history = new HashMap<String, SavedChatHistory>();
        history.put("Forever alone", new SavedChatHistory("Forever alone"));


        try {
            clientSocket = new Socket(serverIp, serverPort);

            inStream = clientSocket.getInputStream();

            outStream = clientSocket.getOutputStream();

            objectOutStream = new ObjectOutputStream(outStream);

            connectToServer();

            ObjInputStream = new ObjectInputStream(inStream);

        } catch (Exception e) {
            System.out.println("error creating socket");
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            try {
                if (inStream.available() > 0) {
                    Object input = null;
                    try {
                        input = ObjInputStream.readObject();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    if (input != null && input instanceof Message) {
                        Message mess = (Message) input;

                        updateHistory(mess.getFrom(), mess.getMessage(), true);
                    } else if (input != null && input instanceof ClientListFromServer) {
                        ClientListFromServer clientListFromServer = (ClientListFromServer) input;
                        ArrayList<SharedClient> tempList = clientListFromServer.getClients();
                        connectedClients.clear();
                        connectedClients.addAll(tempList);
                        allConnectedNames.clear();
                        for (SharedClient sh : tempList) {
                            if (!sh.getName().equals(loginName)) {
                                allConnectedNames.add(sh.getName());
                            }
                        }
                        setChanged();
                        notifyObservers();
                    } else if (input != null && input instanceof RequestMessage) {
                        RequestMessage requestMessage = (RequestMessage) input;
                        //Continue here! Handle what to do now when receiving request. Need to talk with GroupChatHandler somehow.
                        handleRequest(requestMessage);


                    }
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    private void handleRequest(RequestMessage requestMessage) {
        String groupChatName = requestMessage.getGroupName();
        String from = requestMessage.getFrom();

        //display yes/no
        int option = JOptionPane.showConfirmDialog(null, "choose one", "choose one", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            updateHistory(groupChatName, "Joined group chat" + groupChatName, true);

        }


    }

    public ArrayList<String> getConnectedClients() {
        return allConnectedNames;
    }

    /*
     * Request connection to server by sending /C/
     */
    private void connectToServer() {
        try {
            objectOutStream.writeObject(new String(loginName + "/C/"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void sendMessage(String message) {
        if (!message.equals("")) {
            sendObject(new Message(loginName, chatWith, message));
            updateHistory(loginName, message, false);
        }
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public void exterminate() throws IOException {
        objectOutStream.writeObject(new String("/EXIT/"));
        objectOutStream.close();
        outStream.close();
        clientSocket.close();
        ObjInputStream.close();
        running = false;
        System.exit(0);
    }

    private void updateHistory(String from, String mess, boolean received) {
        if (!received) {
            if (history.containsKey(chatWith)) {
                SavedChatHistory sh = history.get(chatWith);
                sh.append("You", mess);
            } else {
                SavedChatHistory s = new SavedChatHistory(chatWith);
                s.append("You", mess);
                history.put(chatWith, s);
            }
        } else {
            recentlyReceivedFrom = from;
            if (history.containsKey(from)) {
                SavedChatHistory sh = history.get(from);
                sh.append(from, mess);
            } else {
                SavedChatHistory sa = new SavedChatHistory(from);
                sa.append(from, mess);
                history.put(from, sa);
            }
        }
        setChanged();
        notifyObservers();

    }

    public String chatsWith() {
        return chatWith;
    }

    public String getHistory(String who) {
        return history.get(who).getHistory();
    }

    public String received() {
        String temp = recentlyReceivedFrom;
        recentlyReceivedFrom = "null";
        return temp;
    }

    public String getChatWith() {
        return chatWith;
    }

    public void setChatWith(String string) {
        chatWith = string;
    }

    public void sendInvite(ArrayList<String> group, String groupName) {
        for (String s : group) {
            sendObject(new RequestMessage(s, loginName, groupName, RequestMessage.GROUPREQUEST));
        }
        updateHistory(groupName, "Group chat started", false);
    }

    private void sendObject(Object o) {
        try {
            objectOutStream.writeObject(o);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
