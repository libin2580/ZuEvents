package com.meridian.zulekhaevents.com.meridian.zulekhaevents.fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.meridian.zulekhaevents.R;
import com.meridian.zulekhaevents.com.meridian.zulekhaevents.model.EventsModel;
import com.tapadoo.alerter.Alerter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static com.meridian.zulekhaevents.ZHconstants.ZH_URL;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SupportRevelationEventSuppFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SupportRevelationEventSuppFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SupportRevelationEventSuppFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

   static View v;
    ProgressDialog progressdialog;

   static EditText eLandLine, eCompany, eLocation, eEmail, eFullName, eMobile,eDate;
    boolean edittexterror = false;
    Spinner spinnerusrtyp;
    ArrayList<EventsModel> eventlist = new ArrayList<EventsModel>();
    String sLandLine, sCompany, sLocation, sEmail, sFullName, sMobile, sUserType,sDate;
    ProgressDialog pd;
    //int spinflag,imr;
    ArrayList<String> spinpoparray=new ArrayList<String>();
    Button bsubmit;
 static int yy, mm, dd, mHour, mMinute;
  static   Calendar calendar;
    public SupportRevelationEventSuppFragment() {
        // Required empty public constructor
    }
String Test,s;
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SupportRevelationEventSuppFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SupportRevelationEventSuppFragment newInstance(String param1, String param2) {
        SupportRevelationEventSuppFragment fragment = new SupportRevelationEventSuppFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }



        View view = getActivity().getWindow().getDecorView();
        view.setBackgroundColor(Color.WHITE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       v= inflater.inflate(R.layout.fragment_support_revelation_event_supp, container, false);




        ScrollView sView = (ScrollView) v.findViewById(R.id.scroll);
        sView.setVerticalScrollBarEnabled(false);


        SharedPreferences shpp = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        String sh_landline=shpp.getString("landline", "landline");

        String sh_company=shpp.getString("company", "company");

        String sh_location=shpp.getString("location","location");

        String sh_email=shpp.getString("email","email");

        String sh_name=shpp.getString("fullname", "name");

        String sh_mobile=shpp.getString("mobile", "mobile");



        eLandLine = (EditText) v.findViewById(R.id.editText7);
        eLandLine.setText(sh_landline);


        eCompany = (EditText) v.findViewById(R.id.editText9);
        eCompany.setText(sh_company);
        eLocation = (EditText) v.findViewById(R.id.editText55);
        eLocation.setText(sh_location);

        eEmail = (EditText) v.findViewById(R.id.editText31);
        eEmail.setText(sh_email);

        eFullName = (EditText) v.findViewById(R.id.editText3);
        eFullName.setText(sh_name);
        eMobile = (EditText) v.findViewById(R.id.editText6);
        eMobile.setText(sh_mobile);




        eDate=(EditText) v.findViewById(R.id.editTextdate);

        //  pd = new ProgressDialog(SupportSynapseEventSuppActivity.this);

        spinnerusrtyp = (Spinner) v.findViewById(R.id.spinner2);

        bsubmit=(Button)v.findViewById(R.id.button8) ;


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.spin_supprev, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerusrtyp.setAdapter(adapter);



        eDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                DialogFragment newFragment = new SelectDateFragment();
                newFragment.show(getFragmentManager(), "DatePicker");


            }
        });




        bsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                edittexterror = false;

                if (!android.util.Patterns.EMAIL_ADDRESS.matcher(eEmail.getText().toString().trim()).matches()) {
                    eEmail.setError("Invalid Email");
                    edittexterror = true;
                }


                if (eEmail.getText().toString().isEmpty()) {
                    eEmail.setError("Enter Email Id");
                    edittexterror = true;
                }

                if (eDate.getText().toString().isEmpty()) {
                    eDate.setError("Enter Revelation Date");
                    edittexterror = true;
                }



                if (eFullName.getText().toString().isEmpty()) {
                    eFullName.setError("Enter Full Name");
                    edittexterror = true;
                }

                if (eMobile.getText().toString().isEmpty()) {
                    eMobile.setError("Enter Mobile Number");
                    edittexterror = true;
                }

                if (eLandLine.getText().toString().isEmpty()) {
                    eLandLine.setError("Enter LandLine Number");
                    edittexterror = true;
                }
                if (eCompany.getText().toString().isEmpty()) {
                    eCompany.setError("Enter Company Name");
                    edittexterror = true;
                }
                if (eLocation.getText().toString().isEmpty()) {
                    eLocation.setError("Enter Location");
                    edittexterror = true;
                }


                if (!edittexterror) {
                    //Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_SHORT).show();

                    // sLandLine,sCompany,sLocation,sEmail,sPassword,sRepeatPass,sFullName,sMobile,sWrkPlc,sTitle,sEmirate,sSpeciality;

                    sLandLine = eLandLine.getText().toString().replaceAll(" ", "%20");
                    sCompany = eCompany.getText().toString().replaceAll(" ", "%20");

                    sLocation = eLocation.getText().toString().replaceAll(" ", "%20");

                    sEmail = eEmail.getText().toString().replaceAll(" ", "%20");


                    sFullName = eFullName.getText().toString().replaceAll(" ", "%20");

                    sMobile = eMobile.getText().toString().replaceAll(" ", "%20");

                  sDate=eDate.getText().toString().replaceAll(" ", "%20");

                    sUserType=spinnerusrtyp.getSelectedItem().toString().replaceAll(" ", "%20");


                    RequestQueue queue1 = Volley.newRequestQueue(getActivity());
                    String url1 = ZH_URL+"json-cme.php?fid=118&branch="+sUserType+"&titleofprg=test&reg_fee=100&revelation_date="+sDate+"&full_name="+sFullName+"&company="+sCompany+"&mobile="+sMobile+"&email="+sEmail+"&location="+sLocation+"&landline="+sLandLine+"&pay_plan=1&pay_option=cash&accept=1";
// Request a string response from the provided URL.
                    StringRequest stringRequest1 = new StringRequest
                            (Request.Method.GET, url1, new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    // Display the first 500 characters of the response string.
                                    //  tv.setText("Response is: "+ response);
                                    progressdialog.dismiss();
                                    System.out.println("++++++++++++++RESPONSE+++++++++++++++    :" + response);


                                    JSONObject jsonObject = null;

                                    try {
                                        JSONArray jsonarray = new JSONArray(response);

                                        for (int i = 0; i < jsonarray.length(); i++) {


                                            JSONObject jsonobject = jsonarray.getJSONObject(i);
                                            String result = jsonobject.getString("result");
                                            if (result.contentEquals("true")) {
                                               /* Toast.makeText(getActivity(), "SUCCESS", Toast.LENGTH_SHORT).show();*/


                                                Alerter.create(getActivity())
                                                        .setText("Synapse Support Registration Successfull")
                                                        //.setIcon(R.drawable.alerter_ic_notifications)
                                                        .setBackgroundColor(R.color.successcolor)
                                                        .show();

                                                        //Do something after 100ms
                                                        try {
                                                            Fragment newFragment = new ProfileFragment_event_supp();
                                                            FragmentTransaction transaction = getFragmentManager().beginTransaction();

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack
                                                            transaction.replace(R.id.mainfragevsupp, newFragment);
                                                            // transaction.addToBackStack(null);

// Commit the transaction
                                                            transaction.commit();
                                                        }catch (Exception e){
                                                            e.printStackTrace();
                                                        }


                                               /* MaterialStyledDialog dialog = new MaterialStyledDialog(getActivity())
                                                        .setTitle("SUCCESS!")
                                                        .setDescription("Synapse Support Registration Successfull ")
                                                        .setIcon(R.drawable.ic_done_white_24dp)
                                                        .withIconAnimation(true)
                                                        .withDialogAnimation(true)
                                                        .setHeaderColor(R.color.colorPrimary)
                                                        .setCancelable(true)
                                                        .setPositive(getResources().getString(R.string.ok), new MaterialDialog.SingleButtonCallback() {
                                                            @Override
                                                            public void onClick(MaterialDialog dialog, DialogAction which) {
                                                                //  Toast.makeText(getActivity(), "OK", Toast.LENGTH_SHORT).show();


                                                                Fragment newFragment = new ProfileFragment_event_supp();
                                                                FragmentTransaction transaction = getFragmentManager().beginTransaction();

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack
                                                                transaction.replace(R.id.mainfragevsupp, newFragment);
                                                                // transaction.addToBackStack(null);

// Commit the transaction
                                                                transaction.commit();

                                                            }
                                                        })
                                                        .build();

                                                dialog.show();*/
                                            }
                                            else if (result.equalsIgnoreCase("Alreday Registred")) {
                                                Alerter.create(getActivity())
                                                        .setText("Alreday Registred")
                                                        //.setIcon(R.drawable.alerter_ic_notifications)
                                                        .setBackgroundColor(R.color.errorcolor)
                                                        .show();
                                                try {
                                                    Fragment newFragment = new ProfileFragment_event_supp();
                                                    FragmentTransaction transaction = getFragmentManager().beginTransaction();

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack
                                                    transaction.replace(R.id.mainfragevsupp, newFragment);
                                                    // transaction.addToBackStack(null);

// Commit the transaction
                                                    transaction.commit();
                                                }catch (Exception e){
                                                    e.printStackTrace();
                                                }
                                            }else {
                                               /* Toast.makeText(getActivity(), "ERROR", Toast.LENGTH_SHORT).show();*/


                                                Alerter.create(getActivity())
                                                        .setText("Synapse Support Registration failed")
                                                        //.setIcon(R.drawable.alerter_ic_notifications)
                                                        .setBackgroundColor(R.color.errorcolor)
                                                        .show();

                                                        //Do something after 100ms
try {
    Fragment newFragment = new ProfileFragment_event_supp();
    FragmentTransaction transaction = getFragmentManager().beginTransaction();

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack
    transaction.replace(R.id.mainfragevsupp, newFragment);
    // transaction.addToBackStack(null);

// Commit the transaction
    transaction.commit();
}catch (Exception e){
    e.printStackTrace();
}

                                                /*MaterialStyledDialog dialog = new MaterialStyledDialog(getActivity())
                                                        .setTitle("ERROR!")
                                                        .setDescription("Synapse Support Registration Failed")
                                                        .setIcon(R.drawable.ic_error_white_24dp)
                                                        .withIconAnimation(true)
                                                        .withDialogAnimation(true)
                                                        .setHeaderColor(R.color.red)
                                                        .setCancelable(true)
                                                        .setPositive(getResources().getString(R.string.ok), new MaterialDialog.SingleButtonCallback() {
                                                            @Override
                                                            public void onClick(MaterialDialog dialog, DialogAction which) {
                                                                //  Toast.makeText(getActivity(), "OK", Toast.LENGTH_SHORT).show();


                                                                Fragment newFragment = new ProfileFragment_event_supp();
                                                                FragmentTransaction transaction = getFragmentManager().beginTransaction();

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack
                                                                transaction.replace(R.id.mainfragevsupp, newFragment);
                                                                // transaction.addToBackStack(null);

// Commit the transaction
                                                                transaction.commit();

                                                            }
                                                        })
                                                        .build();

                                                dialog.show();*/

                                            }


                                        }


                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    //tv.setText("That didn't work!");
                                    progressdialog.dismiss();
                                }
                            });
