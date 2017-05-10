package com.example.admin.abc;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

//import com.viewpagerindicator.CirclePageIndicator;

public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
   // public int currentimageindex = 0;

   // ImageView slidingimage;

  //  private int[] IMAGE_IDS = {
           // R.mipmap.backfinalfour, R.mipmap.backfinalthree, R.mipmap.backfinaltwo, R.mipmap.backfinalfive, R.mipmap.backfinalsix};

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     *
     *
     */
private boolean menuOptionState=false;

   private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;

    private static final Integer[] IMAGES= {R.mipmap.backfinalfour, R.mipmap.backfinalthree, R.mipmap.backfinaltwo, R.mipmap.backfinalfive,R.mipmap.backfinalseven,R.mipmap.backfinaleight};

    private ArrayList<Integer> ImagesArray = new ArrayList<Integer>();

    private GoogleApiClient client;
    private Menu menu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
       Button b1, b2, b3, b4;
        init();

        // ViewPager viewPager;
        // int images[] = { R.mipmap.backfinalfour, R.mipmap.backfinaltwo,R.mipmap.backfinalthree,R.mipmap.backfinalfive,R.mipmap.backfinalsix,R.mipmap.backfour};
        //  MyCustomPagerAdapter myCustomPagerAdapter;


        // viewPager = (ViewPager)findViewById(R.id.viewPager);

        //  myCustomPagerAdapter = new MyCustomPagerAdapter(Main2Activity.this, images);
        // viewPager.setAdapter(myCustomPagerAdapter);
       /* final Handler mHandler = new Handler();

        // Create runnable for posting
        final Runnable mUpdateResults = new Runnable() {
            public void run() {

                AnimateandSlideShow();

            }
        };

        int delay = 800; // delay for 1 sec.

        int period = 1000; // repeat every 4 sec.

        Timer timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {

            public void run() {

                mHandler.post(mUpdateResults);

            }

        }, delay, period);


        /**
         * Helper method to start the animation on the splash screen
         */


       b1 = (Button) findViewById(R.id.button1);
        b2 = (Button) findViewById(R.id.buttonh);
        b3 = (Button) findViewById(R.id.button3);
        b4 = (Button) findViewById(R.id.button4);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Main2Activity.this, Products.class);
                startActivity(in);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Main2Activity.this, AboutUs.class);
                startActivity(in);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Main2Activity.this, Enquiry.class);
                startActivity(in);
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Main2Activity.this, Contact.class);
                startActivity(in);
            }
        });





        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

  /*  private void AnimateandSlideShow() {


        slidingimage = (ImageView)findViewById(R.id.ImageView3_Left);
        slidingimage.setImageResource(IMAGE_IDS[currentimageindex%IMAGE_IDS.length]);

        currentimageindex++;

        Animation rotateimage = AnimationUtils.loadAnimation(this, R.anim.fade_in);


        slidingimage.startAnimation(rotateimage);



    }*/

    private void init() {


        for(int i=0;i<IMAGES.length;i++)
            ImagesArray.add(IMAGES[i]);

        mPager = (ViewPager) findViewById(R.id.pager1);


        mPager.setAdapter(new SlidingImage_Adapter(Main2Activity.this,ImagesArray));


        CirclePageIndicator indicator = (CirclePageIndicator)
                findViewById(R.id.indicator1);

        indicator.setViewPager(mPager);

        final float density = getResources().getDisplayMetrics().density;

        indicator.setRadius(5 * density);



        NUM_PAGES =IMAGES.length;



        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 3000);

        // Pager listener over indicator
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;

            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });

    }




    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

   /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
       this.menu = menu;
       // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.logout) {
            Intent in = new Intent(Main2Activity.this, MainActivity.class);
            startActivity(in);
        }

        return super.onOptionsItemSelected(item);
    }
   */

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home) {
            Intent in = new Intent(Main2Activity.this, Main2Activity.class);
            startActivity(in);

        }else if (id == R.id.ab) {
            Intent in = new Intent(Main2Activity.this, AboutUs.class);
            startActivity(in);

        }
        else if (id == R.id.product) {
            Intent in = new Intent(Main2Activity.this, Products.class);
            startActivity(in);
        } else if (id == R.id.brands) {
            Intent in = new Intent(Main2Activity.this, Brands.class);
            startActivity(in);

        }  else if (id == R.id.cu) {
            Intent in = new Intent(Main2Activity.this, Contact.class);
            startActivity(in);

        } else if (id == R.id.enquiry) {
            Intent in = new Intent(Main2Activity.this, Enquiry.class);
            startActivity(in);

        }  else if (id == R.id.nw) {
            Intent in = new Intent(Main2Activity.this,News.class);
            startActivity(in);

        } else if (id == R.id.of) {
            Intent in = new Intent(Main2Activity.this,Offers.class);
            startActivity(in);


        } else if (id == R.id.login) {
            Intent in = new Intent(Main2Activity.this, Login.class);
            startActivity(in);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main2 Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }


        //Logout function
    private void logout(){
        //Creating an alert dialog to confirm logout

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure you want to logout?");
        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {


                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                                        //Getting out sharedpreferences
                        SharedPreferences preferences = getSharedPreferences(Config.SHARED_PREF_NAME,Context.MODE_PRIVATE);
                        //Getting editor
                        SharedPreferences.Editor editor = preferences.edit();

                        //Puting the value false for loggedin
                        editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, false);

                        //Putting blank value to email
                        editor.putString(Config.USER_SHARED_PREF, "");
                        editor.putString(Config.LOGIN_CHECK, "fail");
                        //Saving the sharedpreferences
                        editor.commit();
                        invalidateOptionsMenu();
                       // menu.getItem(R.id.menuLogout).setEnabled(true);
                        //Starting login activity
                        Intent intent = new Intent(Main2Activity.this, Main2Activity.class);
                        startActivity(intent);
                    }
                });

        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        //Showing the alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


                //Getting out sharedpreferences
        SharedPreferences preferences = getSharedPreferences(Config.LOGIN_CHECK, Context.MODE_PRIVATE);
            //Getting editor
        SharedPreferences.Editor editor = preferences.edit();
        if (Config.LOGIN_CHECK == "suc") {

            //Adding our menu to toolbar
            getMenuInflater().inflate(R.menu.main2, menu);

            // editor.putString(Config.LOGIN_CHECK, "fail");
            // editor.commit();
            return true;
        } else {
            return false;
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       final int id = item.getItemId();
        if (id == R.id.menuLogout) {
            //calling logout method when the logout button is clicked
            logout();
        }
        return super.onOptionsItemSelected(item);
    }
}
