package server;

import commons.ClientListFromServer;
import commons.Message;
import commons.RequestMessage;
import commons.SharedClient;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ServerClient implements Runnable, Comparable<ServerClient> {
    private Socket clientSocket;
    // private ServerSocket ss;
    private InputStream is;
    private ObjectInputStream ois;
    private OutputStream outStream;
    public ObjectOutputStream objectOutStream;
    boolean running = false;
    private Server server;
    private int id;
    private String name;

    public ServerClient(Server server, Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
        this.server = server;
        is = clientSocket.getInputStream();
        ois = new ObjectInputStream(is);
        outStream = clientSocket.getOutputStream();
        objectOutStream = new ObjectOutputStream(outStream);
        running = true;

    }

    @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e2) {
                // TODO Auto-generated catch block
                e2.printStackTrace();
            }
            try {
                while (running && is.available() > 0) {
                    Object input = null;
                    try {
                        input = (Object) ois.readObject();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        // e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        System.out.println("Unknonwn class");
                        e.printStackTrace();
                    }

                    if (input != null && input instanceof String) {

                        String message = (String) input;
                        if (message.contains("/C/")) {
                            System.out.println(name + " receivedString: " + message);
                            String[] split = message.split("/");
                            name = split[0];
                            updateOnlineClients();
                            objectOutStream.writeObject(new Message("Forever alone", name, "Chat with the server if no one else is online."));
                        } else if (message.contains("/EXIT/")) {
                            exterminate();
                        }
                    } else if (input != null && input instanceof Message) {
                        Message mess = (Message) input;
                        if (mess.getTo().equals("Forever alone")) {
                            System.out.println("myself!");
//							sendObject(new Message("Forever alone", name, mess.getMessage()));
                        } else {
                            server.passOnMessage(mess);
                        }
                    } else if (input != null && input instanceof RequestMessage) {
                        RequestMessage requestMessage = (RequestMessage) input;
                        server.passOnRequest(requestMessage);
                    }

                }
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }

    }

    public void updateOnlineClients() {
        ArrayList<ServerClient> connectedClients = server.getServerClients();
        ArrayList<SharedClient> shareClientList = new ArrayList<SharedClient>();

        for (ServerClient s : connectedClients) {
            SharedClient share = new SharedClient(s.getID(), s.getName());
            shareClientList.add(share);
        }
        for (ServerClient s : connectedClients) {
            try {
                s.objectOutStream.writeObject(new ClientListFromServer(shareClientList));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getName() {
        return name;
    }

    public void exterminate() {
        running = false;

        server.exterminateMe(this);
        try {
            is.close();
            ois.close();
            objectOutStream.close();
            outStream.close();
            clientSocket.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public int getID() {
        return id;
    }

    @Override
    public int compareTo(ServerClient o) {
        return name.compareTo(o.getName());
    }

    public void sendObject(Object o) {
        try {
            objectOutStream.writeObject(o);
        } catch (IOException e) {
            System.out.println("Couldn't object");
            e.printStackTrace();
        }

    }

    public String toString() {
        return name;
    }


}