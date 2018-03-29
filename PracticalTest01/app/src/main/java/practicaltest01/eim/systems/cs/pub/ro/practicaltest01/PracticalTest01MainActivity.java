package practicaltest01.eim.systems.cs.pub.ro.practicaltest01;

import android.app.Activity;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.provider.ContactsContract;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class PracticalTest01MainActivity extends AppCompatActivity {

    private TextView result_text_view;
    private EditText left_edit;
    private EditText right_edit;
    private Button press_left;
    private Button press_right;
    private Button navigate_to_sec_activ;
    private Integer limit = 5;
    public boolean serviceStatus = false;
    private IntentFilter startedServiceIntentFilter;
    private StartedServiceBroadcastReceiver startedServiceBroadcastReceiver;

    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            Integer total = 0;
            switch (view.getId()) {
                case R.id.press_me_left:
                    Integer leftText = Integer.parseInt(left_edit.getText().toString()) + 1;
                    left_edit.setText(leftText.toString());
                    total = Integer.parseInt(left_edit.getText().toString()) + Integer.parseInt(right_edit.getText().toString());
                    if (total >= limit && !serviceStatus) {
                        Intent intent = new Intent();
                        intent.setComponent(new ComponentName(getApplicationContext(), PracticalTest01Service.class));
                        intent.putExtra("a",  Integer.parseInt(left_edit.getText().toString()));
                        intent.putExtra("b",  Integer.parseInt(right_edit.getText().toString()));
                        startService(intent);
                        serviceStatus = true;
                    }
                    break;
                case R.id.press_me_right:
                    Integer rightText = Integer.parseInt(right_edit.getText().toString()) + 1;
                    right_edit.setText(rightText.toString());
                    total = Integer.parseInt(left_edit.getText().toString()) + Integer.parseInt(right_edit.getText().toString());
                    if (total >= limit && !serviceStatus) {
                        Intent intent = new Intent();
                        intent.setComponent(new ComponentName(getApplicationContext(), PracticalTest01Service.class));
                        intent.putExtra("a",  Integer.parseInt(left_edit.getText().toString()));
                        intent.putExtra("b",  Integer.parseInt(right_edit.getText().toString()));
                        startService(intent);
                        serviceStatus = true;
                    }
                    break;
                case R.id.navigate_to_sec_activ:
                    total = Integer.parseInt(left_edit.getText().toString()) + Integer.parseInt(right_edit.getText().toString());

                    Context context = PracticalTest01MainActivity.this;
                    Class cls = PracticalTest01SecondaryActivity.class;
                    Intent intent = new Intent(context, cls);
                    intent.putExtra("total", total);
                    startActivityForResult(intent, 111);
                    break;
            }
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        registerReceiver(startedServiceBroadcastReceiver, startedServiceIntentFilter);
    }

    @Override
    protected void onPause() {
        unregisterReceiver(startedServiceBroadcastReceiver);

        super.onPause();
    }

    @Override
    protected void onDestroy() {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(getApplicationContext(), PracticalTest01Service.class));
        stopService(intent);

        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_main);

        result_text_view = (TextView) findViewById(R.id.result_text_view);
        left_edit = (EditText) findViewById(R.id.left_edit_text);
        right_edit = (EditText) findViewById(R.id.right_edit_text);
        press_left = (Button) findViewById(R.id.press_me_left);
        press_right = (Button) findViewById(R.id.press_me_right);
        navigate_to_sec_activ = (Button) findViewById(R.id.navigate_to_sec_activ);

        press_left.setOnClickListener(buttonClickListener);
        press_right.setOnClickListener(buttonClickListener);
        navigate_to_sec_activ.setOnClickListener(buttonClickListener);

        startedServiceBroadcastReceiver = new StartedServiceBroadcastReceiver(result_text_view);
        startedServiceIntentFilter = new IntentFilter();
        startedServiceIntentFilter.addAction("1");
        startedServiceIntentFilter.addAction("2");
        startedServiceIntentFilter.addAction("3");
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        left_edit = (EditText) findViewById(R.id.left_edit_text);
        right_edit = (EditText) findViewById(R.id.right_edit_text);
            savedInstanceState.putString("LEFT_EDIT_TEXT", left_edit.getText().toString());
            savedInstanceState.putString("RIGHT_EDIT_TEXT", right_edit.getText().toString());
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState.containsKey("LEFT_EDIT_TEXT")) {
            left_edit = (EditText) findViewById(R.id.left_edit_text);
            left_edit.setText(savedInstanceState.getString("LEFT_EDIT_TEXT"));
        }
        if (savedInstanceState.containsKey("RIGHT_EDIT_TEXT")) {
            right_edit = (EditText) findViewById(R.id.right_edit_text);
            right_edit.setText(savedInstanceState.getString("RIGHT_EDIT_TEXT"));
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        switch (requestCode) {
            case 111:
                Toast.makeText(this, "Activity returned with result " + resultCode, Toast.LENGTH_LONG).show();
                break;
        }
    }
}
