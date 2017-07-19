package com.meridian.zulekhaevents;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.meridian.zulekhaevents.com.meridian.zulekhaevents.fragments.ParticipatedEventsFragment;
import com.meridian.zulekhaevents.com.meridian.zulekhaevents.fragments.PatientReferralFragment;
import com.meridian.zulekhaevents.com.meridian.zulekhaevents.fragments.SupportSynapseSynapseUserFragment;
import com.meridian.zulekhaevents.firebase.MessagesFragment;
import com.meridian.zulekhaevents.firebase.MessagesFragmentForSynapse;

import es.dmoral.toasty.Toasty;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,ProfileFragment.OnFragmentInteractionListener,ParticipatedEventsFragment.OnFragmentInteractionListener,PatientReferralFragment.OnFragmentInteractionListener,SupportSynapseSynapseUserFragment.OnFragmentInteractionListener,ProfileUpdateSynapseUserFragment.OnFragmentInteractionListener,SynapseRegistrationWebFragment.OnFragmentInteractionListener,AboutUsFragment.OnFragmentInteractionListener,MessagesFragment.OnFragmentInteractionListener,MessagesFragmentForSynapse.OnFragmentInteractionListener,AboutUsFragmentForSynapse.OnFragmentInteractionListener{


    String name,spec="";
    ListView list;
    TextView tv;
    public static ImageButton ib,imgexit;
    //CustomAdapter adapter;
    public  HomeActivity CustomListView = null;
   // public ArrayList<ListModel> CustomListViewValuesArr = new ArrayList<ListModel>();
   private static final int TIME_DELAY = 2000;
    private static long back_pressed;
    static DrawerLayout drawer;
    String pushnot="test";
    FragmentManager fragmentManager;
    public ImageView navbackimageView;
    NavigationView navigationView;
    LinearLayout menu_home,menu_messages,menu_synapseregistration,menu_participatedevents,menu_patientreferral,menu_updateprofile,menu_settings,menu_aboutus,menu_signout;
    ImageView menu_home_icon,menu_messages_icon,menu_synapseregistration_icon,menu_participatedevents_icon,menu_patientreferral_icon,menu_updateprofile_icon,menu_settings_icon,menu_aboutus_icon,menu_signout_icon;
    TextView menu_home_text,menu_messages_text,menu_synapseregistration_text,menu_participatedevents_text,menu_patientreferral_text,menu_updateprofile_text,menu_settings_text,menu_aboutus_text,menu_signout_text;
    ImageView fb,twitter,linkedin;
    public static TextView t2;
String anvin="hiiiiiiiiiiiii anvin";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_home);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().hide();

        //drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ib=(ImageButton)findViewById(R.id.imageButton);

        imgexit=(ImageButton)findViewById(R.id.imageButton2);

        t2=(TextView) findViewById(R.id.textView2);
        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


               drawer.openDrawer(GravityCompat.END);
            }
        });

        SharedPreferences prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        prefs.getString("userid", null);

        if(prefs.getString("title",null)!=null){
            t2.setText("Welcome "+prefs.getString("title",null)+" "+prefs.getString("fullname",null));
        }else{
            t2.setText("Welcome "+prefs.getString("fullname",null));
        }

        navigationView = (NavigationView) findViewById(R.id.navigation_drawer_container);
        navigationView.setNavigationItemSelectedListener(this);

        fb=(ImageView)findViewById(R.id.facebook);
        twitter=(ImageView)findViewById(R.id.twitter);
        linkedin=(ImageView)findViewById(R.id.linkedin);



        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    //getActivity().getPackageManager().getPackageInfo("com.facebook.katana", 0);
                    String page_url="";
                    int versionCode =  getPackageManager().getPackageInfo("com.facebook.katana", 0).versionCode;
                    if (versionCode >= 3002850) { //newer versions of fb app
                        page_url= "fb://facewebmodal/f?href=https://www.facebook.com/zulekhahospitals";
                    } else { //older versions of fb app
                        page_url= "fb://page/"+"zulekhahospitals";
                    }

                    String url = page_url;
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);

                } catch (Exception e) {
                    String url = "https://m.facebook.com/zulekhahospitals";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                }
                drawer.closeDrawer(GravityCompat.END);
            }
        });

        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://twitter.com/zulekhahosptls";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                drawer.closeDrawer(GravityCompat.END);
            }
        });

        linkedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://linkedin.com/company/zulekha-hospitals-llc";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                drawer.closeDrawer(GravityCompat.END);
            }
        });

        menu_home=(LinearLayout)findViewById(R.id.menu_home);
        menu_messages=(LinearLayout)findViewById(R.id.menu_messages);
        menu_synapseregistration=(LinearLayout)findViewById(R.id.menu_synapseregistration);
        menu_patientreferral=(LinearLayout)findViewById(R.id.menu_patientreferral);
        menu_participatedevents=(LinearLayout)findViewById(R.id.menu_participatedevents);
        menu_updateprofile=(LinearLayout)findViewById(R.id.menu_updateprofile);
        menu_settings=(LinearLayout)findViewById(R.id.menu_settings);
        menu_aboutus=(LinearLayout)findViewById(R.id.menu_aboutus);
        menu_signout=(LinearLayout)findViewById(R.id.menu_signout);

        menu_home_icon=(ImageView) findViewById(R.id.menu_home_icon);
        menu_messages_icon=(ImageView)findViewById(R.id.menu_messages_icon);
        menu_synapseregistration_icon=(ImageView)findViewById(R.id.menu_synapseregistration_icon);
        menu_patientreferral_icon=(ImageView)findViewById(R.id.menu_patientreferral_icon);
        menu_participatedevents_icon=(ImageView)findViewById(R.id.menu_participatedevents_icon);
        menu_updateprofile_icon=(ImageView)findViewById(R.id.menu_updateprofile_icon);
        menu_settings_icon=(ImageView)findViewById(R.id.menu_settings_icon);
        menu_aboutus_icon=(ImageView)findViewById(R.id.menu_aboutus_icon);
        menu_signout_icon=(ImageView)findViewById(R.id.menu_signout_icon);

        menu_home_text=(TextView) findViewById(R.id.menu_home_text);
        menu_messages_text=(TextView)findViewById(R.id.menu_messages_text);
        menu_synapseregistration_text=(TextView)findViewById(R.id.menu_synapseregistration_text);
        menu_patientreferral_text=(TextView)findViewById(R.id.menu_patientreferral_text);
        menu_participatedevents_text=(TextView)findViewById(R.id.menu_participatedevents_text);
        menu_updateprofile_text=(TextView)findViewById(R.id.menu_updateprofile_text);
        menu_settings_text=(TextView)findViewById(R.id.menu_settings_text);
        menu_aboutus_text=(TextView)findViewById(R.id.menu_aboutus_text);
        menu_signout_text=(TextView)findViewById(R.id.menu_signout_text);



        menu_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.closeDrawer(GravityCompat.END);

                menu_home.setBackgroundResource(R.color.colorAccent);
                menu_messages.setBackgroundResource(R.color.white);
                menu_synapseregistration.setBackgroundResource(R.color.white);
                menu_participatedevents.setBackgroundResource(R.color.white);
                menu_patientreferral.setBackgroundResource(R.color.white);
                menu_updateprofile.setBackgroundResource(R.color.white);
                menu_settings.setBackgroundResource(R.color.white);
                menu_aboutus.setBackgroundResource(R.color.white);
                menu_signout.setBackgroundResource(R.color.white);

                menu_home_icon.getBackground().setColorFilter(ContextCompat.getColor(HomeActivity.this,R.color.white), PorterDuff.Mode.SRC_ATOP);
                menu_messages_icon.getBackground().setColorFilter(ContextCompat.getColor(HomeActivity.this,R.color.gray), PorterDuff.Mode.SRC_ATOP);
                menu_synapseregistration_icon.getBackground().setColorFilter(ContextCompat.getColor(HomeActivity.this,R.color.gray), PorterDuff.Mode.SRC_ATOP);
                menu_participatedevents_icon.getBackground().setColorFilter(ContextCompat.getColor(HomeActivity.this,R.color.gray), PorterDuff.Mode.SRC_ATOP);
                menu_patientreferral_icon.getBackground().setColorFilter(ContextCompat.getColor(HomeActivity.this,R.color.gray), PorterDuff.Mode.SRC_ATOP);
                menu_updateprofile_icon.getBackground().setColorFilter(ContextCompat.getColor(HomeActivity.this,R.color.gray), PorterDuff.Mode.SRC_ATOP);
                menu_settings_icon.getBackground().setColorFilter(ContextCompat.getColor(HomeActivity.this,R.color.gray), PorterDuff.Mode.SRC_ATOP);
                menu_aboutus_icon.getBackground().setColorFilter(ContextCompat.getColor(HomeActivity.this,R.color.gray), PorterDuff.Mode.SRC_ATOP);
                menu_signout_icon.getBackground().setColorFilter(ContextCompat.getColor(HomeActivity.this,R.color.gray), PorterDuff.Mode.SRC_ATOP);


                menu_home_text.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.white));
                menu_messages_text.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.black));
                menu_synapseregistration_text.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.black));
                menu_participatedevents_text.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.black));
                menu_patientreferral_text.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.black));
                menu_updateprofile_text.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.black));
                menu_settings_text.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.black));
                menu_aboutus_text.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.black));
                menu_signout_text.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.black));

                ProfileFragment proffrag=new ProfileFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();

                fragmentManager.beginTransaction().replace(R.id.mainfrag,proffrag).commit();


            }
        });
        menu_messages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.closeDrawer(GravityCompat.END);

                menu_home.setBackgroundResource(R.color.white);
                menu_messages.setBackgroundResource(R.color.colorAccent);
                menu_synapseregistration.setBackgroundResource(R.color.white);
                menu_participatedevents.setBackgroundResource(R.color.white);
                menu_patientreferral.setBackgroundResource(R.color.white);
                menu_updateprofile.setBackgroundResource(R.color.white);
                menu_settings.setBackgroundResource(R.color.white);
                menu_aboutus.setBackgroundResource(R.color.white);
                menu_signout.setBackgroundResource(R.color.white);

                menu_home_icon.getBackground().setColorFilter(ContextCompat.getColor(HomeActivity.this,R.color.gray), PorterDuff.Mode.SRC_ATOP);
                menu_messages_icon.getBackground().setColorFilter(ContextCompat.getColor(HomeActivity.this,R.color.white), PorterDuff.Mode.SRC_ATOP);
                menu_synapseregistration_icon.getBackground().setColorFilter(ContextCompat.getColor(HomeActivity.this,R.color.gray), PorterDuff.Mode.SRC_ATOP);
                menu_participatedevents_icon.getBackground().setColorFilter(ContextCompat.getColor(HomeActivity.this,R.color.gray), PorterDuff.Mode.SRC_ATOP);
                menu_patientreferral_icon.getBackground().setColorFilter(ContextCompat.getColor(HomeActivity.this,R.color.gray), PorterDuff.Mode.SRC_ATOP);
                menu_updateprofile_icon.getBackground().setColorFilter(ContextCompat.getColor(HomeActivity.this,R.color.gray), PorterDuff.Mode.SRC_ATOP);
                menu_settings_icon.getBackground().setColorFilter(ContextCompat.getColor(HomeActivity.this,R.color.gray), PorterDuff.Mode.SRC_ATOP);
                menu_aboutus_icon.getBackground().setColorFilter(ContextCompat.getColor(HomeActivity.this,R.color.gray), PorterDuff.Mode.SRC_ATOP);
                menu_signout_icon.getBackground().setColorFilter(ContextCompat.getColor(HomeActivity.this,R.color.gray), PorterDuff.Mode.SRC_ATOP);


                menu_home_text.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.black));
                menu_messages_text.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.white));
                menu_synapseregistration_text.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.black));
                menu_participatedevents_text.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.black));
                menu_patientreferral_text.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.black));
                menu_updateprofile_text.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.black));
                menu_settings_text.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.black));
                menu_aboutus_text.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.black));
                menu_signout_text.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.black));

                MessagesFragmentForSynapse msfrag=new MessagesFragmentForSynapse();
                FragmentManager fragmentManager = getSupportFragmentManager();

                fragmentManager.beginTransaction().replace(R.id.mainfrag,msfrag).addToBackStack("test").commit();

            }
        });
        menu_synapseregistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.closeDrawer(GravityCompat.END);

                menu_home.setBackgroundResource(R.color.white);
                menu_messages.setBackgroundResource(R.color.white);
                menu_synapseregistration.setBackgroundResource(R.color.colorAccent);
                menu_participatedevents.setBackgroundResource(R.color.white);
                menu_patientreferral.setBackgroundResource(R.color.white);
                menu_updateprofile.setBackgroundResource(R.color.white);
                menu_settings.setBackgroundResource(R.color.white);
                menu_aboutus.setBackgroundResource(R.color.white);
                menu_signout.setBackgroundResource(R.color.white);

                menu_home_icon.getBackground().setColorFilter(ContextCompat.getColor(HomeActivity.this,R.color.gray), PorterDuff.Mode.SRC_ATOP);
                menu_messages_icon.getBackground().setColorFilter(ContextCompat.getColor(HomeActivity.this,R.color.gray), PorterDuff.Mode.SRC_ATOP);
                menu_synapseregistration_icon.getBackground().setColorFilter(ContextCompat.getColor(HomeActivity.this,R.color.white), PorterDuff.Mode.SRC_ATOP);
                menu_participatedevents_icon.getBackground().setColorFilter(ContextCompat.getColor(HomeActivity.this,R.color.gray), PorterDuff.Mode.SRC_ATOP);
                menu_patientreferral_icon.getBackground().setColorFilter(ContextCompat.getColor(HomeActivity.this,R.color.gray), PorterDuff.Mode.SRC_ATOP);
                menu_updateprofile_icon.getBackground().setColorFilter(ContextCompat.getColor(HomeActivity.this,R.color.gray), PorterDuff.Mode.SRC_ATOP);
                menu_settings_icon.getBackground().setColorFilter(ContextCompat.getColor(HomeActivity.this,R.color.gray), PorterDuff.Mode.SRC_ATOP);
                menu_aboutus_icon.getBackground().setColorFilter(ContextCompat.getColor(HomeActivity.this,R.color.gray), PorterDuff.Mode.SRC_ATOP);
                menu_signout_icon.getBackground().setColorFilter(ContextCompat.getColor(HomeActivity.this,R.color.gray), PorterDuff.Mode.SRC_ATOP);


                menu_home_text.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.black));
                menu_messages_text.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.black));
                menu_synapseregistration_text.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.white));
                menu_participatedevents_text.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.black));
                menu_patientreferral_text.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.black));
                menu_updateprofile_text.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.black));
                menu_settings_text.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.black));
                menu_aboutus_text.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.black));
                menu_signout_text.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.black));
                SynapseRegistrationWebFragment supfrag=new SynapseRegistrationWebFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();

                fragmentManager.beginTransaction().replace(R.id.mainfrag,supfrag).addToBackStack("test").commit();

            }
        });
        menu_participatedevents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.closeDrawer(GravityCompat.END);
                menu_home.setBackgroundResource(R.color.white);
                menu_messages.setBackgroundResource(R.color.white);
                menu_synapseregistration.setBackgroundResource(R.color.white);
                menu_participatedevents.setBackgroundResource(R.color.colorAccent);
                menu_patientreferral.setBackgroundResource(R.color.white);
                menu_updateprofile.setBackgroundResource(R.color.white);
                menu_settings.setBackgroundResource(R.color.white);
                menu_aboutus.setBackgroundResource(R.color.white);
                menu_signout.setBackgroundResource(R.color.white);

                menu_home_icon.getBackground().setColorFilter(ContextCompat.getColor(HomeActivity.this,R.color.gray), PorterDuff.Mode.SRC_ATOP);
                menu_messages_icon.getBackground().setColorFilter(ContextCompat.getColor(HomeActivity.this,R.color.gray), PorterDuff.Mode.SRC_ATOP);
                menu_synapseregistration_icon.getBackground().setColorFilter(ContextCompat.getColor(HomeActivity.this,R.color.gray), PorterDuff.Mode.SRC_ATOP);
                menu_participatedevents_icon.getBackground().setColorFilter(ContextCompat.getColor(HomeActivity.this,R.color.white), PorterDuff.Mode.SRC_ATOP);
                menu_patientreferral_icon.getBackground().setColorFilter(ContextCompat.getColor(HomeActivity.this,R.color.gray), PorterDuff.Mode.SRC_ATOP);
                menu_updateprofile_icon.getBackground().setColorFilter(ContextCompat.getColor(HomeActivity.this,R.color.gray), PorterDuff.Mode.SRC_ATOP);
                menu_settings_icon.getBackground().setColorFilter(ContextCompat.getColor(HomeActivity.this,R.color.gray), PorterDuff.Mode.SRC_ATOP);
                menu_aboutus_icon.getBackground().setColorFilter(ContextCompat.getColor(HomeActivity.this,R.color.gray), PorterDuff.Mode.SRC_ATOP);
                menu_signout_icon.getBackground().setColorFilter(ContextCompat.getColor(HomeActivity.this,R.color.gray), PorterDuff.Mode.SRC_ATOP);


                menu_home_text.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.black));
                menu_messages_text.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.black));
                menu_synapseregistration_text.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.black));
                menu_participatedevents_text.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.white));
                menu_patientreferral_text.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.black));
                menu_updateprofile_text.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.black));
                menu_settings_text.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.black));
                menu_aboutus_text.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.black));
                menu_signout_text.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.black));

                ParticipatedEventsFragment parfrag=new ParticipatedEventsFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();



                fragmentManager.beginTransaction().replace(R.id.mainfrag,parfrag).addToBackStack("test").commit();

            }
        });
        menu_patientreferral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.closeDrawer(GravityCompat.END);
                menu_home.setBackgroundResource(R.color.white);
                menu_messages.setBackgroundResource(R.color.white);
                menu_synapseregistration.setBackgroundResource(R.color.white);
                menu_participatedevents.setBackgroundResource(R.color.white);
                menu_patientreferral.setBackgroundResource(R.color.colorAccent);
                menu_updateprofile.setBackgroundResource(R.color.white);
                menu_settings.setBackgroundResource(R.color.white);
                menu_aboutus.setBackgroundResource(R.color.white);
                menu_signout.setBackgroundResource(R.color.white);

                menu_home_icon.getBackground().setColorFilter(ContextCompat.getColor(HomeActivity.this,R.color.gray), PorterDuff.Mode.SRC_ATOP);
                menu_messages_icon.getBackground().setColorFilter(ContextCompat.getColor(HomeActivity.this,R.color.gray), PorterDuff.Mode.SRC_ATOP);
                menu_synapseregistration_icon.getBackground().setColorFilter(ContextCompat.getColor(HomeActivity.this,R.color.gray), PorterDuff.Mode.SRC_ATOP);
                menu_participatedevents_icon.getBackground().setColorFilter(ContextCompat.getColor(HomeActivity.this,R.color.gray), PorterDuff.Mode.SRC_ATOP);
                menu_patientreferral_icon.getBackground().setColorFilter(ContextCompat.getColor(HomeActivity.this,R.color.white), PorterDuff.Mode.SRC_ATOP);
                menu_updateprofile_icon.getBackground().setColorFilter(ContextCompat.getColor(HomeActivity.this,R.color.gray), PorterDuff.Mode.SRC_ATOP);
                menu_settings_icon.getBackground().setColorFilter(ContextCompat.getColor(HomeActivity.this,R.color.gray), PorterDuff.Mode.SRC_ATOP);
                menu_aboutus_icon.getBackground().setColorFilter(ContextCompat.getColor(HomeActivity.this,R.color.gray), PorterDuff.Mode.SRC_ATOP);
                menu_signout_icon.getBackground().setColorFilter(ContextCompat.getColor(HomeActivity.this,R.color.gray), PorterDuff.Mode.SRC_ATOP);


                menu_home_text.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.black));
                menu_messages_text.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.black));
                menu_synapseregistration_text.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.black));
                menu_participatedevents_text.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.black));
                menu_patientreferral_text.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.white));
                menu_updateprofile_text.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.black));
                menu_settings_text.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.black));
                menu_aboutus_text.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.black));
                menu_signout_text.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.black));

                PatientReferralFragment patreferalfrag=new PatientReferralFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();

                fragmentManager.beginTransaction().replace(R.id.mainfrag,patreferalfrag).addToBackStack("test").commit();

            }
        });
        menu_updateprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.closeDrawer(GravityCompat.END);

                menu_home.setBackgroundResource(R.color.white);
                menu_messages.setBackgroundResource(R.color.white);
                menu_synapseregistration.setBackgroundResource(R.color.white);
                menu_participatedevents.setBackgroundResource(R.color.white);
                menu_patientreferral.setBackgroundResource(R.color.white);
                menu_updateprofile.setBackgroundResource(R.color.colorAccent);
                menu_settings.setBackgroundResource(R.color.white);
                menu_aboutus.setBackgroundResource(R.color.white);
                menu_signout.setBackgroundResource(R.color.white);

                menu_home_icon.getBackground().setColorFilter(ContextCompat.getColor(HomeActivity.this,R.color.gray), PorterDuff.Mode.SRC_ATOP);
                menu_messages_icon.getBackground().setColorFilter(ContextCompat.getColor(HomeActivity.this,R.color.gray), PorterDuff.Mode.SRC_ATOP);
                menu_synapseregistration_icon.getBackground().setColorFilter(ContextCompat.getColor(HomeActivity.this,R.color.gray), PorterDuff.Mode.SRC_ATOP);
                menu_participatedevents_icon.getBackground().setColorFilter(ContextCompat.getColor(HomeActivity.this,R.color.gray), PorterDuff.Mode.SRC_ATOP);
                menu_patientreferral_icon.getBackground().setColorFilter(ContextCompat.getColor(HomeActivity.this,R.color.gray), PorterDuff.Mode.SRC_ATOP);
                menu_updateprofile_icon.getBackground().setColorFilter(ContextCompat.getColor(HomeActivity.this,R.color.white), PorterDuff.Mode.SRC_ATOP);
                menu_settings_icon.getBackground().setColorFilter(ContextCompat.getColor(HomeActivity.this,R.color.gray), PorterDuff.Mode.SRC_ATOP);
                menu_aboutus_icon.getBackground().setColorFilter(ContextCompat.getColor(HomeActivity.this,R.color.gray), PorterDuff.Mode.SRC_ATOP);
                menu_signout_icon.getBackground().setColorFilter(ContextCompat.getColor(HomeActivity.this,R.color.gray), PorterDuff.Mode.SRC_ATOP);


                menu_home_text.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.black));
                menu_messages_text.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.black));
                menu_synapseregistration_text.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.black));
                menu_participatedevents_text.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.black));
                menu_patientreferral_text.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.black));
                menu_updateprofile_text.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.white));
                menu_settings_text.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.black));
                menu_aboutus_text.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.black));
                menu_signout_text.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.black));

                ProfileUpdateSynapseUserFragment proup=new ProfileUpdateSynapseUserFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();

                fragmentManager.beginTransaction().replace(R.id.mainfrag,proup).addToBackStack("test").commit();


            }
        });
        menu_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.closeDrawer(GravityCompat.END);
                Intent i=new Intent(getApplicationContext(), MyPreferencesActivity.class);
                startActivity(i);

                menu_home.setBackgroundResource(R.color.white);
                menu_messages.setBackgroundResource(R.color.white);
                menu_synapseregistration.setBackgroundResource(R.color.white);
                menu_participatedevents.setBackgroundResource(R.color.white);
                menu_patientreferral.setBackgroundResource(R.color.white);
                menu_updateprofile.setBackgroundResource(R.color.white);
                menu_settings.setBackgroundResource(R.color.colorAccent);
                menu_aboutus.setBackgroundResource(R.color.white);
                menu_signout.setBackgroundResource(R.color.white);

                menu_home_icon.getBackground().setColorFilter(ContextCompat.getColor(HomeActivity.this,R.color.gray), PorterDuff.Mode.SRC_ATOP);
                menu_messages_icon.getBackground().setColorFilter(ContextCompat.getColor(HomeActivity.this,R.color.gray), PorterDuff.Mode.SRC_ATOP);
                menu_synapseregistration_icon.getBackground().setColorFilter(ContextCompat.getColor(HomeActivity.this,R.color.gray), PorterDuff.Mode.SRC_ATOP);
                menu_participatedevents_icon.getBackground().setColorFilter(ContextCompat.getColor(HomeActivity.this,R.color.gray), PorterDuff.Mode.SRC_ATOP);
                menu_patientreferral_icon.getBackground().setColorFilter(ContextCompat.getColor(HomeActivity.this,R.color.gray), PorterDuff.Mode.SRC_ATOP);
                menu_updateprofile_icon.getBackground().setColorFilter(ContextCompat.getColor(HomeActivity.this,R.color.gray), PorterDuff.Mode.SRC_ATOP);
                menu_settings_icon.getBackground().setColorFilter(ContextCompat.getColor(HomeActivity.this,R.color.white), PorterDuff.Mode.SRC_ATOP);
                menu_aboutus_icon.getBackground().setColorFilter(ContextCompat.getColor(HomeActivity.this,R.color.gray), PorterDuff.Mode.SRC_ATOP);
                menu_signout_icon.getBackground().setColorFilter(ContextCompat.getColor(HomeActivity.this,R.color.gray), PorterDuff.Mode.SRC_ATOP);


                menu_home_text.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.black));
                menu_messages_text.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.black));
                menu_synapseregistration_text.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.black));
                menu_participatedevents_text.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.black));
                menu_patientreferral_text.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.black));
                menu_updateprofile_text.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.black));
                menu_settings_text.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.white));
                menu_aboutus_text.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.black));
                menu_signout_text.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.black));


            }
        });
        menu_aboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.closeDrawer(GravityCompat.END);


                menu_home.setBackgroundResource(R.color.white);
                menu_messages.setBackgroundResource(R.color.white);
                menu_synapseregistration.setBackgroundResource(R.color.white);
                menu_participatedevents.setBackgroundResource(R.color.white);
                menu_patientreferral.setBackgroundResource(R.color.white);
                menu_updateprofile.setBackgroundResource(R.color.white);
                menu_settings.setBackgroundResource(R.color.white);
                menu_aboutus.setBackgroundResource(R.color.colorAccent);
                menu_signout.setBackgroundResource(R.color.white);

                menu_home_icon.getBackground().setColorFilter(ContextCompat.getColor(HomeActivity.this,R.color.gray), PorterDuff.Mode.SRC_ATOP);
                menu_messages_icon.getBackground().setColorFilter(ContextCompat.getColor(HomeActivity.this,R.color.gray), PorterDuff.Mode.SRC_ATOP);
                menu_synapseregistration_icon.getBackground().setColorFilter(ContextCompat.getColor(HomeActivity.this,R.color.gray), PorterDuff.Mode.SRC_ATOP);
                menu_participatedevents_icon.getBackground().setColorFilter(ContextCompat.getColor(HomeActivity.this,R.color.gray), PorterDuff.Mode.SRC_ATOP);
                menu_patientreferral_icon.getBackground().setColorFilter(ContextCompat.getColor(HomeActivity.this,R.color.gray), PorterDuff.Mode.SRC_ATOP);
                menu_updateprofile_icon.getBackground().setColorFilter(ContextCompat.getColor(HomeActivity.this,R.color.gray), PorterDuff.Mode.SRC_ATOP);
                menu_settings_icon.getBackground().setColorFilter(ContextCompat.getColor(HomeActivity.this,R.color.gray), PorterDuff.Mode.SRC_ATOP);
                menu_aboutus_icon.getBackground().setColorFilter(ContextCompat.getColor(HomeActivity.this,R.color.white), PorterDuff.Mode.SRC_ATOP);
                menu_signout_icon.getBackground().setColorFilter(ContextCompat.getColor(HomeActivity.this,R.color.gray), PorterDuff.Mode.SRC_ATOP);


                menu_home_text.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.black));
                menu_messages_text.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.black));
                menu_synapseregistration_text.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.black));
                menu_participatedevents_text.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.black));
                menu_patientreferral_text.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.black));
                menu_updateprofile_text.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.black));
                menu_settings_text.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.black));
                menu_aboutus_text.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.white));
                menu_signout_text.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.black));

                AboutUsFragmentForSynapse abfrag=new AboutUsFragmentForSynapse();
                FragmentManager fragmentManager = getSupportFragmentManager();

                fragmentManager.beginTransaction().replace(R.id.mainfrag,abfrag).addToBackStack("test").commit();

            }
        });
        menu_signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences settings = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                settings.edit().clear().commit();

                menu_home.setBackgroundResource(R.color.white);
                menu_messages.setBackgroundResource(R.color.white);
                menu_synapseregistration.setBackgroundResource(R.color.white);
                menu_participatedevents.setBackgroundResource(R.color.white);
                menu_patientreferral.setBackgroundResource(R.color.white);
                menu_updateprofile.setBackgroundResource(R.color.white);
                menu_settings.setBackgroundResource(R.color.white);
                menu_aboutus.setBackgroundResource(R.color.white);
                menu_signout.setBackgroundResource(R.color.colorAccent);

                menu_home_icon.getBackground().setColorFilter(ContextCompat.getColor(HomeActivity.this,R.color.gray), PorterDuff.Mode.SRC_ATOP);
                menu_messages_icon.getBackground().setColorFilter(ContextCompat.getColor(HomeActivity.this,R.color.gray), PorterDuff.Mode.SRC_ATOP);
                menu_synapseregistration_icon.getBackground().setColorFilter(ContextCompat.getColor(HomeActivity.this,R.color.gray), PorterDuff.Mode.SRC_ATOP);
                menu_participatedevents_icon.getBackground().setColorFilter(ContextCompat.getColor(HomeActivity.this,R.color.gray), PorterDuff.Mode.SRC_ATOP);
                menu_patientreferral_icon.getBackground().setColorFilter(ContextCompat.getColor(HomeActivity.this,R.color.gray), PorterDuff.Mode.SRC_ATOP);
                menu_updateprofile_icon.getBackground().setColorFilter(ContextCompat.getColor(HomeActivity.this,R.color.gray), PorterDuff.Mode.SRC_ATOP);
                menu_settings_icon.getBackground().setColorFilter(ContextCompat.getColor(HomeActivity.this,R.color.gray), PorterDuff.Mode.SRC_ATOP);
                menu_aboutus_icon.getBackground().setColorFilter(ContextCompat.getColor(HomeActivity.this,R.color.gray), PorterDuff.Mode.SRC_ATOP);
                menu_signout_icon.getBackground().setColorFilter(ContextCompat.getColor(HomeActivity.this,R.color.white), PorterDuff.Mode.SRC_ATOP);


                menu_home_text.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.black));
                menu_messages_text.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.black));
                menu_synapseregistration_text.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.black));
                menu_participatedevents_text.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.black));
                menu_patientreferral_text.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.black));
                menu_updateprofile_text.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.black));
                menu_settings_text.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.black));
                menu_aboutus_text.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.black));
                menu_signout_text.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.white));
                Intent intlog=new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intlog);
                finish();

            }
        });


        imgexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                System.out.println("fragment by id : "+fragmentManager.findFragmentById(R.id.mainfrag));
                String current_fragment=fragmentManager.findFragmentById(R.id.mainfrag).toString().substring(0,fragmentManager.findFragmentById(R.id.mainfrag).toString().indexOf("{"));
                System.out.println("current_fragment : "+current_fragment);

                if(current_fragment.trim().equalsIgnoreCase("ProfileFragment")) {
                    /*AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
                    builder.setTitle("Zulekha Events");
                    builder.setIcon(R.mipmap.ic_launcher);
                    builder.setMessage("Do you want to exit from the app ?");
                    builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            // Show location settings when the user acknowledges the alert dialog
                            finish();
                        }
                    });

                    builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            // Show location settings when the user acknowledges the alert dialog
                        }
                    });
                    Dialog alertDialog = builder.create();
                    alertDialog.setCanceledOnTouchOutside(true);
                    alertDialog.show();*/
                    if (back_pressed + TIME_DELAY > System.currentTimeMillis()) {
                        onBackPressed();
                    } else {
                        Toasty.info(getApplicationContext(), "Press again to exit", Toast.LENGTH_SHORT, true).show();
                    }
                    back_pressed = System.currentTimeMillis();
                }else {
                    onBackPressed();
                }


            }
        });


        if(getIntent().getExtras()!=null)
        {
            name=getIntent().getExtras().getString("name");
            spec=getIntent().getExtras().getString("spec");

            pushnot = getIntent().getStringExtra("push");


           /* Toast.makeText(getApplicationContext(),"push :"+pushnot,Toast.LENGTH_SHORT).show();*/

        }


