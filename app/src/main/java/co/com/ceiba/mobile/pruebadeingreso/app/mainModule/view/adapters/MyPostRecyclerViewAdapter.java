package co.com.ceiba.mobile.pruebadeingreso.app.mainModule.view.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import co.com.ceiba.mobile.pruebadeingreso.R;
import co.com.ceiba.mobile.pruebadeingreso.app.mainModule.models.PostBean;
import co.com.ceiba.mobile.pruebadeingreso.app.mainModule.view.UserListener;


public class MyPostRecyclerViewAdapter extends RecyclerView.Adapter<MyPostRecyclerViewAdapter.ViewHolder> {

    private  List<PostBean> mValues = null;

    public MyPostRecyclerViewAdapter( ) {
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list_post, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        holder.title.setText(mValues.get(position).getTitle());
        holder.body.setText(mValues.get(position).getBody());
    }

    @Override
    public int getItemCount() {
        return mValues==null ? 0 : mValues.size();
    }

    public void updateItems(List<PostBean> userEntities) {

        this.mValues = userEntities;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView title;
        public final TextView body;

        public ViewHolder(View view) {
            super(view);
            title =  view.findViewById(R.id.title);
            body =  view.findViewById(R.id.body);
        }
    }
}