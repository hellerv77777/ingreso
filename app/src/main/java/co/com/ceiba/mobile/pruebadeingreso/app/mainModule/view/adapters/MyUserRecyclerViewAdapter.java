package co.com.ceiba.mobile.pruebadeingreso.app.mainModule.view.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import co.com.ceiba.mobile.pruebadeingreso.R;
import co.com.ceiba.mobile.pruebadeingreso.app.dataBase.entity.UserEntity;
import co.com.ceiba.mobile.pruebadeingreso.app.mainModule.view.UserListener;


public class MyUserRecyclerViewAdapter extends RecyclerView.Adapter<MyUserRecyclerViewAdapter.ViewHolder> {

    private  List<UserEntity> mValues = null;
    private UserListener mListener;

    public MyUserRecyclerViewAdapter(UserListener mListener ) {
        this.mListener = mListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.name.setText(mValues.get(position).getName());
        holder.phone.setText(mValues.get(position).getPhone());
        holder.email.setText(mValues.get(position).getEmail());

        holder.button.setOnClickListener(v -> mListener.onClickUser(holder.mItem));
    }

    @Override
    public int getItemCount() {
        return mValues==null ? 0 : mValues.size();
    }

    public void updateItems(List<UserEntity> userEntities) {

        this.mValues = userEntities;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView name;
        public final TextView phone;
        public final TextView email;
        public final Button button;
        public UserEntity mItem;

        public ViewHolder(View view) {
            super(view);
            name =  view.findViewById(R.id.name);
            phone =  view.findViewById(R.id.phone);
            email =  view.findViewById(R.id.email);
            button =view.findViewById(R.id.btn_view_post);
        }
    }
}