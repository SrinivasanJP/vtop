package Activities.Modules;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.myinfo.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import Helpers.Modules.FacultyData;

public class Faculty_info extends AppCompatActivity {
    private EditText facultyName;
    private RelativeLayout SearchBtn, facultyView;
    private TextView data[], searchText;
    private int ids[]={R.id.facultyName, R.id.facultyDesignation,R.id.facultyCabin,R.id.facultyEmail};
    private ImageView faculty_img;
    private ProgressBar progressBar;

    private DatabaseReference reference;
    private FacultyData facultyData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_info);
        facultyName = findViewById(R.id.facultyInput);
        SearchBtn = findViewById(R.id.facultyButton);
        progressBar = findViewById(R.id.facultyProgress);
        facultyView = findViewById(R.id.facultyViewContainer);
        searchText =findViewById(R.id.btntext);
        faculty_img = findViewById(R.id.faculty_img);
        facultyView.setVisibility(View.INVISIBLE);
        data = new TextView[4];
        for(int i=0; i<4;i++){
            data[i] = findViewById(ids[i]);
        }


        SearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchText.setVisibility(View.INVISIBLE);
                progressBar.setVisibility(View.VISIBLE);
                String name = facultyName.getText().toString().trim().toLowerCase();

                if(name.isEmpty()){
                    facultyName.setError("Input Required");
                    searchText.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.INVISIBLE);
                }else{
                    if(name.equals("modigari narendra")) {
                        faculty_img.setImageResource(R.drawable.modigari);
                    }else{
                        faculty_img.setImageResource(R.drawable.dean);
                    }
                    reference = FirebaseDatabase.getInstance().getReference("FacultyInfo").child(name);
                    reference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            try {
                                facultyData = snapshot.getValue(FacultyData.class);
                                data[0].setText(facultyData.getName());
                                data[1].setText(facultyData.getDesignation());
                                data[2].setText(facultyData.getCabin());
                                data[3].setText(facultyData.getEmail());
                                facultyView.setVisibility(View.VISIBLE);
                            } catch (Exception e) {
                                Log.d("error", e.toString());
                                facultyName.setError("Faculty name not available in database");
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            facultyName.setError("Name Not available");
                            searchText.setVisibility(View.VISIBLE);
                            progressBar.setVisibility(View.INVISIBLE);

                        }
                    });

                    searchText.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.INVISIBLE);




                }
            }
        });
    }
}