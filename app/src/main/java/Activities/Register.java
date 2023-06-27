package Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myinfo.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import Helpers.Regno;

public class Register extends AppCompatActivity {
    private EditText vUserName, vPassword, vName;
    private ProgressBar vProgressBar;
    private RelativeLayout vSingUpBtn;
    private TextView vMvSignin;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        vProgressBar = findViewById(R.id.ProgressbarSignin);
        vProgressBar.setVisibility(View.INVISIBLE);
        // axilary jobs
        vMvSignin = findViewById(R.id.backtologin);
        vMvSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Login.class));
                finish();
            }
        });

        //main register
        vSingUpBtn = findViewById(R.id.btnSignIN);
        vSingUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vUserName = findViewById(R.id.signUPEmailID);
                vPassword = findViewById(R.id.SignUPPassword);
                vName = findViewById(R.id.regno);
                String username = vUserName.getText().toString().trim();
                String password = vPassword.getText().toString().trim();
                String regno = vName.getText().toString().trim();
                if(username.isEmpty()){
                    Toast.makeText(Register.this, "Enter MailID", Toast.LENGTH_SHORT).show();
                    vUserName.setError("Enter Username");
                }else if(password.isEmpty()) {
                    Toast.makeText(Register.this, "Enter Password", Toast.LENGTH_SHORT).show();
                    vPassword.setError("Password Required");
                }else if(password.length()<7){
                    Toast.makeText(Register.this, "Password length must be more then 7 character", Toast.LENGTH_SHORT).show();
                    vPassword.setError("Week Password");
                }else{
                    vProgressBar.setVisibility(View.VISIBLE);
                    firebaseAuth = FirebaseAuth.getInstance();
                    firebaseAuth.createUserWithEmailAndPassword(username,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                               
                                Toast.makeText(Register.this, "Register success", Toast.LENGTH_SHORT).show();
                                firebaseDatabase = FirebaseDatabase.getInstance();
                                reference = firebaseDatabase.getReference("user");
                                Regno regno1 = new Regno(regno);
                                reference.child(firebaseAuth.getUid()).setValue(regno1).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            vProgressBar.setVisibility(View.INVISIBLE);
                                            Log.d("DB", "Data stored");
                                            startActivity(new Intent(getApplicationContext(), Login.class));
                                            finish();
                                        }else{
                                            Toast.makeText(Register.this, "Data base problem", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                                

                            }else if(task.isCanceled()){
                                task.addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(Register.this, e.toString(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }else{
                                vProgressBar.setVisibility(View.INVISIBLE);
                                Toast.makeText(Register.this, "Failed to register", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

    }
}