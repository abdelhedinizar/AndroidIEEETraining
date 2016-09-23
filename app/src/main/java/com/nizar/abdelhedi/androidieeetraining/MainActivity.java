package com.nizar.abdelhedi.androidieeetraining;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.nizar.abdelhedi.androidieeetraining.storage.SQLite;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    List<TODO> arrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.cancelButton);
        final ListView listView = (ListView) findViewById(R.id.listView);
        Button buttonOk = (Button) findViewById(R.id.okButton);


        final SQLite sqlite = new SQLite(getString(R.string.databasename),this);
        sqlite.createTable(getString(R.string.databasetablename));
        arrayList = sqlite.retrivenDate(getString(R.string.databasetablename));


        ArrayAdapter<TODO> iEEEAdapter = new IEEEAdapter(this,R.layout.list_item,arrayList);
        listView.setAdapter(iEEEAdapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
            }
        });
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText nameEditText = (EditText) findViewById(R.id.nameEditText);
                EditText todoEditText = (EditText) findViewById(R.id.memoEditText);

                TODO todo = new TODO(nameEditText.getText().toString(),todoEditText.getText().toString(),"");
                sqlite.insertData(todo,getString(R.string.databasetablename));

                arrayList = sqlite.retrivenDate(getString(R.string.databasetablename));
                ArrayAdapter<TODO> iEEEAdapter = new IEEEAdapter(MainActivity.this,R.layout.list_item,arrayList);
                listView.setAdapter(iEEEAdapter);
                nameEditText.setText("");
                todoEditText.setText("");

            }
        });


    }

    class IEEEAdapter extends ArrayAdapter<TODO>
    {

        List<TODO> objects;

        public IEEEAdapter(Context context, int resource, List<TODO> objects) {
            super(context, resource, objects);
            this.objects=objects;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            final View view = layoutInflater.inflate(R.layout.list_item, parent, false);
            TextView nametextView = (TextView) view.findViewById(R.id.nameTextView);
            TextView tODOtextView = (TextView) view.findViewById(R.id.TODOTextView);

            nametextView.setText(objects.get(position).name.toString());
            tODOtextView.setText(objects.get(position).memo.toString());
            return view;
        }
    }

}
