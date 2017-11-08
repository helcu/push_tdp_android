package pe.com.push_tdp.push_tdp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import pe.com.push_tdp.push_tdp.R;
import pe.com.push_tdp.push_tdp.models.Course;
import pe.com.push_tdp.push_tdp.models.User;

/**
 * Created by Angelo-pooh on 30/10/2017.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private List<User> listUser;
    private Context context;
    private View view;

    public UserAdapter(List<User> listCourse) {
        setListUser(listCourse);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_users_view,parent,false);
        return new UserAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final User user= getListUser().get(position);

        holder.nameTextView.setText(user.getName());
        holder.surnameTextView.setText(user.getSurname());
    }


    @Override
    public int getItemCount() {
        return getListUser().size();
    }

    public List<User> getListUser() {
        return listUser;
    }

    public void setListUser(List<User> listUser) {
        this.listUser = listUser;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView surnameTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.nameTextView);
            surnameTextView = (TextView) itemView.findViewById(R.id.surnameTextView);

        }
    }
}
