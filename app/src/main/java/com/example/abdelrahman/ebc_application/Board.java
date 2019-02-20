package com.example.abdelrahman.ebc_application;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
    private ArrayList<BoardInfo> boardMembers;

    // Layout Variables
    private RecyclerView recyclerView;

    // Firebase Variables
    private FirebaseRemoteConfig mFirebaseRemoteConfig;

    // Parameters
    private static String KEY_Images = "board_images";
    private  static String KEY_Board = "board_members";

    private String links = "null";
    private String members = "null";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        boardMembers = new ArrayList<>();

        // Initialize Firebase Objects
        mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();

        // Initialize references to views
        recyclerView = findViewById(R.id.recycler);

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
    private void applyRetrievedLengthLimit() {
        String fetchedImages = mFirebaseRemoteConfig.getString(KEY_Images);
        String fetchedMembers = mFirebaseRemoteConfig.getString(KEY_Board);
        links = fetchedImages;
        members = fetchedMembers;
        if (!fetchedImages.equalsIgnoreCase("null") && !fetchedMembers.equalsIgnoreCase("null")){
            String[] linksDetails = links.split(",");
            String[] membersDetails = members.split(",");
            for (int i = 0; i < linksDetails.length; i++){
                boardMembers.add(new BoardInfo(linksDetails[i],membersDetails[2 * i], membersDetails[(2 * i)+1]));
            }
            BoardAdapter adapter=new BoardAdapter(boardMembers,this);
            recyclerView.setLayoutManager(new GridLayoutManager(this,1));
            recyclerView.setAdapter(adapter);
        } else {
            Toast.makeText(this, links, Toast.LENGTH_LONG).show();
        }
    }
}
