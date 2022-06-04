package sg.edu.np.mad.p02.activity1;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class UserViewHolder extends RecyclerView.ViewHolder {
    TextView username;
    TextView description;

    public UserViewHolder(View viewItem){
        super(viewItem);
        username = viewItem.findViewById(R.id.name);
        description = viewItem.findViewById(R.id.description);
    }
}
