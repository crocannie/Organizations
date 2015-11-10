package com.example.akarpenko.organisations;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class OrganizationDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_org_detail);



        Intent intent = getIntent();

        Org org=(Org) intent.getSerializableExtra("ORG");

       // String name = intent.getStringExtra("NAME");
        String name=org.getName();

       // String phone = intent.getStringExtra("PHONE");
        String phone=org.getPhone();
     //   String address = intent.getStringExtra("ADDRESS");
        String address=org.getAddress();
      //  String mail = intent.getStringExtra("EMAIL");
        String mail=org.getMail();
        TextView nameText = (TextView) findViewById(R.id.name_textView);
        nameText.setText(name);

        TextView phoneText = (TextView) findViewById(R.id.phone_textView);
        phoneText.setText(phone);

        TextView addressText = (TextView) findViewById(R.id.address_textView);
        addressText.setText(address);

        TextView emailText = (TextView) findViewById(R.id.email_textView);
        emailText.setText(mail);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_org_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
