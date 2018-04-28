package github.abhilashg97com.gitmelon.repositoryrecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import github.abhilashg97com.gitmelon.R;


/**
 * Created by AbhilashG97 on 14/03/2018.
 */

public class RepositoryItemListAdapter extends RecyclerView.Adapter<RepositoryViewHolder> {


    private final RepositoryItemPresenter presenter;

    private Context context;

    public RepositoryItemListAdapter(RepositoryItemPresenter presenter, Context context) {
        this.presenter = presenter;
        this.context = context;
    }

    @Override
    public RepositoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RepositoryViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.repository_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RepositoryViewHolder holder, int position) {
        presenter.onBindRepositoryView(holder, position);
    }

    @Override
    public int getItemCount() {
        return presenter.getRepositoriesCount();
    }

    public void clear(){
        final int size = presenter.getRepositoriesCount();
        if (size > 0) {
            presenter.removeItems(size);
            notifyItemRangeRemoved(0, size);
        }
    }
}
