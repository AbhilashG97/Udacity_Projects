package github.abhilashg97com.gitmelon;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by AbhilashG97 on 14/03/2018.
 */

public class RepositoryItemListAdapter extends RecyclerView.Adapter<RepositoryItemListAdapter.RepositoryViewHolder> {

    private List<Repository> repositoriesList;
    private Context context;

    public RepositoryItemListAdapter(List<Repository> repositoriesList, Context context) {
        this.repositoriesList = repositoriesList;
        this.context = context;
    }

    public void clear(){
        final int size = repositoriesList.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                repositoriesList.remove(0);
            }
            notifyItemRangeRemoved(0, size);
        }
    }

    @Override
    public RepositoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.repository_list_item, parent, false);
        return new RepositoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RepositoryViewHolder holder, int position) {
        Repository repository = repositoriesList.get(position);
        if (holder.tvRepositoryName != null) {
            Log.v("Repository name", repository.getRepositoryName());
            holder.tvRepositoryName.setText(repository.getRepositoryName());
        }
    }

    @Override
    public int getItemCount() {
        int itemCount = -1;
        if(repositoriesList != null){
            itemCount = repositoriesList.size();
         }
        return itemCount;
    }

    public class RepositoryViewHolder extends RecyclerView.ViewHolder {

        private TextView tvRepositoryName;

        public RepositoryViewHolder(View itemView) {
            super(itemView);
            tvRepositoryName = itemView.findViewById(R.id.tv_repository_name);
        }
    }

}
