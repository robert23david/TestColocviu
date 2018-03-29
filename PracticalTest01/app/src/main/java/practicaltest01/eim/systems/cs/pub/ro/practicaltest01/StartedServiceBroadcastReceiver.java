package practicaltest01.eim.systems.cs.pub.ro.practicaltest01;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.TextView;

class StartedServiceBroadcastReceiver extends BroadcastReceiver {

    private TextView messageTextView;

    public StartedServiceBroadcastReceiver() {
        this.messageTextView = null;
    }

    public StartedServiceBroadcastReceiver(TextView messageTextView) {
        this.messageTextView = messageTextView;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // and set the text on the messageTextView
        Log.d("[Message]", intent.getStringExtra("message"));
        String action = intent.getAction();
        String data = null;
        if ("1".equals(action)) {
            data = intent.getStringExtra("message");
        }
        if ("2".equals(action)) {
            data =intent.getStringExtra("message");
        }
        if ("3".equals(action)) {
            data = intent.getStringExtra("message");
        }
        if (messageTextView != null) {
            messageTextView.setText(messageTextView.getText().toString() + "\n" + data);
        }

    }
}
