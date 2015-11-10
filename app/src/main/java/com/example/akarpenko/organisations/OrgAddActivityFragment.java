package com.example.akarpenko.organisations;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import java.sql.SQLException;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class OrgAddActivityFragment extends Fragment {

    private EditText nameText;
    private EditText phoneText;
    private EditText addressText;
    private EditText mailText;
    OrganizationList orglist=new OrganizationList();

    public static Db mDb;

      public void onCreate() throws SQLException {
        mDb=new Db(getContext());
        mDb.open();
    }

    public OrgAddActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        final View rootView = inflater.inflate(R.layout.fragment_org_add, container, false);

        nameText = (EditText) rootView.findViewById(R.id.name_editText);
        phoneText = (EditText) rootView.findViewById(R.id.phone_editText);
        addressText = (EditText) rootView.findViewById(R.id.address_editText);
        mailText = (EditText) rootView.findViewById(R.id.mail_editText);

        Button addOrg = (Button) rootView.findViewById(R.id.addOrg);
//        Button checkMap = (Button) rootView.findViewById(R.id.map_button);
//        Button callbutton = (Button) rootView.findViewById(R.id.call_button);
//        Button adrbutton = (Button) rootView.findViewById(R.id.address_button);
//        Button mailbutton = (Button) rootView.findViewById(R.id.mail_button);
        ImageButton mailbutton = (ImageButton) rootView.findViewById(R.id.iMailButton);
        ImageButton callbutton = (ImageButton) rootView.findViewById(R.id.iCallButton);
        ImageButton adrbutton = (ImageButton) rootView.findViewById(R.id.iAddressButton);
        ImageButton checkMap = (ImageButton) rootView.findViewById(R.id.iMapButton);



        Intent intent = getActivity().getIntent();
        String name = intent.getStringExtra("NAME");
        String phone = intent.getStringExtra("PHONE");
        String address = intent.getStringExtra("ADDRESS");
        String mail = intent.getStringExtra("MAIL");

        checkMap.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                createMapIntent();
            }

            public void createMapIntent() {
                String name = nameText.getText().toString().trim();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + name));
                PackageManager packageManager = getActivity().getPackageManager();
                List<ResolveInfo> activities = packageManager.queryIntentActivities(intent, 0);
                if (activities.size() > 0) {
                    startActivity(intent);
                }
            }
        });

        callbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                createCallIntent();
            }

            public void createCallIntent() {
                String tel = phoneText.getText().toString().trim();

                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + tel));
                PackageManager packageManager = getActivity().getPackageManager();
                List<ResolveInfo> activities = packageManager.queryIntentActivities(intent, 0);
                if (activities.size() > 0) {
                    startActivity(intent);
                }
            }
        });


        mailbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                createMailIntent();
            }

            public void createMailIntent() {
                String email = mailText.getText().toString().replace(" ", "");
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("message/rfc822");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[] { email });
                PackageManager packageManager = getActivity().getPackageManager();
                List<ResolveInfo> activities = packageManager.queryIntentActivities(intent, 0);
                if (activities.size() > 0) {
                    startActivity(intent);
                }
            }
        });

        adrbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String url = addressText.getText().toString();
                if (!url.startsWith("http://") && !url.startsWith("https://")) {
                    url = "http://" + url;
                }
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                PackageManager packageManager = getActivity().getPackageManager();
                List<ResolveInfo> activities = packageManager.queryIntentActivities(intent, 0);
                if (activities.size() > 0) {
                    startActivity(intent);
                }
            }
        });


        addOrg.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    onCreate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                String name = nameText.getText().toString().trim();
                String mail = mailText.getText().toString().replace(" ", "");
                String phone = phoneText.getText().toString().trim();
                String address = addressText.getText().toString().trim();

                Org new_org = new Org();
                new_org.name = name;
                new_org.phone = phone;
                new_org.mail = mail;
                new_org.address = address;

                boolean isSave=Db.mOrganizationDao.addOrganization(new_org);
                Log.e("save",Boolean.toString(isSave));
                Intent intent = new Intent(v.getContext(), MainActivity.class);

                startActivity(intent);
            }

        });

        return rootView;





    }
}
