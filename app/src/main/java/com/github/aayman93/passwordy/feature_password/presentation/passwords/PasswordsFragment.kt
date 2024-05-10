package com.github.aayman93.passwordy.feature_password.presentation.passwords

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.github.aayman93.passwordy.databinding.FragmentPasswordsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PasswordsFragment : Fragment() {

    private var _binding: FragmentPasswordsBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<PasswordsViewModel>()
    private lateinit var passwordsAdapter: PasswordsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPasswordsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycler()
        checkUiState()
        respondToUiEvents()
    }

    private fun setupRecycler() {
        passwordsAdapter = PasswordsAdapter(
            onCopyButtonClickListener = {
                viewModel.onCopyButtonClicked(it)
            },
            onDeleteButtonClickListener = {
                viewModel.onDeleteButtonClicked(it)
            },
            onItemClickListener = {
                viewModel.onItemClicked(it)
            }
        )
        binding.passwordsRecycler.adapter = passwordsAdapter
    }

    private fun checkUiState() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collectLatest {

                    binding.emptyState.isVisible = !it.isLoading && it.data.isNullOrEmpty()
                    binding.passwordsRecycler.isVisible = !it.data.isNullOrEmpty()

                    if (it.data != null) {
                        passwordsAdapter.submitList(it.data)
                    }
                }
            }
        }
    }

    private fun respondToUiEvents() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.eventFlow.collect {
                    when (it) {
                        is PasswordsUiEvent.ShowToast -> {
                            Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                        }

                        is PasswordsUiEvent.Navigate -> {
                            findNavController().navigate(
                                PasswordsFragmentDirections
                                    .actionPasswordsFragmentToSavePasswordFragment(it.data)
                            )
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