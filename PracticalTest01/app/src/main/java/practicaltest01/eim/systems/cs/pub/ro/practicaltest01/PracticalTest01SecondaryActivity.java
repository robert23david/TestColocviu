package practicaltest01.eim.systems.cs.pub.ro.practicaltest01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class PracticalTest01SecondaryActivity extends AppCompatActivity {

    private TextView authentication_result_text_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_secondary);

        authentication_result_text_view = (TextView) findViewById(R.id.result_text_view);
        Intent intentThatStartedThisActivity = getIntent();
        if (intentThatStartedThisActivity.hasExtra("total")) {
            Integer textEntered = intentThatStartedThisActivity.getIntExtra("total", 0);
            authentication_result_text_view.setText(textEntered.toString());
        }
     }

    public void goBack(View view) {
        setResult(111);
        finish();
    }
}
