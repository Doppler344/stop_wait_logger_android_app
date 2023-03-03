package com.example.android_practice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.android_practice.databinding.FragmentFirstBinding
import com.google.android.material.snackbar.Snackbar
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
        val client = AsyncHttpClient()
        client["https://6401d995ab6b7399d0ae14fc.mockapi.io/admin_api/v1/log", object : AsyncHttpResponseHandler(){
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray
            ) {
                binding.buttonFirst.setOnClickListener {

                    findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
                    Snackbar.make(view, String(responseBody), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()}

            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable
            ) {
                binding.buttonFirst.setOnClickListener {
                    findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
                    Snackbar.make(view, "Fail $statusCode", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()}
            }
        }]
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}