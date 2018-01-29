package github.abhilashg97com.gitmelon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import utilities.NetworkUtilities;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.et_url)
    EditText searchQuery;

    @BindView(R.id.tv_url_result)
    TextView urlResult;

    @BindView(R.id.btn_clear)
    Button clear;

    @BindView(R.id.btn_search)
    Button search;
    private String queryResult;

    public String getQueryResult() {
        return queryResult;
    }

    public void setQueryResult(String queryResult) {
        this.queryResult = queryResult;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_search)
    public void makeGitHubQuery() {
        URL url = NetworkUtilities.buildUrl(searchQuery.getText().toString());
        try {
            setQueryResult(NetworkUtilities.getResponseFromHttpUrl(url));
            displayQueryResult();
        } catch (Exception ioe) {
            ioe.printStackTrace();
        }
    }

    public void displayQueryResult() {
        Log.v("Get http request", getQueryResult());
        urlResult.setText(getQueryResult());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.exit:
                finish();
                System.exit(0);
                break;
            case R.id.about:
                // TO DO
        }
        return super.onOptionsItemSelected(item);
    }
}
