package com.example.mobiledevproject.ApiCall.Interfaces;

import com.example.mobiledevproject.Models.PlayerProfile.Player;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PlayerFullApi {

    @GET("api/player/{player_id}/full")
    Call<Player> getUserInfo(@Path(value = "player_id", encoded = true) String playerId);
}
