package server;

import util.NetworkUtil;
import data.Restaurant;
import data.RestaurantDatabase;
import data.Food;
//import data.FileIO;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;

public class Server {
    volatile public data.RestaurantDatabase rdB;
    private FileOperations fileOperations;

    private ServerSocket serverSocket;
    public HashMap<String, String> userMap;

    public HashMap<String, NetworkUtil> clientMap;

    Server() throws Exception {
        userMap = new HashMap<>();
        userMap.put("kfc", "a");
        userMap.put("ihop", "b");
        userMap.put("starbucks", "c");
        userMap.put("mcdonalds", "d");
        userMap.put("customer", "e");
        //userMap.put("e", "e");
        clientMap = new HashMap<>();
        try {

            serverSocket = new ServerSocket(33333);
            while (true) {
                loadData();
                List<Restaurant> r = rdB.getResList();
                System.out.println(r);
                for(Restaurant res : r)
                {
                    List<Food> f = res.getFoods();
                    System.out.println(f);
                }
                Socket clientSocket = serverSocket.accept();
                serve(clientSocket);
            }
        } catch (Exception e) {
            System.out.println("Server starts:" + e);
        }
    }

    public void serve(Socket clientSocket) throws IOException, ClassNotFoundException {
        NetworkUtil networkUtil = new NetworkUtil(clientSocket);
        String user = (String) networkUtil.read();
        clientMap.put(user, networkUtil);
        new ReadThreadServer(userMap, clientMap, networkUtil, this);
    }
    private void loadData() throws Exception {
        rdB = new RestaurantDatabase();
        fileOperations = new FileOperations();
        fileOperations.readFromFile(rdB);
        fileOperations.readMenu(rdB);
    }
    public static void main(String[] args) throws Exception {
        new Server();
    }
}
