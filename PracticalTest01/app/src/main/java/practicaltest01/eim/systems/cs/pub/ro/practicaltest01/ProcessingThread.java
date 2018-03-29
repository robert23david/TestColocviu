package practicaltest01.eim.systems.cs.pub.ro.practicaltest01;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

class ProcessingThread extends Thread{

    private Context context;
    private double ma;
    private double mg;
    final public static String DATA = "practicaltest01.eim.systems.cs.pub.ro.practicaltest01.data";
    final public static String INTEGER_DATA = new Date(System.currentTimeMillis()).toString();
    private int stillRunning = 0;

    private Random random = new Random();

    public ProcessingThread(Context context, double a, double b) {
        this.context = context;
        ma = (a+b)/2;
        mg = Math.sqrt(a * b);
    }

    @Override
    public void run() {
        while(stillRunning == 0) {
            sendMessage(random.nextInt(3));
            sleep();
        }
    }

    private void sendMessage(int messageType) {
        Intent intent = new Intent();
        switch (messageType) {
            case 0:
                intent.setAction("1");
                intent.putExtra("message", INTEGER_DATA + " " + ma + " " + mg);
                break;
            case 1:
                intent.setAction("2");
                intent.putExtra("message", INTEGER_DATA + " " + ma + " " + mg);
                break;
            case 2:
                intent.setAction("3");
                intent.putExtra("message", INTEGER_DATA + " " + ma + " " + mg);
                break;
        }
        context.sendBroadcast(intent);
    }

    private void sleep() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    public void stopThread() {
        stillRunning = 1;
    }

}
