package github.abhilashg97com.gitmelon;

import com.google.gson.annotations.SerializedName;

/**
 * Created by AbhilashG97 on 14/03/2018.
 */

public class Repository {

    @SerializedName("name")
    private String repositoryName;

    public Repository(String repositoryName) {
        this.repositoryName = repositoryName;
    }

    public String getRepositoryName() {
        return repositoryName;
    }

    @Override
    public String toString() {
        return repositoryName;
    }
}
