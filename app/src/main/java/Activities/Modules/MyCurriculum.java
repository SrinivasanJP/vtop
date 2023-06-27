package Activities.Modules;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.myinfo.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import Helpers.Modules.MyCurriculumHelper;

public class MyCurriculum extends AppCompatActivity {

    private TextView views[];
    private MyCurriculumHelper data;
    private DatabaseReference reference;
    private int ids[] = {R.id.pc,R.id.pe,R.id.uc,R.id.ue,R.id.bc,R.id.ncc,R.id.tc};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_curriculum);
        views = new TextView[7];
        for(int i=0;i<7;i++){
            views[i] = findViewById(ids[i]);
        }
        reference = FirebaseDatabase.getInstance().getReference("MyCurriculum");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data = snapshot.getValue(MyCurriculumHelper.class);
                views[0].setText(data.getPc());
                views[1].setText(data.getPe());
                views[2].setText(data.getUc());
                views[3].setText(data.getUe());
                views[4].setText(data.getBc());
                views[5].setText(data.getNcc());
                views[6].setText(data.getTc());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}