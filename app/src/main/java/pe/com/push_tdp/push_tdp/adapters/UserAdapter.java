package pe.com.push_tdp.push_tdp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import pe.com.push_tdp.push_tdp.R;
import pe.com.push_tdp.push_tdp.models.User;

/**
 * Created by Angelo-pooh on 30/10/2017.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private List<User> listUser;
    private Context context;
    private View view;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_sign_up,parent,false);
        return new UserAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final User user= getListUser().get(position);

        holder.firstNameTextView.setText(user.getFirstNameUser());
        holder.lastNameTextView.setText(user.getLastNameUser());
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
        TextView firstNameTextView;
        TextView lastNameTextView;


        public ViewHolder(View itemView) {
            super(itemView);

            firstNameTextView=(TextView) itemView.findViewById(R.id.firstNameTextInputEditText);
            lastNameTextView=(TextView) itemView.findViewById(R.id.lastNameTextInputEditText);

        }
    }
}
