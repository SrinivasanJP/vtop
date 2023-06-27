package Activities.Modules;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
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

import Helpers.Modules.CoursePageHelper;

public class CoursePage extends AppCompatActivity {
    private TextView c1,c2;
    private Button c1l,c2l,search;
    private Spinner spinner;
    private CoursePageHelper coursePageHelper;
    private DatabaseReference reference;
    private String sem;
    private LinearLayout ccLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_page);
        c1 = findViewById(R.id.c1);
        c2 = findViewById(R.id.c2);
        c1l = findViewById(R.id.c1l);
        c2l = findViewById(R.id.c2l);
        spinner = findViewById(R.id.spinnerSem);
        search = findViewById(R.id.search);
        ccLayout = findViewById(R.id.ccContainer);
        ccLayout.setVisibility(View.INVISIBLE);
        ArrayAdapter<CharSequence> marrayAdapter = ArrayAdapter.createFromResource(this, R.array.spinnerSem, R.layout.spinner_list);
        marrayAdapter.setDropDownViewResource(R.layout.spinner_list);
        spinner.setAdapter(marrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0){
                    sem = "fallinter";
                }else{
                    sem = "fallinter";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                sem = "fallinter";
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sem==null){
                    Log.d("err", "err");
                    Toast.makeText(CoursePage.this, "Select semester", Toast.LENGTH_SHORT).show();
                }else {
                    Log.d("sem", sem);
                    reference = FirebaseDatabase.getInstance().getReference("CoursePage").child(sem);

                    reference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            coursePageHelper = snapshot.getValue(CoursePageHelper.class);
                            c1.setText("Android Programming");
                            c2.setText("Ruby Programming");
                            c1l.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(coursePageHelper.getC1())));
                                }
                            });
                            c2l.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(coursePageHelper.getC2())));
                                }
                            });
                            ccLayout.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }



            }
        });



    }
}