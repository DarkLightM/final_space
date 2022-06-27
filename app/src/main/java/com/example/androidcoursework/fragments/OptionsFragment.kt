package com.example.androidcoursework.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SwitchCompat
import com.example.androidcoursework.R

class OptionsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_options, container, false)
    }

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dbSwitch = view.findViewById<SwitchCompat>(R.id.databaseSwitch)
        val themeSwitch = view.findViewById<SwitchCompat>(R.id.themeSwitch)
        val sharedPreferences =
            activity?.getSharedPreferences(getString(R.string.shared_prefs), Context.MODE_PRIVATE)
                ?: return
        val dbState = sharedPreferences.getBoolean("dbSwitch", false)
        val themeState = sharedPreferences.getBoolean("themeSwitch", false)
        dbSwitch.isChecked = dbState
        themeSwitch.isChecked = themeState

    }

    @SuppressLint("CommitPrefEdits", "UseSwitchCompatOrMaterialCode")
    override fun onPause() {
        super.onPause()
        val dbSwitch = view?.findViewById<SwitchCompat>(R.id.databaseSwitch)
        val themeSwitch = view?.findViewById<SwitchCompat>(R.id.themeSwitch)
        val sharedPreferences =
            activity?.getSharedPreferences(getString(R.string.shared_prefs), Context.MODE_PRIVATE)
                ?.edit() ?: return
        if (dbSwitch != null)
            sharedPreferences.putBoolean("dbSwitch", dbSwitch.isChecked).apply()
        if (themeSwitch != null)
            sharedPreferences.putBoolean("themeSwitch", themeSwitch.isChecked).apply()
    }
}