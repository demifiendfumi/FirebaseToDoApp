package sg.edu.rp.webservices.firebasetodoapp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private TextView tvMessageTitle, tvMessageDate, tvMessageDays, tvMessageComplete;
    private Button btnMessage;
    private EditText etMessageTitle, etMessageDate, etMessageDays;
    private CheckBox ckMessageComplete;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference toDoItemReference;
    private String check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvMessageTitle = findViewById(R.id. textViewMessageTitle);
        tvMessageDate = findViewById(R.id.textViewMessageDate);
        tvMessageDays = findViewById(R.id. textViewMessageDays);
        tvMessageComplete = findViewById(R.id. textViewMessageCompleted);
        etMessageTitle = findViewById(R.id. editTextMessageTitle);
        etMessageDate = findViewById(R.id. editTextMessageDate);
        etMessageDays = findViewById(R.id. editTextMessageDay);
        ckMessageComplete = findViewById(R.id. checkBoxMessageComplete);
        btnMessage = findViewById(R.id.buttonAdd);

        firebaseDatabase = FirebaseDatabase.getInstance();
        toDoItemReference = firebaseDatabase.getReference("toDoItem");


        toDoItemReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // TODO: Task 4 - Get the latest value from the dataSnapshot and display on the UI using the EditText message
                // This method will get fired everytime the "message" value updates in the realtime database. We're getting our data back as a DataSnapshot
                Message message = dataSnapshot.getValue(Message.class);
                if (message != null) {
                    tvMessageTitle.setText(message.getTitle());
                    tvMessageDate.setText(message.getDate());
                    tvMessageDays.setText(String.valueOf(message.getNumOfDays()));
                    tvMessageComplete.setText(message.getCompleted());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e(TAG, "Database error occurred", databaseError.toException());
            }
        });

        btnMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ckMessageComplete.isChecked()){
                 check = "true" ;
                }else{
                    check = "false";
                }
                int days = Integer.parseInt(etMessageDays.getText().toString());
                Message message = new Message(etMessageTitle.getText().toString(), etMessageDate.getText().toString(), days, check);
                toDoItemReference.setValue(message);
            }
        });
    }
}
