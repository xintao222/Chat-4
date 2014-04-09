package client;

import commons.ClientListFromServer;
import commons.Message;
import commons.SharedClient;

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
            System.out.println("make clientsocket");
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
                        input = (Object) ObjInputStream.readObject();
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
                        System.out.println("After Update clients: " + allConnectedNames);
                        setChanged();
                        notifyObservers();
                    }
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
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


            try {
                objectOutStream.writeObject(new Message(loginName, chatWith, message));
            } catch (IOException e) {
                e.printStackTrace();
            }

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

}
