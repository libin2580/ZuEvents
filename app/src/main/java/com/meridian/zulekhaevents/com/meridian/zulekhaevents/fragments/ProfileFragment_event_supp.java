package com.meridian.zulekhaevents.com.meridian.zulekhaevents.fragments;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.meridian.zulekhaevents.EventSupporterHomeActivity;
import com.meridian.zulekhaevents.EventsMainActivity_Event_supp;
import com.meridian.zulekhaevents.R;
import com.tapadoo.alerter.Alerter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.meridian.zulekhaevents.ZHconstants.ZH_URL;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProfileFragment_event_supp.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProfileFragment_event_supp#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment_event_supp extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    SliderLayout sliderShow;

    Button b4;
    TextView t2;
    ImageButton ib,imgexit;
    SharedPreferences sharedpreferences;

    ImageView profpic;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ProfileFragment_event_supp() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment_event_supp newInstance(String param1, String param2) {
        ProfileFragment_event_supp fragment = new ProfileFragment_event_supp();
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
            try {
                Bundle bundle = this.getArguments();
                if (bundle != null) {
                    String nw_name = bundle.getString("nw_username");
                    //String nw_title = bundle.getString("nw_title");
                  /*  if(nw_title!=null){
                        t2.setText("Welcome "+nw_title+" "+nw_name);
                    }else{*/
                        t2.setText("Welcome "+nw_name);
                    //}
                }
            }catch (Exception e){e.printStackTrace();}



        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//        String name = getArguments().getString("name");
//        String spec = getArguments().getString("spec");
        // Inflate the layout for this fragment


        View v=inflater.inflate(R.layout.fragment_profile, container, false);

        //profpic=(ImageView)v.findViewById(R.id.imageView5);
        FragmentManager fragmentManager=getActivity().getSupportFragmentManager();
        System.out.println("fragment by id : "+fragmentManager.findFragmentById(R.id.mainfragevsupp));
        String current_fragment=fragmentManager.findFragmentById(R.id.mainfragevsupp).toString().substring(0,fragmentManager.findFragmentById(R.id.mainfragevsupp).toString().indexOf("{"));
        System.out.println("current_fragment : "+current_fragment);

        if(current_fragment.trim().equalsIgnoreCase("ProfileFragment_event_supp")) {
            EventSupporterHomeActivity.imgexit.setVisibility(View.GONE);
            EventSupporterHomeActivity.ib.setVisibility(View.VISIBLE);
            SharedPreferences prefs = getActivity().getSharedPreferences("MyPrefs", getActivity().MODE_PRIVATE);
            prefs.getString("userid", null);

            // t2.setText(prefs.getString("fullname","Name"));
            if(prefs.getString("title",null)!=null){
                EventSupporterHomeActivity.t2.setText("Welcome "+prefs.getString("title",null)+" "+prefs.getString("fullname",null));
            }else{
                EventSupporterHomeActivity.t2.setText("Welcome "+prefs.getString("fullname",null));
            }
        }
        else {
            EventSupporterHomeActivity.imgexit.setVisibility(View.VISIBLE);
            EventSupporterHomeActivity.ib.setVisibility(View.GONE);


        }
        sliderShow = (SliderLayout)v. findViewById(R.id.slider);
       // b3=(Button)v.findViewById(R.id.button3);
        b4=(Button)v.findViewById(R.id.button4);

     //   b5=(Button)v.findViewById(R.id.button5);

    //    b6=(Button)v.findViewById(R.id.button6);
    //    b7=(Button)v.findViewById(R.id.button7);


        t2=(TextView) v.findViewById(R.id.textView2);
       // t3=(TextView) v.findViewById(R.id.textView3);
       // t5=(TextView) v.findViewById(R.id.textView5);


        SharedPreferences prefs = getActivity().getSharedPreferences("MyPrefs", getActivity().MODE_PRIVATE);
        prefs.getString("userid", null);

       // t2.setText(prefs.getString("fullname","Name"));
        if(prefs.getString("title",null)!=null){
        t2.setText("Welcome "+prefs.getString("title",null)+" "+prefs.getString("fullname",null));
        }else{
            t2.setText("Welcome "+prefs.getString("fullname",null));
        }
      //  t5.setText(prefs.getString("company","Complany"));
//
//        t2.setText(name);
//        t5.setText(spec);

        ib=(ImageButton)v.findViewById(R.id.imageButton);

        imgexit=(ImageButton)v.findViewById(R.id.imageButton2);


        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


               EventSupporterHomeActivity.drawer.openDrawer(GravityCompat.END);
            }
        });

        imgexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(getActivity());
                builder.setTitle("Exit");
                builder.setIcon(R.mipmap.ic_launcher);
                builder.setMessage("Are You Sure You Want To Exit The App?");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Show location settings when the user acknowledges the alert dialog
                      getActivity().finish();
                    }
                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Show location settings when the user acknowledges the alert dialog

                    }
                });
                Dialog alertDialog = builder.create();
                alertDialog.setCanceledOnTouchOutside(true);
                alertDialog.show();


            }
        });




     //   Picasso.with(getActivity()).load("http://zulekhahospitals.com/uploads/doctor/Dr.Saurabh.jpg").transform(new CircleTransform()).into(profpic);


