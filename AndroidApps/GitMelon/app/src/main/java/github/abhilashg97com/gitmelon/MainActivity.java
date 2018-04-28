package github.abhilashg97com.gitmelon;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import github.abhilashg97com.gitmelon.objects.Repository;
import github.abhilashg97com.gitmelon.repositoryrecyclerview.RepositoryItemListAdapter;
import github.abhilashg97com.gitmelon.repositoryrecyclerview.RepositoryItemPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import utilities.rest.ApiClient;
import utilities.rest.ApiInterface;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class MainActivity extends AppCompatActivity implements RepositoriesListMvpView {

    @BindView(R.id.et_url)
    EditText searchQuery;

    @BindView(R.id.rv_repository_list_items)
    RecyclerView rvRepositoryNames;

    @BindView(R.id.btn_clear)
    Button clear;

    @BindView(R.id.btn_search)
    Button search;

    @BindView(R.id.pb_fetch_github_data)
    ProgressBar pbFetchingData;

    @BindView(R.id.error_message)
    TextView tvErrorMessage;

    private RepositoryItemListAdapter repositoryAdapter;
    private Context context;
    private ApiInterface apiService;
    private String enteredUsername;
    private RepositoryItemPresenter repositoryItemPresenter;


    public String getEnteredUsername() {
        return enteredUsername;
    }

    public void setEnteredUsername(String enteredUsername) {
        this.enteredUsername = enteredUsername;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        context = this;
    }

    @OnClick(R.id.btn_search)
    public void makeGitHubQuery() {
        setEnteredUsername(searchQuery.getText().toString());
        if (Pattern.matches("\\s*", searchQuery.getText().toString())) {
            Toast.makeText(this, R.string.field_empty, Toast.LENGTH_SHORT).show();
        } else {
            Log.v("User entered value", searchQuery.getText().toString());
            try {
                apiService = ApiClient.getClient().create(ApiInterface.class);
                new GitHubQueryTask().execute(apiService);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @OnClick(R.id.btn_clear)
    public void clearSearchField() {
        searchQuery.setText("");
        if (repositoryAdapter != null) {
            repositoryAdapter.clear();
        }
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
                // TODO (4) : Create an about dialog box for the application
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showMessage(int id) {
        Toast.makeText(context, getString(id), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showRepositories(List<Repository> repositories) {

    }

    @Override
    public String getSearchQuery() {
        return enteredUsername;
    }

    @Override
    public void showProgressBar(boolean show) {
        if (show) {
            pbFetchingData.setVisibility(VISIBLE);
        } else {
            pbFetchingData.setVisibility(INVISIBLE);
        }
    }

    private class GitHubQueryTask extends AsyncTask<ApiInterface, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pbFetchingData.setVisibility(VISIBLE);
        }

        @Override
        protected String doInBackground(ApiInterface... apiInterfaces) {

            ApiInterface apiInterface = apiInterfaces[0];
            Call<ArrayList<Repository>> call = apiInterface.getRepositories(getEnteredUsername());
            call.enqueue(new Callback<ArrayList<Repository>>() {
                @Override
                public void onResponse(Call<ArrayList<Repository>> call, Response<ArrayList<Repository>> response) {
                    Log.v("Formed URL Retrofit -> ", call.request().url().toString());

                    if (response.body() != null) {
                        repositoryItemPresenter = new RepositoryItemPresenter(response.body());
                        rvRepositoryNames.setHasFixedSize(true);
                        rvRepositoryNames.setLayoutManager(new LinearLayoutManager(context));
                        Log.v("Repository list -> ", repositoryItemPresenter.getRepositories().toString());
                        repositoryAdapter = new RepositoryItemListAdapter(repositoryItemPresenter, context);
                        rvRepositoryNames.setAdapter(repositoryAdapter);
                        Log.v("Repositories ->", repositoryItemPresenter.getRepositories().toString());
                    } else {
                        showMessage(R.string.error_no_such_user_exists);
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<Repository>> call, Throwable t) {
                    Log.v("Formed URL Retrofit -> ", call.request().url().toString());
                    Log.e("Retrofit Failure", t.toString());
                }
            });
            return null;
        }

        @Override
        protected void onPostExecute(String string) {
            pbFetchingData.setVisibility(INVISIBLE);
        }
    }
}
