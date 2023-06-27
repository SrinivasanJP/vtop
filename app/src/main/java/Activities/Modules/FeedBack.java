package Activities.Modules;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myinfo.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import Activities.Vtop;
import Helpers.Modules.FeedbackHelper;

public class FeedBack extends AppCompatActivity {
    private Spinner spinner;
    private FeedbackHelper feedbackHelper;
    private EditText cc, feed;
    private Button submit;
    private DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);
        spinner = findViewById(R.id.spinnerSem);
        feedbackHelper = new FeedbackHelper();
        ArrayAdapter<CharSequence> marrayAdapter = ArrayAdapter.createFromResource(this, R.array.spinnerSem, R.layout.spinner_list);
        marrayAdapter.setDropDownViewResource(R.layout.spinner_list);
        spinner.setAdapter(marrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                feedbackHelper.setSemester(adapterView.getItemAtPosition(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        cc = findViewById(R.id.courseCode);
        feed = findViewById(R.id.feedInput);
        submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cc.getText().toString()==null){
                    cc.setError("Course Code required");
                }
                else if(feed.getText().toString() == null){
                    feed.setError("Enter feedback");
                }else{
                    reference = FirebaseDatabase.getInstance().getReference("Feedback");
                    feedbackHelper.setCourseCode(cc.getText().toString());
                    feedbackHelper.setFeedback(feed.getText().toString());
                    reference.setValue(feedbackHelper).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(FeedBack.this, "Thanks for your feedback", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), Vtop.class));
                                finish();
                            }else{
                                Toast.makeText(FeedBack.this, "Feedback is not saved", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });


    }
}