//       k

    //    CustomListView = this;

        /******** Take some data in Arraylist ( CustomListViewValuesArr ) ***********/
      //  setListData();

       // Resources res =getResources();
//        list= ( ListView )findViewById( R.id.list );  // List defined in XML ( See Below )
//
//        /**************** Create Custom Adapter *********/
//        adapter=new CustomAdapter( CustomListView, CustomListViewValuesArr,res );
//        list.setAdapter( adapter );


//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

          drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, null, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


       /* NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);*/

       // View headerview = navigationView.getHeaderView(0);
        navbackimageView=(ImageView)findViewById(R.id.navbackimageView);
        navbackimageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.closeDrawer(GravityCompat.END);
            }
        });

        Bundle bundle = new Bundle();
        bundle.putString("name", name);
        bundle.putString("spec", spec);
// set Fragmentclass Arguments

        ProfileFragment profrag = new ProfileFragment();
        profrag.setArguments(bundle);
         fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction().replace(R.id.mainfrag, profrag).commit();
        System.out.println("fragment by id : "+fragmentManager.findFragmentById(R.id.mainfrag));



        if (pushnot.equals("notification"))
        {
            MessagesFragment msfrag=new MessagesFragment();
            FragmentManager fragmentManager1 = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.mainfrag,msfrag).addToBackStack("test").commit();
        }








    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.END)) {
            drawer.closeDrawer(GravityCompat.END);
        } else {



//            Fragment webview = getSupportFragmentManager().findFragmentByTag("webview");
//            if (webview instanceof SynapseRegistrationWebFragment) {
//               // boolean goback = ((SynapseRegistrationWebFragment)webview).canGoBack();
////                if (!goback)
////                    super.onBackPressed();
//            }


//            AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
//            builder.setTitle("Exit");
//            builder.setIcon(R.mipmap.ic_launcher);
//            builder.setMessage("Are You Sure You Want To Exit The App?");
//            builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
//                public void onClick(DialogInterface dialogInterface, int i) {
//                    // Show location settings when the user acknowledges the alert dialog
//                    finish();
//                }
//            });
//
//            builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
//                public void onClick(DialogInterface dialogInterface, int i) {
//                    // Show location settings when the user acknowledges the alert dialog
//
//                }
//            });
//            Dialog alertDialog = builder.create();
//            alertDialog.setCanceledOnTouchOutside(true);
//            alertDialog.show();
            System.out.println("fragment by id : "+fragmentManager.findFragmentById(R.id.mainfrag));
            String current_fragment=fragmentManager.findFragmentById(R.id.mainfrag).toString().substring(0,fragmentManager.findFragmentById(R.id.mainfrag).toString().indexOf("{"));
            System.out.println("current_fragment : "+current_fragment);

            if(current_fragment.trim().equalsIgnoreCase("ProfileFragment")) {
                /*AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
                builder.setTitle("Zulekha Events");
                builder.setIcon(R.mipmap.ic_launcher);
                builder.setMessage("Do you want to exit from the app ?");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Show location settings when the user acknowledges the alert dialog
                        finish();
                    }
                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Show location settings when the user acknowledges the alert dialog
                    }
                });
                Dialog alertDialog = builder.create();
                alertDialog.setCanceledOnTouchOutside(true);
                alertDialog.show();*/
                if (back_pressed + TIME_DELAY > System.currentTimeMillis()) {
                    super.onBackPressed();
                } else {
                    Toasty.info(getApplicationContext(), "Press again to exit", Toast.LENGTH_SHORT, true).show();
                }
                back_pressed = System.currentTimeMillis();
            }
            else {
                super.onBackPressed();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
/*        int id = item.getItemId();

        if (id == R.id.nav_notifications) {

          *//*  Toast.makeText(getApplicationContext(),"test",Toast.LENGTH_SHORT).show();*//*


            MessagesFragment msfrag=new MessagesFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();

            fragmentManager.beginTransaction().replace(R.id.mainfrag,msfrag).addToBackStack("test").commit();
            // Handle the camera action
        }

        else if (id == R.id.nav_home) {
            *//*Toast.makeText(getApplicationContext(),"test1",Toast.LENGTH_SHORT).show();*//*

            ProfileFragment proffrag=new ProfileFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();

            fragmentManager.beginTransaction().replace(R.id.mainfrag,proffrag).commit();



        }


        else if (id == R.id.nav_synapsereg) {
            *//*Toast.makeText(getApplicationContext(),"test1",Toast.LENGTH_SHORT).show();*//*

            SynapseRegistrationWebFragment supfrag=new SynapseRegistrationWebFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();

            fragmentManager.beginTransaction().replace(R.id.mainfrag,supfrag).addToBackStack("test").commit();






        } else if (id == R.id.nav_participatedevents) {

            ParticipatedEventsFragment parfrag=new ParticipatedEventsFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();



            fragmentManager.beginTransaction().replace(R.id.mainfrag,parfrag).addToBackStack("test").commit();

           *//* Toast.makeText(getApplicationContext(),"test2",Toast.LENGTH_SHORT).show();*//*

        } else if (id == R.id.nav_patientreferral) {
            *//*Toast.makeText(getApplicationContext(),"test3",Toast.LENGTH_SHORT).show();*//*

            PatientReferralFragment patreferalfrag=new PatientReferralFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();

            fragmentManager.beginTransaction().replace(R.id.mainfrag,patreferalfrag).addToBackStack("test").commit();




        } else if (id == R.id.nav_viewprofile) {
            *//*Toast.makeText(getApplicationContext(),"test4",Toast.LENGTH_SHORT).show();*//*


            ProfileUpdateSynapseUserFragment proup=new ProfileUpdateSynapseUserFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();

            fragmentManager.beginTransaction().replace(R.id.mainfrag,proup).addToBackStack("test").commit();



        } else if (id == R.id.nav_settings) {
           // Toast.makeText(getApplicationContext(),"test5",Toast.LENGTH_SHORT).show();


          //  Toast.makeText(getApplicationContext(),"test5",Toast.LENGTH_SHORT).show();
            Intent i=new Intent(getApplicationContext(), MyPreferencesActivity.class);
            startActivity(i);
        }


        else if (id == R.id.nav_aboutus) {
            *//*Toast.makeText(getApplicationContext(),"test5",Toast.LENGTH_SHORT).show();*//*

            AboutUsFragment abfrag=new AboutUsFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();

            fragmentManager.beginTransaction().replace(R.id.mainfrag,abfrag).addToBackStack("test").commit();

        }

        else if (id == R.id.nav_signout) {
            *//*Toast.makeText(getApplicationContext(),"test5",Toast.LENGTH_SHORT).show();*//*
            SharedPreferences settings = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
            settings.edit().clear().commit();


            Intent intlog=new Intent(getApplicationContext(),LoginActivity.class);
            startActivity(intlog);
            finish();

        }*/

       drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.END);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


}
