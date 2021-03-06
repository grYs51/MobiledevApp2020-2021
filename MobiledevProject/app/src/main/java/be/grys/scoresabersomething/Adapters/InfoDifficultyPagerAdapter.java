package be.grys.scoresabersomething.Adapters;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import be.grys.scoresabersomething.Beatsaver.BeatSaverMapInfo.ViewPager.DifficultyInfoPar;
import be.grys.scoresabersomething.Models.Beatsaver.beatsavermap.DifficultiesSpecs;

import java.util.ArrayList;
import java.util.List;


public class InfoDifficultyPagerAdapter extends FragmentPagerAdapter {

    private static final String TAG = "DifficultyAdapter";
    DifficultiesSpecs difficultiesSpecs;
    int duration;
    List<String> strings = new ArrayList<String>();
    String key;

    public InfoDifficultyPagerAdapter(@NonNull FragmentManager fm, DifficultiesSpecs difficultiesSpecs, int duration, String key) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.difficultiesSpecs = difficultiesSpecs;
        this.duration = duration;
        this.key = key;

        if (this.difficultiesSpecs.getEasy() != null) {
            strings.add("Easy");
        }
        if (this.difficultiesSpecs.getNormal() != null) {
            strings.add("Normal");
        }
        if (this.difficultiesSpecs.getHard() != null) {
            strings.add("Hard");
        }
        if (this.difficultiesSpecs.getExpert() != null) {
            strings.add("Expert");
        }
        if (this.difficultiesSpecs.getExpertPlus() != null) {
            strings.add("Expert+");
        }
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (strings.get(position)) {
            case "Easy":
                return new DifficultyInfoPar(difficultiesSpecs.getEasy(), duration, key);
            case "Normal":
                return new DifficultyInfoPar(difficultiesSpecs.getNormal(), duration, key);
            case "Hard":
                return new DifficultyInfoPar(difficultiesSpecs.getHard(), duration, key);
            case "Expert":
                return new DifficultyInfoPar(difficultiesSpecs.getExpert(), duration, key);
            case "Expert+":
                return new DifficultyInfoPar(difficultiesSpecs.getExpertPlus(), duration, key);
            default:
                return new DifficultyInfoPar(null, duration, key);
        }
    }

    @Override
    public int getCount() {
        return strings.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        Log.d(TAG, "getPageTitle: " + difficultiesSpecs);
        return strings.get(position);
    }
}
