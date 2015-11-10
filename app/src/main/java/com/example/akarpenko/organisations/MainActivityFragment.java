package com.example.akarpenko.organisations;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    List<Org> forecastData = new ArrayList<>();
    private ListView listview_company;
    private ArrayAdapter<Org> adapter;
    OrganizationList orglist=new OrganizationList();

    public static Db mDb;

    DatabaseHelper sqlHelper;




    public void onCreate() throws SQLException {
        mDb=new Db(getContext());
        mDb.open();
    }

    public void onTerminate()
    {
        mDb.close();
    }


    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        try {
            onCreate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        forecastData=Db.mOrganizationDao.getAllOrganizations();


        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        listview_company = (ListView) rootView.findViewById(R.id.listview_company);

      //  for (int i=0;i<orglist.list_organizations.size();i++){
       //     forecastData.add(orglist.list_organizations.get(i));
       // }
        showInTheList(forecastData);

        listview_company.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Intent intent = new Intent(getActivity(), OrganizationDetailActivity.class);
                Org org = adapter.getItem(position);
/*                intent.putExtra("NAME", org.name);
                intent.putExtra("PHONE", org.phone);
                intent.putExtra("ADDRESS", org.address);
                intent.putExtra("EMAIL", org.mail);*/
                intent.putExtra("ORG", org);
                getActivity().startActivity(intent);
            }
        });

     //   sqlHelper = new DatabaseHelper(getApplicationContext());
        Button addButton = (Button) rootView.findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent= new Intent(getActivity(),OrgAddActivity.class);
                getActivity().startActivity(intent);
            }

        });


        return rootView;



    }

    private void showInTheList(List<Org> forecastData) {
        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, forecastData);
        listview_company.setAdapter(adapter);
    }

    public void addItem(Org org){

    }

}
