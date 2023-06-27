package Activities.Modules;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.myinfo.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import Helpers.Modules.DeanAndHod;

public class Hod_dean_info extends AppCompatActivity {
    private DatabaseReference reference;
    private DeanAndHod deanAndHod;
    private TextView vData[];
    private ProgressBar progressBar;
    private RelativeLayout dean,hod;
    private int textIds[] = {R.id.deanName, R.id.deanDesignation, R.id.deanCabin, R.id.deanEmail,
    R.id.hodName, R.id.hodDesignation, R.id.hodCabin, R.id.hodEmail};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hod_dean_info);
        dean = findViewById(R.id.deanContainer);
        hod = findViewById(R.id.hodContainer);
        dean.setVisibility(View.INVISIBLE);
        hod.setVisibility(View.INVISIBLE);
        progressBar = findViewById(R.id.deanhodProgress);
        progressBar.setVisibility(View.VISIBLE);
        vData = new TextView[8];
        for(int i=0;i<8;i++){
            vData[i] = findViewById(textIds[i]);
        }

        reference = FirebaseDatabase.getInstance().getReference("DeanAndHod");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                deanAndHod = snapshot.getValue(DeanAndHod.class);
                vData[0].setText(deanAndHod.getDeanName());
                vData[1].setText(deanAndHod.getDeanDesignation());
                vData[2].setText(deanAndHod.getDeanCabin());
                vData[3].setText(deanAndHod.getDeanEmail());
                vData[4].setText(deanAndHod.getHodName());
                vData[5].setText(deanAndHod.getHodDesignation());
                vData[6].setText(deanAndHod.getHodCabin());
                vData[7].setText(deanAndHod.getHodEmail());
                dean.setVisibility(View.VISIBLE);
                hod.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("err", error.toString() );

            }
        });

    }
}