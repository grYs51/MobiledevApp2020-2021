package com.example.mobiledevproject;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mobiledevproject.model.PlayerProfile.Player;
import com.example.mobiledevproject.model.PlayerProfile.PlayerPlayerInfo;
import com.example.mobiledevproject.model.PlayerProfile.PlayerScoreStats;
import com.example.mobiledevproject.model.PlayerProfile.ScoresSaberApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ProfileFragment extends Fragment implements DialogScoresaberFragment.OnInputSelected {

    private static final String TAG = "ProfileFragment";


    private  Player player_response;

    private TextView profileUserName;
    private TextView profile_Rank_Global;
    private TextView profile_Rank_Local;
    private TextView profile_pp;
    private TextView profile_Average_Rank_Acc;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        profileUserName = view.findViewById(R.id.profileUserName);
        profile_Rank_Global = view.findViewById(R.id.profileRankGlobal);
        profile_Rank_Local = view.findViewById(R.id.profileRankLocal);
        profile_pp = view.findViewById(R.id.profilePP);
        profile_Average_Rank_Acc = view.findViewById(R.id.profileAverageRankedAcc);

        Log.d(TAG, "onClick: Opening Dialog");
                DialogScoresaberFragment dialog = new DialogScoresaberFragment();
                dialog.setTargetFragment(ProfileFragment.this, 1);
                dialog.show(getParentFragmentManager(), "DialogScoresaberFragment");



        return view;
    }

    @Override
    public void sendInput(String input) {
         Log.d(TAG, "sendInput: found incoming input: " + input);
//         mInputDisplay.setText(input);

         Retrofit retrofit = new Retrofit.Builder()
                 .baseUrl("https://new.scoresaber.com/api/")
                 .addConverterFactory(GsonConverterFactory.create())
                 .build();

        final ScoresSaberApi scoresSaberApi = retrofit.create(ScoresSaberApi.class);

        scoresSaberApi.getUserInfo(input).enqueue(new Callback<Player>() {
            @Override
            public void onResponse(Call<Player> call, Response<Player> response) {
                Log.d(TAG, "onResponse1: "+response.isSuccessful());

                if ( !response.isSuccessful()){
                    Log.d(TAG, "onResponse1: isSuccessful: "+response.code());
                    return;
                }
                player_response = response.body();
                PlayerScoreStats playerScoreStats = player_response.getScore_stats();
                PlayerPlayerInfo playerPlayerInfo = player_response.getPlayer_info();

                profileUserName.setText(playerPlayerInfo.getPlayer_Name());
                profile_Rank_Global.setText(Integer.toString( playerPlayerInfo.getRank()));
                profile_Rank_Local.setText(Integer.toString(playerPlayerInfo.getCountry_Rank()));
                profile_pp.setText(Double.toString( playerPlayerInfo.getPp()));
                profile_Average_Rank_Acc.setText( Double.toString( Math.round(playerScoreStats.getAverage_ranked_Accuracy())) );

            }

            @Override
            public void onFailure(Call<Player> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage());
            }
        });

    }

}