package com.example.tbm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button sign_out;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference ref;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        ref = FirebaseDatabase.getInstance().getReference().child("main");
        auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() == null) {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        }


        sign_out = (Button) findViewById(R.id.sign_out);
        sign_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.signOut();
                FirebaseAuth.AuthStateListener authListener = new FirebaseAuth.AuthStateListener() {
                    @Override
                    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        if (user == null) {
                            startActivity(new Intent(MainActivity.this, LoginActivity.class));
                            finish();
                        }
                    }
                };
                authListener.onAuthStateChanged(auth);
            }
        });

        final ArrayList<ItemCard> list = new ArrayList<>();
        ref.child("posts").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot post : snapshot.getChildren()) {
                    try {
                        String image_url = post.child("image_url").getValue().toString();
                        String caption = post.child("caption").getValue().toString();
                        ItemCard temp = new ItemCard(image_url,caption);

                       int c = Integer.parseInt(post.child("reactions/brave").getValue().toString());
                        temp.setBraveCounter(c);
                         c = Integer.parseInt(post.child("reactions/happy").getValue().toString());
                        temp.setHappyCounter(c);
                         c = Integer.parseInt(post.child("reactions/likes").getValue().toString());
                        temp.setLikeCounter(c);
                         c = Integer.parseInt(post.child("reactions/love").getValue().toString());
                        temp.setLoveCounter(c);
                         c = Integer.parseInt(post.child("reactions/sad").getValue().toString());
                        temp.setSadCounter(c);
                        list.add(temp);

                    }catch (Exception e){
                       e.printStackTrace();
                    }
                }
                setRecyclerView(list);

            }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //String url ="https://instagram.ftlv1-1.fna.fbcdn.net/v/t51.2885-15/e35/19932366_111802176122106_559666614132277248_n.jpg?tp=1&_nc_ht=instagram.ftlv1-1.fna.fbcdn.net&_nc_cat=106&_nc_ohc=B_m06H7p03AAX8TBDxQ&ccb=7-4&oh=02c58c14b39f69dc3079329359b61f17&oe=608AEF20&_nc_sid=4efc9f";
        String url ="https://instagram.ftlv1-1.fna.fbcdn.net/v/t51.2885-15/e35/19429123_188512221678616_5156252335000780800_n.jpg?tp=1&_nc_ht=instagram.ftlv1-1.fna.fbcdn.net&_nc_cat=104&_nc_ohc=I4bBc8sIcPgAX_Ie6bC&ccb=7-4&oh=a3558a000c6f06fd44f11196445b4c60&oe=608AD6FD&_nc_sid=4efc9f";

//        ItemCard ic1 = new ItemCard(url,"liad");
//        ItemCard ic2 = new ItemCard(url,"tal");
//        list.add(ic1);
//        list.add(ic2);
        setRecyclerView(list);
    }
    private void setRecyclerView(ArrayList<ItemCard> list)
    {
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new PostAdapter(list);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }
}







