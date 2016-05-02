package herman.com.scoutv2;

import android.content.Intent;
import android.os.Environment;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    String data, teamNumStr, matchNumStr, autoStart, cOrRStr, defCrossedStr, autoHighStr, autoLoStr,
           portCCrossesStr, chevCrossesStr, rampartsCrossesStr, moatCrossesStr, dBridgeCrossesStr,
           sPortCrossesStr, rockWallCrossesStr, roughTCrossesStr, loBarCrosses, loMadeStr, hiMadeStr,
           hiFailStr, scaleStr, captureStr, commentsStr, firstNameStr;
    int position = 0;
    boolean  inTips, inMain, inAuto, inDefense, inShooting, inEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inTips = true;
        Toolbar myToolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(myToolbar);
        TabLayout tabLayout = (TabLayout)findViewById(R.id.tabs);
        ViewPager viewPager = (ViewPager)findViewById(R.id.view_p);
        viewPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), MainActivity.this));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setOnTabSelectedListener(
                new TabLayout.ViewPagerOnTabSelectedListener(viewPager) {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        super.onTabSelected(tab);
                        Log.d("HEY", "SWITCHED");
                        if(position == 0) {
                            inTips = true;
                            inMain = false;
                            inAuto = false;
                            inDefense = false;
                            inShooting = false;
                            inEnd = false;
                        } else if(position == 1) {
                            inTips = false;
                            inMain = true;
                            inAuto = false;
                            inDefense = false;
                            inShooting = false;
                            inEnd = false;
                        } else if(position == 2) {
                            inTips = false;
                            inMain = false;
                            inAuto = true;
                            inDefense = false;
                            inShooting = false;
                            inEnd = false;
                        } else if(position == 3) {
                            inTips = false;
                            inMain = false;
                            inAuto = false;
                            inDefense = true;
                            inShooting = false;
                            inEnd = false;
                        } else if(position == 4) {
                            inTips = false;
                            inMain = false;
                            inAuto = false;
                            inDefense = false;
                            inShooting = true;
                            inEnd = false;
                        } else if(position == 5) {
                            inTips = false;
                            inMain = false;
                            inAuto = false;
                            inDefense = false;
                            inShooting = false;
                            inEnd = true;
                        }
                        position = tab.getPosition();
                        if(inMain) {
                            EditText teamNum =(EditText) findViewById(R.id.team_num);
                            EditText matchNum =(EditText) findViewById(R.id.match_num);
                            EditText firstName =(EditText) findViewById(R.id.name);
                            teamNumStr = teamNum.getText().toString();
                            matchNumStr = matchNum.getText().toString();
                            firstNameStr = firstName.getText().toString();
                        } else if(inAuto) {
                            Spinner startingZone = (Spinner)findViewById(R.id.auto_start_spinner);
                            Spinner cOrR = (Spinner)findViewById(R.id.cross_or_reach_spinner);
                            Spinner defCrossed = (Spinner)findViewById(R.id.auto_defense_spinner);
                            Spinner autoHigh = (Spinner)findViewById(R.id.hi_made_auto_spinner);
                            Spinner autoLo = (Spinner)findViewById(R.id.lo_made_auto_spinner);
                            autoStart = startingZone.getSelectedItem().toString();
                            cOrRStr = cOrR.getSelectedItem().toString();
                            defCrossedStr = defCrossed.getSelectedItem().toString();
                            autoHighStr = autoHigh.getSelectedItem().toString();
                            autoLoStr = autoLo.getSelectedItem().toString();
                        } else if(inDefense) {
                            Spinner portCCrosses = (Spinner)findViewById(R.id.portcullis_spinner);
                            Spinner chevalCrosses = (Spinner)findViewById(R.id.cheval_spinner);
                            Spinner rampartsCrosses = (Spinner)findViewById(R.id.ramparts_spinner);
                            Spinner moatCrosses = (Spinner)findViewById(R.id.moat_spinner);
                            Spinner drawBCrosses = (Spinner)findViewById(R.id.draw_bridge_spinner);
                            Spinner sallyPCrosses = (Spinner)findViewById(R.id.sally_port_spinner);
                            Spinner rockwallCrosses = (Spinner)findViewById(R.id.rockwal_spinner);
                            Spinner roughTCrosses = (Spinner)findViewById(R.id.rough_terrain_spinner);
                            Spinner loBCrosses = (Spinner)findViewById(R.id.low_bar_spinner);
                            portCCrossesStr = portCCrosses.getSelectedItem().toString();
                            chevCrossesStr = chevalCrosses.getSelectedItem().toString();
                            rampartsCrossesStr = rampartsCrosses.getSelectedItem().toString();
                            moatCrossesStr = moatCrosses.getSelectedItem().toString();
                            dBridgeCrossesStr = drawBCrosses.getSelectedItem().toString();
                            sPortCrossesStr = sallyPCrosses.getSelectedItem().toString();
                            rockWallCrossesStr = rockwallCrosses.getSelectedItem().toString();
                            roughTCrossesStr = roughTCrosses.getSelectedItem().toString();
                            loBarCrosses = loBCrosses.getSelectedItem().toString();
                        } else if(inShooting) {
                            Spinner hiMade = (Spinner)findViewById(R.id.hi_made_spinner);
                            Spinner hiFail = (Spinner)findViewById(R.id.hi_failed_spinner);
                            Spinner loMade = (Spinner)findViewById(R.id.lo_made_spinner);
                            hiMadeStr = hiMade.getSelectedItem().toString();
                            hiFailStr = hiFail.getSelectedItem().toString();
                            loMadeStr = loMade.getSelectedItem().toString();
                        } else if(inEnd) {
                            Spinner scale = (Spinner)findViewById(R.id.scale_spinner);
                            Spinner capture = (Spinner)findViewById(R.id.capture_spinner);
                            EditText comments = (EditText)findViewById(R.id.comments_boz);
                            scaleStr = scale.getSelectedItem().toString();
                            captureStr = capture.getSelectedItem().toString();
                            commentsStr = comments.getText().toString();
                        }
                    }
                });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void initGen(View V) {

        data = firstNameStr + "." + teamNumStr + "." + matchNumStr + "." + autoStart + "." + cOrRStr + "." + defCrossedStr + "." + autoHighStr + "." + autoLoStr
                + "." + portCCrossesStr + "." + chevCrossesStr + "." + rampartsCrossesStr + "." + moatCrossesStr + "." + dBridgeCrossesStr
                + "." + sPortCrossesStr + "." + rockWallCrossesStr + "." + roughTCrossesStr + "." + loBarCrosses + "." + hiMadeStr
                + "." + hiFailStr + "." + loMadeStr + "." + scaleStr + "." + captureStr + "." + commentsStr;

        try {
            File root = new File(Environment.getExternalStorageDirectory(), "Scouting");
            if (!root.exists()) {
                root.mkdirs();
            }
            File gpxfile = new File(root, "Team_" + teamNumStr + "_Match_" + matchNumStr + ".txt");
            FileWriter writer = new FileWriter(gpxfile);
            writer.append(data);
            writer.flush();
            writer.close();
            Toast.makeText(getApplicationContext(), "File generated. Ready for BT transfer", Toast.LENGTH_SHORT).show();
            resetAll();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void resetAll() {
        Intent i = getBaseContext().getPackageManager()
                .getLaunchIntentForPackage( getBaseContext().getPackageName() );
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }

}
