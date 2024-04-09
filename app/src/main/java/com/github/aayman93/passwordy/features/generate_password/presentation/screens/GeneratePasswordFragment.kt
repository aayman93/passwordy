package com.github.aayman93.passwordy.features.generate_password.presentation.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.github.aayman93.passwordy.databinding.FragmentGeneratePasswordBinding
import com.github.aayman93.passwordy.features.generate_password.presentation.view_model.GeneratePasswordViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GeneratePasswordFragment : Fragment() {

    private var _binding: FragmentGeneratePasswordBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<GeneratePasswordViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGeneratePasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}