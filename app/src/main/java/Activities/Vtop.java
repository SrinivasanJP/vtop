package Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.myinfo.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

import Activities.Modules.ClassAttendance;
import Activities.Modules.Class_message;
import Activities.Modules.CoursePage;
import Activities.Modules.Faculty_info;
import Activities.Modules.FeedBack;
import Activities.Modules.Hod_dean_info;
import Activities.Modules.MyCurriculum;
import Activities.Modules.TImeTable;
import Helpers.RecyclerHelper;
import Helpers.RecyclerViewInterface;
import Helpers.Recycler_View_Adapter;
import Helpers.Regno;


public class Vtop extends AppCompatActivity implements RecyclerViewInterface {
    private TextView vRegno;
    private RecyclerView vRecyclerview;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference reference;
    private ArrayList<RecyclerHelper> modules = new ArrayList<>();
    private int[] modulesImg = {R.drawable.dean, R.drawable.female, R.drawable.conversation, R.drawable.knowledge, R.drawable.timetable, R.drawable.attendance, R.drawable.laptop, R.drawable.feedback, R.drawable.logout};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vtop);
        setUpModules();
        vRegno = findViewById(R.id.regno);
        vRecyclerview = findViewById(R.id.funRecycle);
        firebaseAuth = FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference("user").child(firebaseAuth.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Regno regno = snapshot.getValue(Regno.class);
                vRegno.setText(regno.getRegno());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        Recycler_View_Adapter adapter = new Recycler_View_Adapter(this, modules,this);
        vRecyclerview.setAdapter(adapter);
        vRecyclerview.setLayoutManager(new LinearLayoutManager(this));

    }
    private void setUpModules(){
        String[] moduleNames = getResources().getStringArray(R.array.modules);
        for (int i=0; i<moduleNames.length; i++){
            modules.add(new RecyclerHelper(moduleNames[i],modulesImg[i]));
        }
    }

    @Override
    public void onModuleClick(int position) {
        switch (position){
            case 0:
                startActivity(new Intent(getApplicationContext(), Hod_dean_info.class));
                break;
            case 1:
                startActivity((new Intent(getApplicationContext(), Faculty_info.class)));
                break;
            case 2:
                startActivity(new Intent(getApplicationContext(), Class_message.class));
                break;
            case 3:
                startActivity(new Intent(getApplicationContext(), MyCurriculum.class));
                break;
            case 4:
                startActivity(new Intent(getApplicationContext(), TImeTable.class));
                break;
            case 5:
                startActivity(new Intent(getApplicationContext(), ClassAttendance.class));
                break;
            case 6:
                startActivity(new Intent(getApplicationContext(), CoursePage.class));
                break;
            case 7:
                startActivity(new Intent(getApplicationContext(), FeedBack.class));
                break;
            case 8:
                firebaseAuth.signOut();
                startActivity(new Intent(getApplicationContext(), Login.class));
                break;
            default:
                Log.e("err", "Error onModuleClick Listener");

        }

    }
}