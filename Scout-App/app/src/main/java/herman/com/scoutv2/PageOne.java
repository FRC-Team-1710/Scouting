package herman.com.scoutv2;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PageOne extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";

    public int mPage;
    public boolean inTips = true;
    public boolean inMain, inAuto, inDefenses, inShooting, inEnd;

    public String[] mainData = {"0", "0"};
    public String startingZoneStr, teamNumStr, matchNumStr;

    int layout = R.layout.fragment_page_one;

    String[] zones = {"not present", "spy zone", "midline"};
    String[] cOrR = {"no", "Reach", "cross"};
    String[] defenses = {"none", "Low Bar", "Portcullis", "Cheval de Frise", "Ramparts", "Moat", "Draw Bridge", "Sally Port", "Rockwall", "Rough Terrain"};
    String[] autoShotsAr = {"0", "1", "2"};
    String[] shots = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    String[] crosses = {"0", "1", "2"};
    String[] yOrNC = {"No", "Yes"};


    Spinner startingZone, crossOrReach, defenseCrossed, autoShotsMadeHi, autoShotsMadeLo, loBarCrosses, portCCrosses,chevalCrosses, rampartsCrosses, moatCrosses,
            bridgeCrosses, portSCrosses, rockCrosses, roughCrosses, hiMade, hiFail, loMade, scale, capture;

    public static PageOne newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        PageOne fragment = new PageOne();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(layout, container, false);

        //tips
        if(mPage == 1) {
            view = inflater.inflate(R.layout.fragment_page_one, container, false);
            return view;
        }
        //main
        if(mPage == 2) {
            view = inflater.inflate(R.layout.page_two, container, false);
            return view;
        }
        //auto
        if(mPage == 3) {
            view = inflater.inflate(R.layout.page_three, container, false);
            initShit(3, view);
            return view;
        }
        //defenses
        if(mPage == 4) {
            view = inflater.inflate(R.layout.page_four, container, false);
            initShit(4, view);
            return view;
        }
        //shooting
        if(mPage == 5) {
            view = inflater.inflate(R.layout.page_five, container, false);
            initShit(5, view);
            return view;
        }
        //end game
        if(mPage == 6) {
            view = inflater.inflate(R.layout.page_six, container, false);
            initShit(6, view);
            return view;
        }
        return view;
    }

    public void initShit(int i, View view) {
        if(i==3) {
            startingZone = (Spinner) view.findViewById(R.id.auto_start_spinner);
            ArrayAdapter<String> adapterStart = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_spinner_item, zones);
            adapterStart.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            startingZone.setAdapter(adapterStart);

            crossOrReach = (Spinner) view.findViewById(R.id.cross_or_reach_spinner);
            ArrayAdapter<String> adapterCR = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_spinner_item, cOrR);
            adapterCR.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            crossOrReach.setAdapter(adapterCR);

            defenseCrossed = (Spinner) view.findViewById(R.id.auto_defense_spinner);
            ArrayAdapter<String> autoD = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_spinner_item, defenses);
            autoD.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            defenseCrossed.setAdapter(autoD);

            autoShotsMadeHi = (Spinner) view.findViewById(R.id.hi_made_auto_spinner);
            autoShotsMadeLo = (Spinner) view.findViewById(R.id.lo_made_auto_spinner);
            ArrayAdapter<String> autoShots = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_spinner_item, autoShotsAr);
            autoShots.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            autoShotsMadeHi.setAdapter(autoShots);
            autoShotsMadeLo.setAdapter(autoShots);

        }

        if(i==4) {
            loBarCrosses = (Spinner) view.findViewById(R.id.low_bar_spinner);
            portCCrosses = (Spinner) view.findViewById(R.id.portcullis_spinner);
            chevalCrosses = (Spinner) view.findViewById(R.id.cheval_spinner);
            rampartsCrosses = (Spinner) view.findViewById(R.id.ramparts_spinner);
            moatCrosses = (Spinner) view.findViewById(R.id.moat_spinner);
            bridgeCrosses = (Spinner) view.findViewById(R.id.draw_bridge_spinner);
            portSCrosses = (Spinner) view.findViewById(R.id.sally_port_spinner);
            rockCrosses = (Spinner) view.findViewById(R.id.rockwal_spinner);
            roughCrosses = (Spinner) view.findViewById(R.id.rough_terrain_spinner);
            ArrayAdapter<String> crossesAdapter = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_spinner_item, crosses);
            crossesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            loBarCrosses.setAdapter(crossesAdapter);
            portCCrosses.setAdapter(crossesAdapter);
            chevalCrosses.setAdapter(crossesAdapter);
            rampartsCrosses.setAdapter(crossesAdapter);
            moatCrosses.setAdapter(crossesAdapter);
            bridgeCrosses.setAdapter(crossesAdapter);
            portSCrosses.setAdapter(crossesAdapter);
            rockCrosses.setAdapter(crossesAdapter);
            roughCrosses.setAdapter(crossesAdapter);
        }

        if(i==5) {
            hiMade = (Spinner) view.findViewById(R.id.hi_made_spinner);
            hiFail = (Spinner) view.findViewById(R.id.hi_failed_spinner);
            loMade = (Spinner) view.findViewById(R.id.lo_made_spinner);
            ArrayAdapter<String> teleShots = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_spinner_item, shots);
            teleShots.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            hiMade.setAdapter(teleShots);
            hiFail.setAdapter(teleShots);
            loMade.setAdapter(teleShots);
        }

        if(i==6) {
            scale = (Spinner) view.findViewById(R.id.scale_spinner);
            capture = (Spinner) view.findViewById(R.id.capture_spinner);
            ArrayAdapter<String> yOrN = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_spinner_item, yOrNC);
            yOrN.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            scale.setAdapter(yOrN);
            capture.setAdapter(yOrN);
        }
    }

    public void GenerateFile(View v) {
        String data = teamNumStr + " " + matchNumStr + " ";
        Log.d("TeamNum", teamNumStr);


    }

}
