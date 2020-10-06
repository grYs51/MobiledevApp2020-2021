package com.example.mobiledevproject.Models;

import com.example.mobiledevproject.Models.ScoresaberMap;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Scores {

    public List<ScoresaberMap> getScores() {
        return scores;
    }

    @SerializedName("scores")
    List<ScoresaberMap> scores;
}