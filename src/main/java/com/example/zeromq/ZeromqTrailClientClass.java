package com.example.zeromq;

import org.zeromq.ZContext;
import org.zeromq.ZMQ;

/**
 * Created by skucukbay on 8/9/16.
 */
public class ZeromqTrailClientClass {
    private final static int REQUEST_TIMEOUT = 2500;    //  msecs, (> 1000!)
    private final static int REQUEST_RETRIES = 3;       //  Before we abandon
    private final static String SERVER_ENDPOINT = "tcp://localhost:5555";
    public static void clientTry() throws InterruptedException {
        ZContext ctx = new ZContext();
        System.out.println("I: connecting to server");
        ZMQ.Socket client = ctx.createSocket(ZMQ.REQ);
        ZMQ.Socket client2 = ctx.createSocket(ZMQ.REQ);
        ZMQ.Socket client3 = ctx.createSocket(ZMQ.REQ);
        assert (client != null);
        client.connect(SERVER_ENDPOINT);
        client2.connect(SERVER_ENDPOINT);
        client3.connect(SERVER_ENDPOINT);
        String request = String.format("%d", 1);

        client.send(request);
        client2.send(request);
        client3.send(request);
        int sequence = 0;
        int retriesLeft = REQUEST_RETRIES;
        /*while (retriesLeft > 0 && !Thread.currentThread().isInterrupted()) {
            //  We send a request, then we work to get a reply
            String request = String.format("%d", ++sequence);
            client.send(request);
//            Thread.sleep(10000);
            int expect_reply = 1;
            while (expect_reply > 0) {
                //  Poll socket for a reply, with timeout
                ZMQ.PollItem items[] = {new ZMQ.PollItem(client, ZMQ.Poller.POLLIN)};
                int rc = ZMQ.poll(items, REQUEST_TIMEOUT);
                if (rc == -1)
                    break;          //  Interrupted

                //  Here we process a server reply and exit our loop if the
                //  reply is valid. If we didn't a reply we close the client
                //  socket and resend the request. We try a number of times
                //  before finally abandoning:

                if (items[0].isReadable()) {
                    //  We got a reply from the server, must match sequence
                    String reply = client.recvStr();
                    if (reply == null)
                        break;      //  Interrupted
                    if (Integer.parseInt(reply) == sequence) {
                        System.out.printf("I: server replied OK (%s)\n", reply);
                        retriesLeft = REQUEST_RETRIES;
                        expect_reply = 0;
                    } else
                        System.out.printf("E: malformed reply from server: %s\n",
                                reply);

                } else if (--retriesLeft == 0) {
                    System.out.println("E: server seems to be offline, abandoning\n");
                    break;
                } else {
                    System.out.println("W: no response from server, retrying\n");
                    //  Old socket is confused; close it and open a new one
                    ctx.destroySocket(client);
                    System.out.println("I: reconnecting to server\n");
                    client = ctx.createSocket(ZMQ.REQ);
                    client.connect(SERVER_ENDPOINT);
                    //  Send request again, on new socket
                    client.send(request);
                }
            }
        }*/
        ctx.destroy();
    }

    public static void main(String[] args) throws InterruptedException {
        clientTry();

    }
}
