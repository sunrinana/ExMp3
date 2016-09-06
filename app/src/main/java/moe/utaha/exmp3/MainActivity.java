package moe.utaha.exmp3;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayAdapter<String> m_Adapter;
    private ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        m_Adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1);
        listView = (ListView) findViewById(R.id.list_item);
        listView.setAdapter(m_Adapter);
        Uri uri = Uri.parse("android.resource://com.cpt.sample/raw/");
        Log.e("asdf --------------",uri.toString());
        for (String i : list )
        {
            m_Adapter.add(i);
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }
    /*
    public void getMp3list(){
        Uri uri = Uri.parse("android.resource://com.cpt.sample/raw/");
        list = new ArrayList<String>();
        try
        {
            File file = new File("/sdcard/A");
            File[] files = file.listFiles();
            for(int i = 0;i < files.length;i++)
            {
                list.add(files[i].getName());
            }
        } catch( Exception e )
        {
            Toast.makeText(getApplicationContext(),"file error",Toast.LENGTH_SHORT).show();
        }
    }*/
}
