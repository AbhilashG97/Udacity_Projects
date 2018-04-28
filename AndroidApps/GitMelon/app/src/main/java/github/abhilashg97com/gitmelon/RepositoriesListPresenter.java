package github.abhilashg97com.gitmelon;

import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;

import github.abhilashg97com.gitmelon.base.BasePresenter;
import github.abhilashg97com.gitmelon.objects.Repository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import utilities.rest.ApiInterface;

public class RepositoriesListPresenter extends BasePresenter<RepositoriesListMvpView> {

    private ArrayList<Repository> repositoryArrayList;

    @Override
    public void attachView(RepositoriesListMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    public ArrayList<Repository> loadRepositories() {
        checkViewAttached();
        class GitHubQueryTask extends AsyncTask<ApiInterface, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                getMvpView().showProgressBar(true);
            }

            @Override
            protected String doInBackground(ApiInterface... apiInterfaces) {

                ApiInterface apiInterface = apiInterfaces[0];
                Call<ArrayList<Repository>> call = apiInterface.getRepositories(getMvpView().getSearchQuery());
                call.enqueue(new Callback<ArrayList<Repository>>() {
                    @Override
                    public void onResponse(Call<ArrayList<Repository>> call, Response<ArrayList<Repository>> response) {
                        Log.v("Formed URL Retrofit -> ", call.request().url().toString());

                        if(response.body() != null) {
                            repositoryArrayList = response.body();
                            Log.v("Repository list -> ", repositoryArrayList.toString());
                            Log.v("Repositories ->", repositoryArrayList.toString());
                        }else {
                            getMvpView().showMessage(R.string.error_no_such_user_exists);
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
                getMvpView().showProgressBar(false);
            }
        }

        return repositoryArrayList;
    }
}
