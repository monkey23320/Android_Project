package edu.skku.map.personal_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SignActivity extends AppCompatActivity {
    private DatabaseReference fireref = FirebaseDatabase.getInstance().getReference();
    EditText idz;
    EditText psz;
    EditText ps2z;
    EditText namez;
    EditText birthz;
    EditText mailz;
    String ids, pss, ps2s, names, births, mails;
    ArrayList<String> keys = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        idz = findViewById(R.id.IDet);
        psz = findViewById(R.id.pswordet);
        ps2z = findViewById(R.id.pswordtwoet);
        namez = findViewById(R.id.nameet);
        birthz = findViewById(R.id.birthet);
        mailz = findViewById(R.id.mailet);
        getFB();
        Button account = (Button)findViewById(R.id.account);
        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ids = idz.getText().toString();
                pss = psz.getText().toString();
                ps2s = ps2z.getText().toString();
                names = namez.getText().toString();
                births = birthz.getText().toString();
                mails = mailz.getText().toString();
                if(ids.length() * pss.length() * ps2s.length() * names.length()*births.length()*mails.length() == 0)
                {
                    Toast.makeText(SignActivity.this, "모든 문항을 입력해주세요", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(keys.contains(ids) || ids.length() < 8 ||ids.length()>15)
                    {
                        Toast.makeText(SignActivity.this, "다른 아이디를 입력해주세요", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        if(!pss.equals(ps2s))
                        {
                            Toast.makeText(SignActivity.this, "같은 비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            if(pss.length() < 4 || pss.length() > 16)
                            {
                                Toast.makeText(SignActivity.this, "다른 비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Intent gotofirst = new Intent(SignActivity.this, FirstActivity.class);
                                postFB(true);
                                startActivity(gotofirst);
                            }
                        }
                    }
                }
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
                keys.add(key);
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };
    fireref.child("userlist").addValueEventListener(postlistner);
    }

    public void postFB(boolean add){
        Map<String, Object> childUp = new HashMap<>();
        Map<String, Object> postV = null;
        if(add)
        {
            FabePost post = new FabePost(ids, pss, names, births, mails);
            postV = post.toMap();
        }
        childUp.put("/userlist/" + ids, postV);
        fireref.updateChildren(childUp);
        clearET();
    }
    public void clearET()
    {
        idz.setText("");
        psz.setText("");
        ps2z.setText("");
        namez.setText("");
        birthz.setText("");
        mailz.setText("");
        ids = "";
        pss = "";
        ps2s = "";
        names = "";
        births = "";
        mails = "";
    }
}