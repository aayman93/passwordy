package com.github.aayman93.passwordy.feature_password.presentation.passwords

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.aayman93.passwordy.databinding.FragmentPasswordsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PasswordsFragment : Fragment() {

    private var _binding: FragmentPasswordsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPasswordsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}