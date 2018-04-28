package github.abhilashg97com.gitmelon.repositoryrecyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import github.abhilashg97com.gitmelon.R;

public class RepositoryViewHolder extends RecyclerView.ViewHolder implements RepositoryItemMvpView {

    @BindView(R.id.tv_repository_name)
    TextView tvRepositoryName;

    public RepositoryViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setRepositoryName(String repositoryName) {
        tvRepositoryName.setText(repositoryName);
    }

}
