package Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import Helpers.SharedPreferenceStore;

public class Login extends AppCompatActivity {
    private EditText vUsername, vPassword;
    private TextView vRegister, vForgetPass, vLoginText;
    private RelativeLayout vLogin;
    private ProgressBar vProgressBar;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser!= null){
            startActivity(new Intent(getApplicationContext(), Vtop.class));
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //hooks
        vUsername = findViewById(R.id.username);
        vPassword = findViewById(R.id.password);
        vRegister = findViewById(R.id.mvRegister);
        vForgetPass = findViewById(R.id.mvForgetPassword);
        vLogin = findViewById(R.id.loginContainer);
        vProgressBar = findViewById(R.id.ProgressbarLogin);
        vLoginText = findViewById(R.id.BtnLoginText);
        vRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Register.class));
            }
        });
        vForgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ForgetPassword.class));
            }
        });
        vLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vProgressBar.setVisibility(View.VISIBLE);
                vLoginText.setVisibility(View.INVISIBLE);
                String username = vUsername.getText().toString().trim();
                String password = vPassword.getText().toString().trim();
                if(username.isEmpty()){
                    vUsername.setError("Mail id is required");
                    vProgressBar.setVisibility(View.INVISIBLE);
                    vLoginText.setVisibility(View.VISIBLE);
                }else if(password.isEmpty()){
                    vPassword.setError("Password is required");
                }else if(password.length()<7){
                    vPassword.setError("Password should be more then 7 character long");
                }else {
                    vProgressBar.setVisibility(View.VISIBLE);
                    vLoginText.setVisibility(View.INVISIBLE);

                    firebaseAuth.signInWithEmailAndPassword(username,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                startActivity(new Intent(getApplicationContext(), Vtop.class));
                                finish();
                            }else{
                                vProgressBar.setVisibility(View.INVISIBLE);
                                vLoginText.setVisibility(View.VISIBLE);
                                Toast.makeText(Login.this, "Login failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }



            }
        });
    }
}