//        SharedPreferences sharedimg = getActivity().getSharedPreferences("imagepref", getActivity().MODE_PRIVATE);
//        String imagepath= sharedimg.getString("imagefilepath",null);



//        if(imagepath!=null) {
//            Utility.Default_DIR = new File(imagepath);
//
//
//            Utility.Create_MY_IMAGES_DIR();
//
//
//            Utility.copyFile(Utility.Default_DIR, Utility.MY_IMG_DIR);
//
//
//            Bitmap bmp = Utility.decodeFile(Utility.Paste_Target_Location);
//
//
//            String valid_photo = Utility.Paste_Target_Location.toString();
//
//            bmp = Bitmap.createScaledBitmap(bmp, 150, 150, true);
//
//
//            Picasso.with(getActivity()).load(Utility.Paste_Target_Location).transform(new CircleTransform()).into(profpic);
//        }




//        else
//        {
//
//
//
//            Picasso.with(getActivity()).load(R.drawable.placeholder_man).transform(new CircleTransform()).into(profpic);
//
//        }


//        profpic.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Image_Picker_Dialog();
//            }
//
//            private void Image_Picker_Dialog() {
//
//                AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(getActivity());
//                myAlertDialog.setTitle("Pictures Option");
//                myAlertDialog.setMessage("Select Picture Mode");
//
//                myAlertDialog.setPositiveButton("Gallery", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface arg0, int arg1) {
//                        Utility.pictureActionIntent = new Intent(Intent.ACTION_GET_CONTENT, null);
//                        Utility.pictureActionIntent.setType("image/*");
//                        Utility.pictureActionIntent.putExtra("return-data", true);
//                        startActivityForResult(Utility.pictureActionIntent, Utility.GALLERY_PICTURE);
//                    }
//                });
//
//                myAlertDialog.setNegativeButton("Camera", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface arg0, int arg1) {
//                        Utility.pictureActionIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//                        startActivityForResult(Utility.pictureActionIntent, Utility.CAMERA_PICTURE);
//                    }
//                });
//                myAlertDialog.show();
//            }
//        });





        Typeface faceralway = Typeface.createFromAsset(getActivity().getAssets(),"HelveticaNeueLTStd-Roman.otf");
       // b3.setTypeface(faceralway);
        b4.setTypeface(faceralway);
       // b5.setTypeface(faceralway);
       // b6.setTypeface(faceralway);
       // b7.setTypeface(faceralway);


        Typeface facemyriad = Typeface.createFromAsset(getActivity().getAssets(),"MyriadPro-Semibold.otf");
        t2.setTypeface(facemyriad);
     //   t3.setTypeface(facemyriad);
      //  t5 .setTypeface(facemyriad);


        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in=new Intent(getActivity(),EventsMainActivity_Event_supp.class);
                startActivity(in);
            }
        });





        RequestQueue queue1 = Volley.newRequestQueue(getContext());
        // "http://meridianinc.biz.cp-30.webhostbox.net/etsdc/response.php?fid=11&contact_person=new%20subin&company=companyname&city=calicut&country=india&phone=9989898989&email=subin@cybraum.net&course=OPITO&subcourse=subcourse1,subcourse2"
        String url1 =ZH_URL+"json-cme.php?fid=125";

        StringRequest stringRequest1 = new StringRequest
                (Request.Method.GET, url1, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        //  tv.setText("Response is: "+ response);

                        System.out.println("++++++++++++++RESPONSE+++++++++++++++banner    :" + response);



                        try {
                            JSONArray jsonarray = new JSONArray(response);

                            for (int i = 0; i < jsonarray.length(); i++)
                            {
                                DefaultSliderView defaultSliderView = new DefaultSliderView(getActivity());
                                JSONObject jsonobject = jsonarray.getJSONObject(i);
                                String banner_id = jsonobject.getString("id");
                                String banner = jsonobject.getString("cme_banner");

                                defaultSliderView.image("http://www.zulekhahospitals.com/uploads/cme_banner/"+banner);



                                sliderShow.addSlider(defaultSliderView);

                                System.out.println("@@@@@@@@@@@   :http://www.zulekhahospitals.com/uploads/cme_banner/"+banner);

                            }






                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //tv.setText("That didn't work!");
                        Alerter.create(getActivity())
                                .setText("Something went wrong.Please try again")
                                //.setIcon(R.drawable.alerter_ic_notifications)
                                .setBackgroundColor(R.color.errorcolor)
                                .show();
                    }
                });

        queue1.add(stringRequest1);

        sliderShow.setPresetTransformer(SliderLayout.Transformer.Accordion);
        sliderShow.setIndicatorVisibility(PagerIndicator.IndicatorVisibility.Visible);


        return v;
    }


