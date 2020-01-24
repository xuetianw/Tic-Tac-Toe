package io.futurestud.retrofit1.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.futurestud.retrofit1.R;
import io.futurestud.retrofit1.api.model.Course;
import io.futurestud.retrofit1.api.proxy.ProxyBuilder;
import io.futurestud.retrofit1.api.proxy.WGServerProxy;
import retrofit2.Call;

public class CourseActivity extends AppCompatActivity {
    private WGServerProxy proxy;
    EditText courseET;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        proxy = ProxyBuilder.getProxy();
        courseET = (EditText) findViewById(R.id.add_courseETId);

        setup_add_btn();

        populateListView();
    }

    private void setup_add_btn() {
        Button button = (Button) findViewById(R.id.save_buttonId);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Course the_course = new Course(courseET.getText().toString());
                Call<Course> call = proxy.add_course(the_course);

                ProxyBuilder.callProxy(getApplicationContext(), call, returnedKey -> response(returnedKey));
                populateListView();
            }
        });

    }

    private void populateListView() {

        Call<List<Course>> call = proxy.get_courses();

        ProxyBuilder.callProxy(getApplicationContext(), call, returnedKey -> response(returnedKey));
    }

    private void response(Course response) {
        if((response == null)) {
            Toast.makeText(getApplicationContext(), "Server replied",
                    Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "course added",
                    Toast.LENGTH_LONG).show();
        }
    }

    private void response(List<Course> response) {
        if((response == null)) {
            Toast.makeText(getApplicationContext(), "Server replied",
                    Toast.LENGTH_LONG).show();
        } else {
            List<String> courses_str_list = new ArrayList<>();

            for (Course course : response) {
                String temp = String.format("course id : %s \ncourse title : %s", course.getId(), course.getTitle());
                courses_str_list.add(temp);
            }

            // build adapter
            ArrayAdapter<String> adapter = new ArrayAdapter<>(
                    this, R.layout.course_items, courses_str_list);

            // configure the list view
            ListView list = (ListView) findViewById(R.id.course_listId);
            list.setAdapter(adapter);
        }
    }
}