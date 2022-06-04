package sg.edu.np.mad.p02.activity1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Parcelable;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadProfile();

        Button messageButton = (Button) findViewById(R.id.messageButton);

        messageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent message = new Intent(MainActivity.this,
                        MessageGroup.class);
                startActivity(message);
            }
        });
    }

    public void loadProfile(){
        Intent receivingEnd = getIntent();
        String username = receivingEnd.getStringExtra("username");
        String description = receivingEnd.getStringExtra("description");
        boolean follow = Boolean.parseBoolean(receivingEnd.getStringExtra("follow"));
        TextView name = findViewById(R.id.nameBox);
        TextView desc = findViewById(R.id.descBox);
        TextView followButton = findViewById(R.id.followButton);
        name.setText(username);
        desc.setText(description);
        if(follow == true){
            followButton.setText("UNFOLLOW");
        }
        else{
            followButton.setText("FOLLOW");
        }
    }

    public void following(View view){
        TextView txt = findViewById(R.id.followButton);
        if (txt.getText().toString() == "FOLLOW"){
            txt.setText("UNFOLLOW");
            Toast.makeText(getApplicationContext(),"FOLLOWED", Toast.LENGTH_SHORT).show();
        }
        else{
            txt.setText("FOLLOW");
            Toast.makeText(getApplicationContext(), "UNFOLLOWED",Toast.LENGTH_SHORT).show();
        }
    }
}

