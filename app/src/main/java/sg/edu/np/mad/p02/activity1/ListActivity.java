package sg.edu.np.mad.p02.activity1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        DBHandler db = new DBHandler(this ,null,null,1);
        createList(db);

        ArrayList<User> userList = db.getUsers();

        Log.d("ListSize","Length: " + userList.size());


        AlertDialog.Builder profile = new AlertDialog.Builder(this);
        profile.setTitle("Profile");
        profile.setMessage("MADness");
        profile.setCancelable(true);
        profile.setPositiveButton("VIEW", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent MainActivity = new Intent(ListActivity.this,
                        MainActivity.class);
                Random rand = new Random();
                int random = rand.nextInt(1000000);
                String num = Integer.toString(random);
                MainActivity.putExtra("number",num);
                startActivity(MainActivity);
            }
        });
        profile.setNegativeButton("CLOSE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        RecyclerView listRV = findViewById(R.id.list_rv);
        ListAdapter l_adapter = new ListAdapter(userList,this);

        LinearLayoutManager listLayoutManager = new LinearLayoutManager(this);

        listRV.setLayoutManager(listLayoutManager);
        listRV.setItemAnimator(new DefaultItemAnimator());
        listRV.setAdapter(l_adapter);
    }

    public void createList(DBHandler db){

        ArrayList<User> uList = new ArrayList<>();

        for( int i = 0; i < 20; i++){
            Random rand = new Random();
            int followRand = rand.nextInt(2);
            boolean followed;
            if(followRand == 1){
                followed = true;
            }
            else{
                followed = false;
            }

            String name_num = Integer.toString(rand.nextInt(1000000000));
            String desc = Integer.toString(rand.nextInt(1000000000));

            User newUser = new User("Name-" + name_num,"Description-" + desc, 0, followed);
            db.addUser(newUser);
        }
    }

/*    public ArrayList<User> createList(){

        ArrayList<User> uList = new ArrayList<>();

        for( int i = 0; i < 20; i++){
            Random rand = new Random();
            int followRand = rand.nextInt(2);
            boolean followed;
            if(followRand == 1){
                followed = true;
            }
            else{
                followed = false;
            }
            int userId = rand.nextInt(1000000000);
            String desc = Integer.toString(rand.nextInt(1000000000));
            User newUser = new User("Name","Description - " + desc, userId, followed);
            Log.d("#" + i,"User1:" + newUser.getName() + newUser.description);
            uList.add(newUser);
        }
        return uList;
    }*/



}