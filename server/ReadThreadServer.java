package server;

import Networks.*;
import data.AllFood;
import data.Restaurant;
import util.LoginDTO;
import util.NetworkUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class ReadThreadServer implements Runnable {
    private final Thread thr;
    private final NetworkUtil networkUtil;
    public HashMap<String, String> userMap;

    public HashMap<String, NetworkUtil> clientMap;
    private Server server;

    public ReadThreadServer(HashMap<String, String> map, HashMap<String, NetworkUtil> m, NetworkUtil networkUtil, Server server) {
        this.userMap = map;
        this.networkUtil = networkUtil;
        this.clientMap = m;
        this.server = server;
        this.thr = new Thread(this);
        thr.start();
    }

    public void run() {
        try {
            while (true) {
                Object o = networkUtil.read();
                if (o != null) {
                    /*if (o instanceof LoginDTO) {
                        LoginDTO loginDTO = (LoginDTO) o;
                        String password = userMap.get(loginDTO.getUserName());
                        loginDTO.setStatus(loginDTO.getPassword().equals(password));
                        networkUtil.write(loginDTO);
                    }*/
                    if(o instanceof LoginInfo)
                    {
                        LoginInfo loginInfo = (LoginInfo) o;
                        if(loginInfo.getMessageHeader() == MessageHeader.LOGIN)
                        {
                            if(loginInfo.getUsername().equals("customer"))
                            {
                                loginInfo.isCus(true);
                                loginInfo.setStatus(true);
                                networkUtil.write(loginInfo);
                            }
                            else {
                                String password = userMap.get(loginInfo.getUsername());
                                loginInfo.setStatus(loginInfo.getPassword().equals(password));
                                networkUtil.write(loginInfo);
                            }
                        }
                    }
                    if(o instanceof Message)
                    {
                        Message msg = (Message) o;
                        if(msg.getMessageHeader() == MessageHeader.RES_INFO)
                        {
                            List<Restaurant> r = server.rdB.getResList();
                            for(Restaurant res : r)
                            {
                                if(res.getRestaurantName().equalsIgnoreCase(msg.getMessage()))
                                {
                                    networkUtil.write(res);
                                }
                            }
                        }
                        else if(msg.getMessageHeader() == MessageHeader.ALLFOOD)
                        {
                            AllFood food = server.rdB.getAll();
                            networkUtil.write(food);
                        }
                        else if(msg.getMessageHeader() == MessageHeader.SEARCHBYNAME)
                        {
                            AllFood food = server.rdB.getAllByName(msg.getMessage());
                            networkUtil.write(food);
                        }
                        else if(msg.getMessageHeader() == MessageHeader.SEARCHBYCAT)
                        {
                            AllFood food = server.rdB.getCat(msg.getMessage());
                            networkUtil.write(food);
                        }
                    }
                    if(o instanceof DualMsg)
                    {
                        DualMsg msg = (DualMsg) o;
                        if(msg.getMessageHeader() == MessageHeader.SEARCHBYRESINNAME)
                        {
                            String resName = msg.getMessage1();
                            String fName = msg.getMessage2();
                            AllFood food = server.rdB.getFoodInRes(resName, fName);
                            networkUtil.write(food);
                        }
                        else if(msg.getMessageHeader() == MessageHeader.SEARCHBYCATINRES)
                        {
                            String resName = msg.getMessage1();
                            String fName = msg.getMessage2();
                            AllFood food = server.rdB.getCatInRes(resName, fName);
                            networkUtil.write(food);
                        }
                        else if(msg.getMessageHeader() == MessageHeader.SEARCHBYPRICE)
                        {
                            int high = Integer.parseInt(msg.getMessage1());
                            int low = Integer.parseInt(msg.getMessage2());
                            AllFood food = server.rdB.getPrice(high, low);
                            networkUtil.write(food);
                        }
                    }
                    if(o instanceof OrderList)
                    {
                        OrderList obj = (OrderList) o;
                        String key = obj.getResName();
                        AllFood f = obj.getF();
                        NetworkUtil nu = clientMap.get(key);
                        if(nu != null)
                        {
                            nu.write(f);
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                networkUtil.closeConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}



