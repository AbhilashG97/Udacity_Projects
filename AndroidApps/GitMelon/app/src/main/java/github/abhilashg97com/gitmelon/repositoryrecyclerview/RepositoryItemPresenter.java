package github.abhilashg97com.gitmelon.repositoryrecyclerview;

import android.util.Log;

import java.util.ArrayList;


import github.abhilashg97com.gitmelon.objects.Repository;

public class RepositoryItemPresenter {

    private ArrayList<Repository> repositories;

    public RepositoryItemPresenter(ArrayList<Repository> repositories) {
        this.repositories = repositories;
    }

    public void onBindRepositoryView(RepositoryItemMvpView repositoriesListMvpView,
                                     int position) {
        Repository repository = repositories.get(position);
        if (repositoriesListMvpView != null) {
            Log.v("Repository name", repository.getRepositoryName());
            repositoriesListMvpView.setRepositoryName(repository.getRepositoryName());
        }
    }

    public int getRepositoriesCount() {
        return repositories.size();
    }

    public void removeItems(int numberOfItems) {
        for(int i=0; i<numberOfItems; i++) {
            repositories.remove(0);
        }
    }

    public ArrayList<Repository> getRepositories() {
        return repositories;
    }
}