//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        Toast.makeText(getActivity(), "inside on activity result", Toast.LENGTH_LONG).show();
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == Utility.GALLERY_PICTURE) {
//            // data contains result
//            // Do some task
//            Image_Selecting_Task(data);
//        } else if (requestCode == Utility.CAMERA_PICTURE) {
//            // Do some task
//            Image_Selecting_Task(data);
//        }
//    }




//    public void Image_Selecting_Task(Intent data) {
//        try {
//            Utility.uri = data.getData();
//            if (Utility.uri != null) {
//                // User had pick an image.
//                Cursor cursor = getActivity().getContentResolver().query(Utility.uri, new String[]
//                        {android.provider.MediaStore.Images.ImageColumns.DATA}, null, null, null);
//                cursor.moveToFirst();
//                // Link to the image
//                final String imageFilePath = cursor.getString(0);
//
//                //Assign string path to File
//                Utility.Default_DIR = new File(imageFilePath);
//
//                // Create new dir MY_IMAGES_DIR if not created and copy image into that dir and store that image path in valid_photo
//                Utility.Create_MY_IMAGES_DIR();
//
//                // Copy your image
//                Utility.copyFile(Utility.Default_DIR, Utility.MY_IMG_DIR);
//
//                // Get new image path and decode it
//                Bitmap bmp = Utility.decodeFile(Utility.Paste_Target_Location);
//
//                // use new copied path and use anywhere
//                String valid_photo = Utility.Paste_Target_Location.toString();
//
//                bmp = Bitmap.createScaledBitmap(bmp, 150, 150, true);
//
//                //set your selected image in image view
//                //profpic.setImageBitmap(b);
//                Toast.makeText(getActivity(), "Loading Photo", Toast.LENGTH_LONG).show();
//
//
//                Picasso.with(getActivity()).load(Utility.Paste_Target_Location).transform(new CircleTransform()).into(profpic);
//
//
//                sharedpreferences = getActivity().getSharedPreferences("imagepref", getActivity().MODE_PRIVATE);
//                SharedPreferences.Editor editor = sharedpreferences.edit();
//
//                editor.putString("imagefilepath",imageFilePath);
//
//
//
//                editor.commit();
//
//
//
//                cursor.close();
//
//            } else {
//                Toast toast = Toast.makeText(getActivity(), "Sorry!!! You haven't selecet any image.", Toast.LENGTH_LONG);
//                toast.show();
//            }
//        } catch (Exception e) {
//            // you get this when you will not select any single image
//            Log.e("onActivityResult", "" + e);
//
//        }}


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


    @Override
    public void onStop() {

        sliderShow.stopAutoCycle();
        super.onStop();
    }
}
