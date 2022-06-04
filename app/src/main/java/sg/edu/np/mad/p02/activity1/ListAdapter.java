package sg.edu.np.mad.p02.activity1;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ListAdapter extends RecyclerView.Adapter<UserViewHolder> {

    ListActivity listActivity;
    ArrayList<User> data;

    AlertDialog.Builder profile;

    public ListAdapter(ArrayList<User> userList,ListActivity listActivity){
        data = userList;
        this.listActivity = listActivity;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(
            ViewGroup parent,
            int viewType
    ){
        View item = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_block,
                        parent,
                        false);

        item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent send = new Intent(listActivity,ShowMessage.class);
                AlertDialog alert = profile.create();
                alert.show();
            }
        });

        return new UserViewHolder(item);
    }

    @Override
    public void onBindViewHolder(
            @NonNull UserViewHolder holder,
            int position
    ){
        User s = data.get(getHolderPos(holder));
        holder.description.setText(s.description);
        holder.username.setText(s.name);


        profile = new AlertDialog.Builder(listActivity);
        profile.setTitle("Profile");
        profile.setMessage(s.name);
        profile.setCancelable(true);
        profile.setPositiveButton("VIEW", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent MainActivity = new Intent(listActivity,
                        MainActivity.class);

                MainActivity.putExtra("username",s.name);
                MainActivity.putExtra("description",s.description);
                MainActivity.putExtra("follow",s.followed);
                MainActivity.putExtra("id",s.id);
                listActivity.startActivity(MainActivity);
            }
        });
        profile.setNegativeButton("CLOSE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
    }

    @Override
    public int getItemCount(){
        return data.size();
    }

    public int getHolderPos(UserViewHolder holder){
        int position = holder.getAdapterPosition();
        return position;
    }
}
