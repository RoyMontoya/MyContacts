package com.example.amado.mycontacts;

import android.annotation.TargetApi;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;


public class ContactEditActivity extends ActionBarActivity {

    public static final String EXTRA = "CEA_EXTRA";
    private Contact mContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_edit);

        int position = getIntent().getIntExtra(EXTRA, 0);
        mContact = ContactList.getIntance().get(position);
        EditText editName = (EditText)findViewById(R.id.edit_name);
        editName.setText(mContact.getName());

        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.contact_edit_toolbar);
        toolbar.setTitle(getResources().getString(R.string.edit_contact));

        toolbar.setNavigationIcon(R.drawable.ic_save);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editName = (EditText) findViewById(R.id.edit_name);

                mContact.setName(editName.getText().toString());
                mContact.phoneNumbers=GetSectionValues(R.id.phonenumber_section);
                mContact.emails =GetSectionValues(R.id.emails_section);
                Toast.makeText(ContactEditActivity.this, "saved contact",Toast.LENGTH_LONG)
                .show();
                finish();
            }
        });

        AddSection(R.id.emails_section, mContact.emails);
        AddSection(R.id.phonenumber_section, mContact.phoneNumbers);

        TextView addemail =  (TextView)findViewById(R.id.add_email);
        addemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            AddSection(R.id.emails_section, "");
            }
        });

        TextView addPhone =  (TextView)findViewById(R.id.add_phone);
        addPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddSection(R.id.phonenumber_section, "");
            }
        });


    }

    private void AddSection(int sectionID, String value){
        LinearLayout section = (LinearLayout)findViewById(sectionID);
        EditText editText = new EditText(this);
        LinearLayout.LayoutParams linearLayout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        editText.setLayoutParams(linearLayout);
        editText.setText(value);
        section.addView(editText);
    }

    private void AddSection(int sectionID, ArrayList<String>values){
        LinearLayout section = (LinearLayout)findViewById(sectionID);
        for (int i = 0; i <values.size() ; i++) {
            EditText editText = new EditText(this);
            LinearLayout.LayoutParams linearLayout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            editText.setLayoutParams(linearLayout);
            editText.setText(values.get(i));
            section.addView(editText);
        }
        }

    private ArrayList<String> GetSectionValues(int sectionID){
        ArrayList<String> values = new ArrayList<String>();
        LinearLayout section = (LinearLayout)findViewById(sectionID);
        for (int i = 0; i <section.getChildCount() ; i++) {
            EditText editNumber=(EditText)section.getChildAt(i);
            values.add(editNumber.getText().toString());
        }
        return values;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contact_edit, menu);
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
