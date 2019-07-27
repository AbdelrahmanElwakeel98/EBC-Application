package com.example.abdelrahman.ebc_application;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Board extends AppCompatActivity {

    private static final String TAG = "BoardActivity";
    private ArrayList<HeadDataHolder> boardMembers;
    private ArrayList<HeadDataHolder> boardCommittees;
    private ArrayList<CommitteeHeads> boardLinear;

    // Layout Variables
    private RecyclerView recyclerView;
    ProgressBar progressBar;

    // Firebase Variables
    private FirebaseRemoteConfig mFirebaseRemoteConfig;

    // Parameters
    private static String KEY_Images = "board_images";
    private  static String KEY_Board = "board_members";
    private  static String KEY_Committee = "committees";

    private String links = "null";
    private String members = "null";
    private String committees = "null";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        boardMembers = new ArrayList<>();
        boardCommittees = new ArrayList<>();
        boardLinear = new ArrayList<>();

        // Initialize Firebase Objects
        mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();

        // Initialize references to views
        recyclerView = findViewById(R.id.recycler);
        progressBar = (ProgressBar) findViewById(R.id.circular_progress_bar);

        progressBar.setVisibility(View.VISIBLE);

        // Create Remote Config Setting to enable developer mode.
        // Fetching configs from the server is normally limited to 5 requests per hour.
        // Enabling developer mode allows many more requests to be made per hour, so developers
        // can test different config values during development.
        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                .setDeveloperModeEnabled(BuildConfig.DEBUG)
                .build();
        mFirebaseRemoteConfig.setConfigSettings(configSettings);

        // Define default config values. Defaults are used when fetched config values are not
        // available. Eg: if an error occurred fetching values from the server.
        Map<String, Object> defaultConfigMap = new HashMap<>();
        defaultConfigMap.put(KEY_Committee, committees);
        defaultConfigMap.put(KEY_Images, links);
        defaultConfigMap.put(KEY_Board, members);
        mFirebaseRemoteConfig.setDefaults(defaultConfigMap);
        fetchConfig();
    }
    // Fetch the config
    public void fetchConfig() {
        long cacheExpiration = 3600; // 1 hour in seconds
        // If developer mode is enabled reduce cacheExpiration to 0 so that each fetch goes to the
        // server. This should not be used in release builds.
        if (mFirebaseRemoteConfig.getInfo().getConfigSettings().isDeveloperModeEnabled()) {
            cacheExpiration = 0;
        }
        mFirebaseRemoteConfig.fetch(cacheExpiration)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Make the fetched config available
                        // via FirebaseRemoteConfig get<type> calls, e.g., getLong, getString.
                        mFirebaseRemoteConfig.activateFetched();

                        // the newly retrieved values from Remote Config.
                        applyRetrievedLengthLimit();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // An error occurred when fetching the config.
                        Log.w(TAG, "Error fetching config", e);

                        // Update the EditText length limit with
                        // the newly retrieved values from Remote Config.
                        applyRetrievedLengthLimit();
                    }
                });
    }

    /**
     * Apply retrieved length limit to edit text field. This result may be fresh from the server or it may be from
     * cached values.
     */
    @SuppressLint("WrongConstant")
    private void applyRetrievedLengthLimit() {
        String fetchedImages = mFirebaseRemoteConfig.getString(KEY_Images);
        String fetchedMembers = mFirebaseRemoteConfig.getString(KEY_Board);
        String fetchedCommittees = mFirebaseRemoteConfig.getString(KEY_Committee);
        links = fetchedImages;
        members = fetchedMembers;
        committees = fetchedCommittees;
        if (!fetchedImages.equalsIgnoreCase("null") && !fetchedMembers.equalsIgnoreCase("null") && !fetchedCommittees.equalsIgnoreCase("null")){
            String[] linksDetails = links.split(",");
            String[] membersDetails = members.split(",");
            String[] committeesDetails = committees.split(",");

              // Log.d("dd", fetchedMembers);

            ArrayList<committeeHolder> committeePair = new ArrayList<>();

            int k = 0;

            for (int i = 0; i < committeesDetails.length - 1; i = i+2){
                committeePair.add(new committeeHolder(committeesDetails[i], Integer.valueOf(committeesDetails[i + 1])));
            }

           // Log.d("Num", String.valueOf(committeePair.size()));

            for (int i = 0; i < committeePair.size(); i++){
                boardCommittees = new ArrayList<>();

                for (int j = k; j < committeePair.get(i).getNumOfCommittee() + k; j++){
                    boardCommittees.add(new HeadDataHolder(linksDetails[j],membersDetails[2 * j], membersDetails[(2 * j)+1]));
                }

                boardLinear.add(new CommitteeHeads(boardCommittees,
                        committeePair.get(i).getCommittee(), committeePair.get(i).getNumOfCommittee()));
                k += committeePair.get(i).getNumOfCommittee();
            }



            BoardAdapter adapter=new BoardAdapter(boardLinear,this);
            recyclerView.setLayoutManager(new GridLayoutManager(this,1));
            recyclerView.setAdapter(adapter);
            progressBar.setVisibility(8);
        } else {
            Toast.makeText(this, links, Toast.LENGTH_LONG).show();
        }
    }
}
