package com.example.zeromq;

import org.zeromq.ZContext;
import org.zeromq.ZMQ;

import java.util.Random;

/**
 * Created by skucukbay on 8/9/16.
 */
public class ZeromqTrailServerClass {

    private final static int REQUEST_TIMEOUT = 2500;    //  msecs, (> 1000!)
    private final static int REQUEST_RETRIES = 3;       //  Before we abandon
    private final static String SERVER_ENDPOINT = "tcp://localhost:5555";

    public static void tryMethod(){

        ZMQ.Context context = ZMQ.context(1);
        ZMQ.Socket server = context.socket(ZMQ.REP);
        server.bind("tcp://*:5555");

    }

    public static void tryMethod2(){
        ZContext ctx = new ZContext();
        System.out.println("I: connecting to server");
        ZMQ.Socket client = ctx.createSocket(ZMQ.REQ);
        assert (client != null);
        client.connect(SERVER_ENDPOINT);
    }

    public static void serverTry() throws Exception
    {

        ZMQ.Context context = ZMQ.context(1);
        ZMQ.Socket server = context.socket(ZMQ.REP);
        server.bind("tcp://*:5555");

        int cycles = 0;
        while (true) {
            String request = server.recvStr();


            System.out.printf("I: normal request (%s)\n", request);
            Thread.sleep(10000l);
            server.send(request);
        }
    }



    public static void main(String[] args) throws Exception {
        serverTry();
    }
}
