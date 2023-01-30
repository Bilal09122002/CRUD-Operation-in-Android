package com.bildriod.CRUD;

import android.database.Cursor;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Handler h=new Handler();
    EditText et1,et2;
    Button b1,b2,b3,b4;
    database g;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1=findViewById(R.id.et1);
        et2=findViewById(R.id.et2);
        b1=findViewById(R.id.b1);
        b2=findViewById(R.id.b2);
        b3=findViewById(R.id.b3);
        b4=findViewById(R.id.b4);
        g=new database(this);
            //    SQLiteDatabase db=g.getReadableDatabase();
        
        
        
        b1.setOnClickListener(new View.OnClickListener() {          //INSERT CODE
            @Override
            public void onClick(View view) {
                String name=et1.getText().toString();
                String password=et2.getText().toString();
                if (name.equals("") || password.equals(""))
                {
                    Toast.makeText(MainActivity.this,"Enter the all Fields",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Boolean i=g.insert_data(name,password);
                    if(i==true)
                    {
                        Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "Data Not Inserted", Toast.LENGTH_SHORT).show();
                    }
                }
                et1.setText("");
                et2.setText("");
            }
        });


       
       
        b2.setOnClickListener(new View.OnClickListener() {          //update code
            @Override
            public void onClick(View view) {
                String name =et1.getText().toString();
                String password =et2.getText().toString();
                Boolean i=g.update_data(name,password);
                if(i==true)
                {
                    Toast.makeText(MainActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                }else
                {
                    Toast.makeText(MainActivity.this, "Not Updated", Toast.LENGTH_SHORT).show();
                }
            }
        });
        
        b3.setOnClickListener(new View.OnClickListener() {      //DELETE CODE
            @Override
            public void onClick(View view) {
                String name=et1.getText().toString();
                String password=et2.getText().toString();
                Boolean i=g.delete_data(name,password);
                if(i==true)
                {
                    Toast.makeText(MainActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
                }else
                {
                    Toast.makeText(MainActivity.this, "Not Deleted", Toast.LENGTH_SHORT).show();
                }
            }
        });




        b4.setOnClickListener(new View.OnClickListener(){       //VIEW CODE
            @Override
            public void onClick(View view) {
                Cursor t = g.getinfo();
                if (t.getCount() == 0)
                {
                    Toast.makeText(MainActivity.this, "No Data Found", Toast.LENGTH_SHORT).show();
                }
                StringBuffer buffer = new StringBuffer();
                while (t.moveToNext())
                {
                    buffer.append("users :" + t.getString(0) + "\n");
                    buffer.append("Name :"+t.getString(1)+"\n");
                    buffer.append("Password :" + t.getString(2) + "\n\n\n");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("SignUp Users Data");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
    }
}