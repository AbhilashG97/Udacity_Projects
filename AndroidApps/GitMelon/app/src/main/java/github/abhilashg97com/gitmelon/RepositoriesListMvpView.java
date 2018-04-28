package github.abhilashg97com.gitmelon;

import java.util.List;

import github.abhilashg97com.gitmelon.base.MvpView;
import github.abhilashg97com.gitmelon.objects.Repository;

public interface RepositoriesListMvpView extends MvpView {

    void showMessage(int id);

    void showMessage(String message);

    void showRepositories(List<Repository> repositories);

    String getSearchQuery();
}
