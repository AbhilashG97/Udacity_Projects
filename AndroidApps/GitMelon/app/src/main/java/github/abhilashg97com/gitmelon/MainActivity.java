package github.abhilashg97com.gitmelon;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URL;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import utilities.NetworkUtilities;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.et_url)
    EditText searchQuery;

    @BindView(R.id.tv_url_result)
    TextView urlResult;

    @BindView(R.id.btn_clear)
    Button clear;

    @BindView(R.id.btn_search)
    Button search;

    @BindView(R.id.pb_fetch_github_data)
    ProgressBar pbFetchingData;

    @BindView(R.id.error_message)
    TextView tvErrorMessage;

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

    private class GitHubQueryTask extends AsyncTask<URL, Void, String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pbFetchingData.setVisibility(VISIBLE);
        }

        @Override
        protected String doInBackground(URL... urls) {
            URL githubSearchURL = urls[0];
            try{
                setQueryResult(NetworkUtilities.getResponseFromHttpUrl(githubSearchURL));
            }catch(Exception e){
                e.printStackTrace();
            }
            return getQueryResult();
        }

        @Override
        protected void onPostExecute(String string) {
            pbFetchingData.setVisibility(INVISIBLE);
            if(string != null && !string.equals("")){
                displayQueryResult();
            }else{
                tvErrorMessage.setVisibility(VISIBLE);
                urlResult.setVisibility(INVISIBLE);
            }
        }
    }

    @OnClick(R.id.btn_search)
    public void makeGitHubQuery() {
        if(Pattern.matches("\\s*", searchQuery.getText().toString())){
            Toast.makeText(this, R.string.field_empty, Toast.LENGTH_SHORT).show();
        }else {
            urlResult.setText("");
            URL url = NetworkUtilities.buildUrl(searchQuery.getText().toString());
            try {
                new GitHubQueryTask().execute(url);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @OnClick(R.id.btn_clear)
    public void clearSearchField(){
        searchQuery.setText("");
        urlResult.setText("");
    }

    public void displayQueryResult() {
        Log.v("Get http request", getQueryResult());
        tvErrorMessage.setVisibility(INVISIBLE);
        urlResult.setVisibility(VISIBLE);
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
