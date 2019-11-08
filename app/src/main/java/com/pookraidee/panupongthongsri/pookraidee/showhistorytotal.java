package com.pookraidee.panupongthongsri.pookraidee;


import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class showhistorytotal extends Fragment {
    View v;
    private RecyclerView recyclerView;
    private List<Shistory> listhistory;
    private String Url_Loadhistory = "http://203.154.83.137/puklaidee/loadhistory.php";
    private String datau;



    public showhistorytotal() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_showhistorytotal, container, false);
        recyclerView = v.findViewById(R.id.recycler);
        historyAdapter HistoryAdapter = new historyAdapter(getContext(),listhistory);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));

        recyclerView.setAdapter(HistoryAdapter);

        return v;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listhistory = new ArrayList<>();
        loadhistory();

    }



    public void loadhistory(){
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,Url_Loadhistory, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject posts = array.getJSONObject(i);
                        String st = posts.getString("sttree");
                        String et  = posts.getString("entree");
                        String nt = posts.getString("natree");
                        String wt = posts.getString("wwtree");
                        String rt = posts.getString("revtree");

                        listhistory.add(new Shistory(
                                st, et, nt,wt+" kg.",rt+" Bath")
                        );
                        historyAdapter HistoryAdapter = new historyAdapter(getContext(),listhistory);
                        recyclerView.setAdapter(HistoryAdapter);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }

                }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                SharedPreferences prefs = getActivity().getSharedPreferences(Login.MyPREFERENCES, Activity.MODE_PRIVATE);
                String showidpre = prefs.getString("My_user","NoId");
                params.put("user",showidpre);
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

}
