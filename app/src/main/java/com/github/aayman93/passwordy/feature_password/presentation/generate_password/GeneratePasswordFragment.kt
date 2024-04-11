package com.github.aayman93.passwordy.feature_password.presentation.generate_password

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.github.aayman93.passwordy.databinding.FragmentGeneratePasswordBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

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
        initClicks()
        respondToUiEvents()
    }

    private fun initViews() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }

    private fun initClicks() {
        binding.generateButton.setOnClickListener {
            viewModel.onGenerateButtonClicked()
        }
        binding.copyButton.setOnClickListener {
            viewModel.onCopyButtonClicked()
        }
        binding.saveButton.setOnClickListener {
            viewModel.onSaveButtonClicked()
        }
    }

    private fun respondToUiEvents() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.eventFlow.collect {
                    when (it) {
                        is GeneratePasswordUiEvent.Navigate -> {
                            findNavController().navigate(
                                GeneratePasswordFragmentDirections
                                    .actionGeneratePasswordFragmentToSavePasswordFragment(it.data)
                            )
                        }

                        is GeneratePasswordUiEvent.ShowToast -> {
                            Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}