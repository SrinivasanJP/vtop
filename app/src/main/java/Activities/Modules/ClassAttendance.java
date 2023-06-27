package Activities.Modules;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myinfo.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import Helpers.Modules.ClassAttendenceHelper;

public class ClassAttendance extends AppCompatActivity {
    private TextView sub[],percent[];
    private String substrings[] = {"Ruby Programming","Software Construction", "SoftSkills", "Operating System","Android Programming", "Computer Networks"};
    private int idssub[] = {R.id.a1,R.id.a2,R.id.a3,R.id.a4,R.id.a5,R.id.a6};
    private int idsper[] = {R.id.a1p,R.id.a2p,R.id.a3p,R.id.a4p,R.id.a5p,R.id.a6p};
    private Spinner spinner;
    private LinearLayout table;
    private DatabaseReference reference;
    private Button search;
    private ClassAttendenceHelper classAttendenceHelper;
    private String sem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_attendance);
        table = findViewById(R.id.table);
        table.setVisibility(View.INVISIBLE);
        sub = new TextView[6];
        percent = new TextView[6];
        for (int i=0;i<6;i++){
            sub[i] = findViewById(idssub[i]);
            percent[i] = findViewById(idsper[i]);
        }
        spinner = findViewById(R.id.spinnerSem);
        ArrayAdapter<CharSequence> marrayAdapter = ArrayAdapter.createFromResource(this, R.array.spinnerSem, R.layout.spinner_list);
        marrayAdapter.setDropDownViewResource(R.layout.spinner_list);
        spinner.setAdapter(marrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i == 0){
                    sem = "fall_inter";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        search = findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reference = FirebaseDatabase.getInstance().getReference("ClassAttendance").child(sem);
                Log.d("referacePath:",reference.getPath().toString());
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        classAttendenceHelper = snapshot.getValue(ClassAttendenceHelper.class);
                        percent[0].setText(classAttendenceHelper.getRuby_Programming()+"");
                        percent[1].setText(classAttendenceHelper.getSoftware_Construction()+"");
                        percent[2].setText(classAttendenceHelper.getSoft_Skills()+"");
                        percent[3].setText(classAttendenceHelper.getOperating_System()+"");
                        percent[4].setText(classAttendenceHelper.getAndroid_Programming()+"");
                        percent[5].setText(classAttendenceHelper.getComputer_Networks()+"");
                        for(int i=0;i<6;i++){
                            sub[i].setText(substrings[i]);
                        }
                        table.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(ClassAttendance.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });


    }
}