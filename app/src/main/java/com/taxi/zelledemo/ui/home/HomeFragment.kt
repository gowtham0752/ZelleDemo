package com.taxi.zelledemo.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.fiserv.dps.mobile.sdk.bridge.controller.Bridge
import com.fiserv.dps.mobile.sdk.bridge.model.Zelle
import com.taxi.zelledemo.MainActivity
import com.taxi.zelledemo.R
import com.taxi.zelledemo.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val zelle = Zelle(
        institutionId = "8888002",
        ssoKey = "1aa248ec9dfac7a8add7280f001b3729",
        parameters = mapOf(
            "param1" to "1234",
            "param2" to "something",
            "param3" to "abc123"
        )
    )

    private val bridge: Bridge by lazy {
        Bridge(
            activity = activity as MainActivity,
            config = zelle
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
       Log.d("onCreateView","------------------->onCreateView")
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("onViewCreated","------------------->onViewCreated")
        zelle.preCacheContacts = true
        val view = bridge.view()
        requireActivity().supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_holder, view)
            commit()
        }
    }


}