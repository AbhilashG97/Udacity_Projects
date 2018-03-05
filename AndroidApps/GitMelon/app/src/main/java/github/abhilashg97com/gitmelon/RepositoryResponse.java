package github.abhilashg97com.gitmelon;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by AbhilashG97 on 15/03/2018.
 */

public class RepositoryResponse {

    @SerializedName("repositories")
    private ArrayList<Repository> repositories;

    public ArrayList<Repository> getRepositories() {
        return repositories;
    }

    public void setRepositories(ArrayList<Repository> repositories) {
        this.repositories = repositories;
    }
}
