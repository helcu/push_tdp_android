package pe.com.push_tdp.push_tdp;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Angelo-pooh on 23/10/2017.
 */

public class ViewHolder extends RecyclerView.ViewHolder {
    public ImageView imageCourse;
    public TextView nameCourse;
    public  TextView countOfStudents;

    public ViewHolder(View itemView) {
        super(itemView);

        imageCourse=(ImageView) itemView.findViewById(R.id.imageCourse);
        nameCourse=(TextView) itemView.findViewById(R.id.nameCourse);
        countOfStudents=(TextView) itemView.findViewById(R.id.numberOfStudentsView);


    }
}
