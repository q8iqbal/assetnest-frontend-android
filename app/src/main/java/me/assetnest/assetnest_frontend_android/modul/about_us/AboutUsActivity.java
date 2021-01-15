package me.assetnest.assetnest_frontend_android.modul.about_us;

import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import me.assetnest.assetnest_frontend_android.R;

public class AboutUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        setTvSupervisorsListText();
    }

    private void setTvSupervisorsListText() {
        String text = getString(R.string.about_us_tv_supervisors_list_text);

        ((TextView) findViewById(R.id.about_us_tv_supervisors_list))
                .setText(Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY));
    }
}
