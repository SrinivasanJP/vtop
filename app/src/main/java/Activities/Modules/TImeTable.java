package Activities.Modules;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.myinfo.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import Helpers.Modules.TimeTableHelper;


public class TImeTable extends AppCompatActivity {
    private Spinner spinner;
    private TimeTableHelper timeTableHelper;
    private TextView a1,b1,c1,d1,e1,f1,a2,b2,c2,d2,e2,f2,a3,b3,c3,d3,e3,f3;
    private DatabaseReference reference;
    private TableLayout table;
    private Button search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table);
        spinner = findViewById(R.id.spinnerSem);
        ArrayAdapter<CharSequence> marrayAdapter = ArrayAdapter.createFromResource(this, R.array.spinnerSem, R.layout.spinner_list);
        marrayAdapter.setDropDownViewResource(R.layout.spinner_list);
        spinner.setAdapter(marrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("debug", adapterView.getItemAtPosition(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        table = findViewById(R.id.ttTable);
        table.setVisibility(View.INVISIBLE);
        search = findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a1 = findViewById(R.id.a1);
                a2 = findViewById(R.id.a2);
                a3 = findViewById(R.id.a3);
                b1 = findViewById(R.id.b1);
                b2 = findViewById(R.id.b2);
                b3 = findViewById(R.id.b3);
                c1 = findViewById(R.id.c1);
                c2 = findViewById(R.id.c2);
                c3 = findViewById(R.id.c3);
                d1 = findViewById(R.id.d1);
                d2 = findViewById(R.id.d2);
                d3 = findViewById(R.id.d3);
                e1 = findViewById(R.id.e1);
                e2 = findViewById(R.id.e2);
                e3 = findViewById(R.id.e3);
                f1 = findViewById(R.id.f1);
                f2 = findViewById(R.id.f2);
                f3 = findViewById(R.id.f3);

                reference = FirebaseDatabase.getInstance().getReference("TimeTable");
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        timeTableHelper = snapshot.getValue(TimeTableHelper.class);
                        table.setVisibility(View.VISIBLE);
                        a1.setText(timeTableHelper.getA1());
                        a2.setText(timeTableHelper.getA1());
                        a3.setText(timeTableHelper.getA1());
                        b1.setText(timeTableHelper.getB1());
                        b2.setText(timeTableHelper.getB1());
                        b3.setText(timeTableHelper.getB1());
                        c1.setText(timeTableHelper.getC1());
                        c2.setText(timeTableHelper.getC1());
                        c3.setText(timeTableHelper.getC1());
                        d1.setText(timeTableHelper.getD1());
                        d2.setText(timeTableHelper.getD1());
                        d3.setText(timeTableHelper.getD1());
                        e1.setText(timeTableHelper.getE1());
                        e2.setText(timeTableHelper.getE1());
                        e3.setText(timeTableHelper.getE1());
                        f1.setText(timeTableHelper.getF1());
                        f2.setText(timeTableHelper.getF1());
                        f3.setText(timeTableHelper.getF1());
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        table.setVisibility(View.VISIBLE);
                    }
                });

            }
        });





    }
}