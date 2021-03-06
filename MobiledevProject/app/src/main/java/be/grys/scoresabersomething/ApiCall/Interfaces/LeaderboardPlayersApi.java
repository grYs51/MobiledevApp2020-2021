package be.grys.scoresabersomething.ApiCall.Interfaces;

import be.grys.scoresabersomething.Models.LeaderboardPlayer.LeaderboardPlayers;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface LeaderboardPlayersApi {

    @GET("api/players/{page}")
    Call<LeaderboardPlayers> getLeaderBoardPlayers(@Path(value = "page", encoded = true) String page);
}
