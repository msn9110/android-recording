package com.danielkim.soundrecorder.fragments;

import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;

import com.danielkim.soundrecorder.BuildConfig;
import com.danielkim.soundrecorder.MySharedPreferences;
import com.danielkim.soundrecorder.R;
import com.danielkim.soundrecorder.activities.SettingsActivity;


public class SettingsFragment extends PreferenceFragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

        CheckBoxPreference highQualityPref = (CheckBoxPreference) findPreference(getResources().getString(R.string.pref_high_quality_key));
        highQualityPref.setChecked(MySharedPreferences.getPrefHighQuality(getActivity()));
        highQualityPref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                MySharedPreferences.setPrefHighQuality(getActivity(), (boolean) newValue);
                return true;
            }
        });

        CheckBoxPreference ftpTransferPref = (CheckBoxPreference) findPreference(getResources().getString(R.string.pref_ftp_transfer_key));
        ftpTransferPref.setChecked(MySharedPreferences.getPrefFTPTransfer(getActivity()));
        ftpTransferPref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                MySharedPreferences.setPrefFTPTransfer(getActivity(), (boolean) newValue);
                return true;
            }
        });

        String[] prefKeys = new String[] { getString(R.string.pref_ftp_host_key), getString(R.string.pref_ftp_dir_key),
                                            getString(R.string.pref_ftp_user_key), getString(R.string.pref_ftp_passwd_key) };
        EditTextPreference[] preferences = new EditTextPreference[prefKeys.length];
        for (int i = 0; i < prefKeys.length; i++) {
            final String key = prefKeys[i];
            preferences[i] = (EditTextPreference) findPreference(key);
            preferences[i].setText(MySharedPreferences.getPrefFTP(getActivity(), key));
            preferences[i].setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object value) {
                    MySharedPreferences.setPrefFTP(getActivity(), key, (String) value);
                    return true;
                }
            });
        }

        Preference aboutPref = findPreference(getString(R.string.pref_about_key));
        aboutPref.setSummary(getString(R.string.pref_about_desc, BuildConfig.VERSION_NAME));
        aboutPref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                LicensesFragment licensesFragment = new LicensesFragment();
                licensesFragment.show(((SettingsActivity)getActivity()).getSupportFragmentManager().beginTransaction(), "dialog_licenses");
                return true;
            }
        });
    }
}
