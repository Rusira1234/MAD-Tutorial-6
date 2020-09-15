package com.example.tutorial6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    EditText editID, editName, editAddress, editContact;
    Button buttonSave, buttonShow, buttonUpdate, buttonDelete;
    DatabaseReference dbrf;
    Student std;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editID = findViewById(R.id.editID);
        editName = findViewById(R.id.editName);
        editAddress = findViewById(R.id.editAddress);
        editContact = findViewById(R.id.editContact);

        buttonSave = findViewById(R.id.buttonSave);
        buttonShow = findViewById(R.id.buttonShow);
        buttonUpdate = findViewById(R.id.buttonUpdate);
        buttonDelete = findViewById(R.id.buttonDelete);

        std = new Student();


        buttonDelete.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbrf = FirebaseDatabase.getInstance().getReference().child("Student");
                dbrf.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild("Std1")) {
                            dbrf = FirebaseDatabase.getInstance().getReference().child("Student").child("Std1");
                            dbrf.removeValue();
                            clearControls();
                            Toast.makeText(getApplicationContext(), "Data deleted successfully", Toast.LENGTH_SHORT).show();
                        } else
                            Toast.makeText(getApplicationContext(), "No Source to delete", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        }));

        buttonUpdate.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbrf = FirebaseDatabase.getInstance().getReference().child("Student");
                dbrf.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild("Std1")) {
                            try {
                                std.setID(editID.getText().toString().trim());
                                std.setName(editName.getText().toString().trim());
                                std.setAddress(editAddress.getText().toString().trim());
                                std.setContactNo(Integer.parseInt(editContact.getText().toString().trim()));

                                dbrf = FirebaseDatabase.getInstance().getReference().child("Student").child("Std1");
                                dbrf.setValue(std);
                                clearControls();

                                Toast.makeText(getApplicationContext(), "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                            } catch (NumberFormatException e) {
                                Toast.makeText(getApplicationContext(), "Invalid contact number", Toast.LENGTH_SHORT).show();
                            }
                        } else
                            Toast.makeText(getApplicationContext(), "No source to update", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        }));

        buttonShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbrf = FirebaseDatabase.getInstance().getReference().child("Student").child("Std1");
                dbrf.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChildren()) {
                            editID.setText(dataSnapshot.child("id").getValue().toString());
                            editName.setText(dataSnapshot.child("name").getValue().toString());
                            editAddress.setText(dataSnapshot.child("address").getValue().toString());
                            editContact.setText(dataSnapshot.child("ConNo").getValue().toString());
                        } else
                            Toast.makeText(getApplicationContext(), "No source to display", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbrf = FirebaseDatabase.getInstance().getReference().child("Student");
                try {
                    if (TextUtils.isEmpty(editID.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter an ID", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(editName.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter a name", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(editAddress.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter an address", Toast.LENGTH_SHORT).show();
                    else {
                        std.setID(editID.getText().toString().trim());
                        std.setName(editName.getText().toString().trim());
                        std.setAddress(editAddress.getText().toString().trim());
                        std.setContactNo(Integer.parseInt(editContact.getText().toString().trim()));

                        dbrf.push().setValue(std);
                        Toast.makeText(getApplicationContext(), "Data saved successfully", Toast.LENGTH_SHORT).show();
                        clearControls();
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Invalid Contact number", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void clearControls() {
        editID.setText("");
        editName.setText("");
        editAddress.setText("");
        editContact.setText("");
    }
}


















