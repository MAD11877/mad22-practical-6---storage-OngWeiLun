package sg.edu.np.mad.p02.activity1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseError;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        FirebaseDatabase db = FirebaseDatabase.getInstance();

        Button loginBtn = findViewById(R.id.buttonLogin);

        loginBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                EditText editUsername = findViewById(R.id.etUsername);
                EditText editPassword = findViewById(R.id.etPassword);

                String username = editUsername.getText().toString();
                String password = editPassword.getText().toString();

                DatabaseReference myRef = db.getReference("Users/" + username);


                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.getValue() == null){
                            Toast.makeText(Login.this, "Username does not exist", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        String dbpassword = snapshot.getValue().toString();

                        if(dbpassword != password){
                            Toast.makeText(Login.this, "Wrong password", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        Intent intent = new Intent(Login.this, ListActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }
}