// Add the request to the RequestQueue.
                    queue1.add(stringRequest1);

                    progressdialog = new ProgressDialog(getActivity());
                    progressdialog.setMessage("Loading");
                    progressdialog.show();




                }


            }
        });









        return  v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }



    public static class SelectDateFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
        private DatePickerDialog datepic;
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            calendar = Calendar.getInstance();
         yy = calendar.get(Calendar.YEAR);
          mm = calendar.get(Calendar.MONTH);
           dd = calendar.get(Calendar.DAY_OF_MONTH);
          /*  DatePickerDialog datePickerDialog=new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int monthofYear, int dayofMonth) {
                    if(year<yy ||monthofYear<mm||dayofMonth<dd){
                        Alerter.create(getActivity())
                                .setText("can't select past date")
                                //.setIcon(R.drawable.alerter_ic_notifications)
                                .setBackgroundColor(R.color.errorcolor)
                                .show();
                     *//*   Snackbar.with(Zulekha_Appointment_UnRegFragment.this,null)
                                .type(Type.ERROR)
                                .message("can't select past date")
                                .duration(Duration.LONG)

                                .show();
*//*
                    }else {

                       // Appoint_Dat.setText(dayofMonth + "/" + (monthofYear + 1) + "/" + year);
                    }
                }
            },yy,mm,dd);
            datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
            datePickerDialog.show();*/
       datepic=new DatePickerDialog (getActivity(), this, yy, mm, dd);
            datepic.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
            return  datepic;
        }

        public void onDateSet(DatePicker view, int yy, int mm, int dd) {
          view.setMinDate(System.currentTimeMillis() - 1000);

            populateSetDate(yy, mm+1, dd);
        }
        public void populateSetDate(int year, int month, int day) {
           // eDate.setText(month+"/"+day+"/"+year);
           // Toast.makeText(getActivity(), month+"/"+day+"/"+year, Toast.LENGTH_SHORT).show();

//

            SimpleDateFormat newDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String s=day+"/"+month+"/"+year;
            Date dt1=null;
            try {
                dt1=newDateFormat.parse(s);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            DateFormat format2=new SimpleDateFormat("EEEE");
            String finalDay=format2.format(dt1);

            if(finalDay.equals("Thursday"))
            {
                eDate.setText(day+"/"+month+"/"+year);
            }
            else{
                //Toast.makeText(getActivity(),"Revelations are conducted on every Thursday,Please select a different date", Toast.LENGTH_SHORT).show();
eDate.setText("");
              /*  Snackbar.make(v, "Revelations are conducted on Thursdays only,Please select a different date", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/

                Alerter.create(getActivity())
                        .setText("Revelations are conducted on Thursdays only,Please select a different date")
                        //.setIcon(R.drawable.alerter_ic_notifications)
                        .setBackgroundColor(R.color.errorcolor)
                        .show();
            }


            //Toast.makeText(getActivity(), finalDay, Toast.LENGTH_SHORT).show();


        }

    }
}
