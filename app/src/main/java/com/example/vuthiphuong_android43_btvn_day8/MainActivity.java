package com.example.vuthiphuong_android43_btvn_day8;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;
import com.example.vuthiphuong_android43_btvn_day8.databinding.ActivityMainBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IInputData{
    ActivityMainBinding binding;
    InputDataPresenter inputDataPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        inputDataPresenter = new InputDataPresenter(this);

        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = binding.etTitle.getText().toString();
                String description = binding.etDescription.getText().toString();
                String time = binding.tvTime.getText().toString();
                String date = binding.tvDate.getText().toString();
                String type = binding.spType.toString();
                String tags = binding.tvTags.getText().toString();
                String weeks = binding.tvWeeks.getText().toString();

                AlertDialog alertDialog=new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Thông báo")
                        .setMessage("Bạn có chắc chắn save không")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                inputDataPresenter.onInput(title, description, time, date, type, tags, weeks);
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .create();
                alertDialog.show();

            }
        });
        Spinner spinner =(Spinner) findViewById(R.id.spType);
        List<String> li = new ArrayList<String>();
        li.add("Work");
        li.add("Friend");
        li.add("Family");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, li);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        binding.tvTune.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(FromTune.newInstance());
            }
        });

        binding.tvTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH : mm");
                        calendar.set(0,0,0,hourOfDay,minute);
                        binding.tvTime.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                }, 14, 20,true);
                timePickerDialog.show();
            }
        });

        binding.tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                DatePickerDialog datePickerDialog=new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(year,month,dayOfMonth);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        binding.tvDate.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                }, 2016, 02, 04);
                datePickerDialog.show();
            }
        });

        binding.tvTags.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] strings = {"Family", "Game", "Android", "VTC", "Friend"};
                boolean[] booleans = {false, false, false, false, false};

                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Choose tags: ")
                        .setMultiChoiceItems(strings, booleans, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                Toast.makeText(MainActivity.this, strings[which], Toast.LENGTH_LONG).show();
                            }
                        })
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String st= null;
                                for(int i=0; i< strings.length; i++)  {
                                    if(booleans[i]) {
                                        if(st == null)  {
                                            st = strings[i];
                                        } else {
                                            st += ", " + strings[i];
                                        }
                                    }
                                }
                                binding.tvTags.setText(st + "");
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .create();
                alertDialog.show();
            }
        });

        binding.tvWeeks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] strings = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
                boolean[] booleans = {false, false, false, false, false, false, false};

                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Choose weeks: ")
                        .setMultiChoiceItems(strings, booleans, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                Toast.makeText(MainActivity.this, strings[which], Toast.LENGTH_LONG).show();
                            }
                        })
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String st= null;
                                for(int i=0; i< strings.length; i++)  {
                                    if(booleans[i]) {
                                        if(st == null)  {
                                            st = strings[i];
                                        } else {
                                            st+= ", " + strings[i];
                                        }
                                    }
                                }
                                binding.tvWeeks.setText(st + "");
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .create();
                alertDialog.show();
            }
        });
    }

    private void getFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentMain, fragment).commit();
    }
    @Override
    public void onInputDataSucsetful(String mess) {
        Toast.makeText(getBaseContext(), mess, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onInputDataError(String mess) {
        Toast.makeText(getBaseContext(), mess, Toast.LENGTH_LONG).show();
    }
}