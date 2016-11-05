package com.soumya.todo;

import android.content.Context;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.Date;

public class FirstActivity extends AppCompatActivity {


    EditText taskEditText;
    Button taskButton;
    ListView taskList;
    ArrayList<Tasks> tasks;
    BaseAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        initiallizeAll();


    }

    private void initiallizeAll() {
        taskEditText = (EditText) findViewById(R.id.taskedittext);
        taskButton=(Button) findViewById(R.id.taskbutton);
        taskList=(ListView) findViewById(R.id.tasklist);
        tasks=new ArrayList<Tasks>();
        final LayoutInflater inflater= (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        adapter=new BaseAdapter() {
            @Override
            public int getCount() {
                return tasks.size();
            }

            @Override
            public Object getItem(int position) {
                return tasks.get(position);
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View view, ViewGroup viewgroup) {
                if(view==null){
                    view =inflater.inflate(R.layout.task_list_view,null);
                }
                TextView taskText=(TextView) view.findViewById(R.id.taskText);
                TextView taskDate=(TextView) view.findViewById(R.id.taskDate);
                taskText.setText(tasks.get(position).getTask());
                Date date=tasks.get(position).getDate();
                taskDate.setText(DateFormat.format("dd/MM/yyyy HH:mm:ss a ",date));
                return view;
            }
        };
        taskList.setAdapter(adapter);

        taskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string=taskEditText.getText().toString();
                Date date=new Date();
                Tasks task=new Tasks(string,date);
                tasks.add(task);
                adapter.notifyDataSetChanged();
                taskEditText.setText("");
                InputMethodManager inputMethodManager= (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(taskEditText.getWindowToken(),0);
                Toast.makeText(getBaseContext(),"Task Added",Toast.LENGTH_LONG).show();
            }
        });

        taskList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                tasks.remove(position);
                adapter.notifyDataSetChanged();
                Toast.makeText(getBaseContext(),"Task Completed",Toast.LENGTH_LONG).show();
                return false;
            }
        });

    }


}