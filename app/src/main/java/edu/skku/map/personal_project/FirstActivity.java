package edu.skku.map.personal_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class FirstActivity extends AppCompatActivity {
    private DatabaseReference fireref = FirebaseDatabase.getInstance().getReference();
    String userid, pssw;
    EditText useruser, pswpsw;
    HashMap<String, String> forLogin = new HashMap<String, String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        useruser = findViewById(R.id.userid);
        pswpsw = findViewById(R.id.password);
        getFB();
        Button button = (Button)findViewById(R.id.login);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userid = useruser.getText().toString();
                pssw = pswpsw.getText().toString();
                if(userid.length() * pssw.length() == 0)
                {
                    Toast.makeText(FirstActivity.this, "모든 항목을 입력해주세요", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(!(forLogin.containsKey(userid)))
                    {
                        Toast.makeText(FirstActivity.this, "존재하지 않는 아이디입니다", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        if(pssw.equals(forLogin.get(userid))){
                            Intent intent = new Intent(FirstActivity.this, MainActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(FirstActivity.this, "잘못된 비밀번호입니다", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
        TextView signin = (TextView)findViewById(R.id.sign);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotosign = new Intent(FirstActivity.this, SignActivity.class);
                startActivity(gotosign);
            }
        });
    }
    public void getFB()
    {
        final ValueEventListener postlistner =new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot postSnapshot : dataSnapshot.getChildren())
                {
                    String key = postSnapshot.getKey();
                    FabePost get = postSnapshot.getValue(FabePost.class);
                    String pass = get.psword;
                    Log.d("getFirebaseDatabase", ""+pass);
                    forLogin.put(key, pass);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        fireref.child("userlist").addValueEventListener(postlistner);
    }
    public void clearET() {
        useruser.setText("");
        pswpsw.setText("");
        userid = "";
        pssw = "";
    }
}