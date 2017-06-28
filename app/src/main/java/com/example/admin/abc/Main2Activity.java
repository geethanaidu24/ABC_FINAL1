package com.example.admin.abc;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

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
        implements NavigationView.OnNavigationItemSelectedListener  {
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
private boolean checkNetworkConnection;
    private boolean loggedIn = false;
   private static ViewPager mPager;
   private static int currentPage = 0;
    private static int NUM_PAGES = 0;
int click=0;
    private static final Integer[] IMAGES= {R.drawable.backfinalfour, R.drawable.backfinalthree, R.drawable.backfinaltwo, R.drawable.backfinalfive,R.drawable.backfinalseven,R.drawable.backfinaleight};

    private ArrayList<Integer> ImagesArray = new ArrayList<Integer>();

    private GoogleApiClient client;
    private Menu menu;
    boolean doubleBackToExitPressedOnce = false;
  //  private int currentPage = -1;
    //Timer timer;
    int noofsize = 5;
    ViewPager myPager = null;
    int count = 0;
    Timer timer;

  /*  private ViewFlipper simpleViewFlipper;
    int[] images = {R.drawable.backfinalfour, R.drawable.backfinalthree, R.drawable.backfinaltwo, R.drawable.backfinalfive,R.drawable.backfinalseven,R.drawable.backfinaleight};     // array of images
*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        boolean finish=getIntent().getBooleanExtra("finish",false);
        if(finish)
        {
            startActivity(new Intent(this,MainActivity.class));
            finish();
            return;
        }
       /* ViewPagerAdapter adapter = new ViewPagerAdapter(Main2Activity.this, noofsize);
        myPager = (ViewPager) findViewById(R.id.reviewpager);
        myPager.setAdapter(adapter);
        myPager.setCurrentItem(0);

        // Timer for auto sliding
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (count <= 5) {
                            myPager.setCurrentItem(count);
                            count++;
                        } else {
                            count = 0;
                            myPager.setCurrentItem(count);
                        }
                    }
                });
            }
        }, 5000, 9000);*/

   /*   for(int i=0;i<IMAGES.length;i++)
            ImagesArray.add(IMAGES[i]);

        mPager = (ViewPager) findViewById(R.id.pager1);


        mPager.setAdapter(new SlidingImage_Adapter(Main2Activity.this,ImagesArray));
       mPager.setOffscreenPageLimit(IMAGES.length-1);

        CirclePageIndicator indicator = (CirclePageIndicator)
                findViewById(R.id.indicator1);

        indicator.setViewPager(mPager);

        final float density = getResources().getDisplayMetrics().density;

        indicator.setRadius(4 * density);



        NUM_PAGES =IMAGES.length;


        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES-1) {
                    currentPage = 0;
                    mPager.setCurrentItem(currentPage);
                }else{
                    mPager.setCurrentItem(currentPage++, true);
                }
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 9000, 9000);



        mPager.setCurrentItem(0);

 *//* //Timer for auto sliding
        timer  = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(NUM_PAGES<=6){
                            mPager.setCurrentItem(NUM_PAGES);
                            NUM_PAGES++;
                        }else{
                            NUM_PAGES = 0;
                            mPager.setCurrentItem(NUM_PAGES);
                        }
                    }
                });
            }
        }, 20000, 20000);
*//*
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
*/

// get The references of ViewFlipper
        // get The references of ViewFlipper
//        simpleViewFlipper = (ViewFlipper) findViewById(R.id.simpleViewFlipper); // get the reference of ViewFlipper
//
//        // loop for creating ImageView's
//        for (int i = 0; i < images.length; i++) {
//            // create the object of ImageView
//            ImageView imageView = new ImageView(this);
//            imageView.setImageResource(images[i]); // set image in ImageView
//            simpleViewFlipper.addView(imageView); // add the created ImageView in ViewFlipper
//        }
//        // Declare in and out animations and load them using AnimationUtils class
//        Animation in = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
//        Animation out = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);
//        // set the animation type's to ViewFlipper
//        simpleViewFlipper.setInAnimation(in);
//        simpleViewFlipper.setOutAnimation(out);
//        // set interval time for flipping between views
//        simpleViewFlipper.setFlipInterval(3000);
//        // set auto start for flipping between views
//        simpleViewFlipper.setAutoStart(true);

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
            checkNetworkConnection = isNetworkConnectionAvailable();
                if(checkNetworkConnection == true) {
                    click = click + 1;
                    if (click == 1) {
                        click = 0;

                        Intent in = new Intent(Main2Activity.this, Products.class);

                       // in.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        startActivity(in);
                    }
                }
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click = click + 1;
                if (click == 1) {
                    click = 0;

                    Intent in = new Intent(Main2Activity.this, AboutUs.class);
                 //   in.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(in);
                }
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkNetworkConnection = isNetworkConnectionAvailable();
                if(checkNetworkConnection == true) {
                    click = click + 1;
                    if (click == 1) {
                        click = 0;

                        Intent in = new Intent(Main2Activity.this, Enquiry.class);
                      //  in.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        startActivity(in);
                    }
                }
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkNetworkConnection = isNetworkConnectionAvailable();
                if(checkNetworkConnection == true) {
                    click = click + 1;
                    if (click == 1) {
                        click = 0;

                        Intent in = new Intent(Main2Activity.this, Contact.class);
                       // in.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        startActivity(in);
                    }
                }
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
        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        loggedIn = sharedPreferences.getBoolean(Config.LOGGEDIN_SHARED_PREF, false);
        if(loggedIn)
        {
            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.menu_logout);
        } else
        {
            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.activity_main2_drawer);

        }
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
     mPager.setOffscreenPageLimit(IMAGES.length-1);

        CirclePageIndicator indicator = (CirclePageIndicator)
                findViewById(R.id.indicator1);

        indicator.setViewPager(mPager);

        final float density = getResources().getDisplayMetrics().density;

        indicator.setRadius(4 * density);



        NUM_PAGES =IMAGES.length;



     /*  // Auto start of viewpager
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
        }, 9000, 9000);*/





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


    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        public void run() {
            if (currentPage >= 6) {
                currentPage = 0;
            } else {
                currentPage = currentPage + 1;
            }
            mPager.setCurrentItem(currentPage, true);
            handler.postDelayed(runnable, 10000);
        }
    };



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

          new AlertDialog.Builder(this) .setTitle(Html.fromHtml("<font color='#ff0000'>Exit</font>"))

                    .setMessage(Html.fromHtml(" Are you sure you want to exit?"))
                  .setIcon(R.drawable.d)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                finishAffinity();
                            }

                        }
                    }).setNegativeButton("No", null).show();

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
if(id == R.id.logout)
{
    logout();
}
      else  if (id == R.id.home) {
            click = click + 1;
            if (click == 1) {
                click = 0;

             /*   Intent in = new Intent(Main2Activity.this, Main2Activity.class);
                //in.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(in);
                finish();*/
            }


        } else if (id == R.id.ab) {
            click = click + 1;
            if (click == 1) {
                click = 0;

                Intent in = new Intent(Main2Activity.this, AboutUs.class);
                //  in.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(in);
            }

        } else if (id == R.id.product) {
            checkNetworkConnection = isNetworkConnectionAvailable();
            if (checkNetworkConnection == true) {
                click = click + 1;
                if (click == 1) {
                    click = 0;

                    Intent in = new Intent(Main2Activity.this, Products.class);

                    // in.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(in);
                }
            }
        } else if (id == R.id.brands) {
            checkNetworkConnection = isNetworkConnectionAvailable();
            if (checkNetworkConnection == true) {
                click = click + 1;
                if (click == 1) {
                    click = 0;

                    Intent in = new Intent(Main2Activity.this, Brands.class);
                    //  in.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(in);
                }
            }

        } else if (id == R.id.cu) {
            checkNetworkConnection = isNetworkConnectionAvailable();
            if (checkNetworkConnection == true) {
                click = click + 1;
                if (click == 1) {
                    click = 0;

                    Intent in = new Intent(Main2Activity.this, Contact.class);
                    // in.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(in);
                }
            }

        } else if (id == R.id.enquiry) {
            checkNetworkConnection = isNetworkConnectionAvailable();
            if (checkNetworkConnection == true) {
                click = click + 1;
                if (click == 1) {
                    click = 0;

                    Intent in = new Intent(Main2Activity.this, Enquiry.class);
                    //  in.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(in);
                }
            }

        } else if (id == R.id.nw) {
            checkNetworkConnection = isNetworkConnectionAvailable();
            if (checkNetworkConnection == true) {
                click = click + 1;
                if (click == 1) {
                    click = 0;

                    Intent in = new Intent(Main2Activity.this, News.class);
                    // in.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(in);
                }
            }

        } else if (id == R.id.of) {
            checkNetworkConnection = isNetworkConnectionAvailable();
           /* Intent in = new Intent(Main2Activity.this,Offers.class);
            startActivity(in);*/
           /* Toast.makeText(this, "No Current Offers.....", Toast.LENGTH_LONG).show();*/
            if (checkNetworkConnection == true) {
                click = click + 1;
                if (click == 1) {
                    click = 0;

                    Toast toast = Toast.makeText(getApplicationContext(),
                            "No Current Offers.....",
                            Toast.LENGTH_SHORT);

                    View toastView = toast.getView();
                    toastView.setBackgroundResource(R.drawable.toast_drawable);
                    toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast.show();
                }
            }
        } else if (id == R.id.login) {
            checkNetworkConnection = isNetworkConnectionAvailable();
            if (checkNetworkConnection == true) {
                click = click + 1;
                if (click == 1) {
                    click = 0;

                    // getMenuInflater().inflate(R.menu.activity_main2_drawer, menu);

                    if (loggedIn ) {
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "Admin Already Login.....",
                                Toast.LENGTH_SHORT);

                        View toastView = toast.getView();
                        toastView.setBackgroundResource(R.drawable.toast_drawable);
                        toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
                        toast.show();

                    } else if (!loggedIn ) {

                        Intent in = new Intent(Main2Activity.this, Login.class);
                        //in.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        startActivity(in);

                    }
                }
            }
        }


        /*else if (id == R.id.logout) {
            checkNetworkConnection = isNetworkConnectionAvailable();
            if(checkNetworkConnection == true) {
                click = click + 1;
                if (click == 1) {
                    click = 0;
                    SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                    loggedIn = sharedPreferences.getBoolean(Config.LOGGEDIN_SHARED_PREF, false);
                    getMenuInflater().inflate(R.menu.activity_main2_drawer, menu);

                    if (loggedIn == true) {

                        MenuItem itemss = menu.findItem(R.id.logout);
                        itemss.setVisible(false);
                        MenuItem items2 = menu.findItem(R.id.login);
                        items2.setVisible(true);
                        logout();
                        return true;
                    }
                    *//* else if (loggedIn == false) {
                        MenuItem itemss = menu.findItem(R.id.logout);
                        itemss.setVisible(false);
                        MenuItem items2 = menu.findItem(R.id.login);
                        items2.setVisible(true);
                        Intent in = new Intent(Main2Activity.this, Login.class);
                        //in.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        startActivity(in);

                    }*//*

                }
            }
        }
*/
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }


    private void logout(){
        //Creating an alert dialog to confirm logout
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // alertDialogBuilder.setMessage("Are you sure you want to logout?");
        alertDialogBuilder.setTitle(" Are you sure you want to logout?");
        alertDialogBuilder.setIcon(R.drawable.logoutt);
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
                        editor.putString(Config.KEY_USER, "");

                        //Saving the sharedpreferences
                        editor.commit();

                        //Starting login activity

                        Intent intent = new Intent(Main2Activity.this, Main2Activity.class);
                        intent.putExtra("finish",true);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                                Intent.FLAG_ACTIVITY_CLEAR_TASK |
                                Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);

                        finish();
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
    @Override
    public void onPause(){
        super.onPause();
        if(handler!=null){
            handler.removeCallbacks(runnable);
        }
    }
    @Override
    public void onResume(){
        super.onResume();
        handler.postDelayed(runnable,10000);
    }


        //Logout function

   /* private void logout(){
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
    }*/
    public void checkNetworkConnection(){
        AlertDialog.Builder builder =new AlertDialog.Builder(this);

        builder.setTitle("No Internet Connection");
        builder.setIcon(R.drawable.internet);
        builder.setMessage(Html.fromHtml("<font color='#ff0000'> Please turn on Internet Connection to Continue</font>"));
        builder.setNegativeButton("close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public boolean isNetworkConnectionAvailable(){
        ConnectivityManager cm =
                (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnected();
        if(isConnected) {
            Log.d("Network", "Connected");
            return true;
        }
        else{
            checkNetworkConnection();
            Log.d("Network","Not Connected");
            return false;
        }
    }
}
