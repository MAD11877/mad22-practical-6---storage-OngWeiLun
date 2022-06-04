package sg.edu.np.mad.p02.activity1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MessageGroup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_group);

        Button grp2 = findViewById(R.id.group2);
        Button grp1 = findViewById(R.id.group1);


        grp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction()
                .replace(R.id.flMessage,Group1.class,null)
                        .commit();
            }
        });
        grp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction()
                .replace(R.id.flMessage,Group2.class,null)
                        .commit();
            }
        });

    }
}