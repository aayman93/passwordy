package com.github.aayman93.passwordy.feature_password.presentation.save_password

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
import androidx.navigation.fragment.navArgs
import com.github.aayman93.passwordy.R
import com.github.aayman93.passwordy.databinding.FragmentSavePasswordBinding
import com.github.aayman93.passwordy.feature_password.presentation.utils.PasswordAction
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SavePasswordFragment : Fragment() {

    private var _binding: FragmentSavePasswordBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<SavePasswordViewModel>()
    private val args by navArgs<SavePasswordFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSavePasswordBinding.inflate(inflater, container, false)
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
        viewModel.setInitialValues(args.passwordAction)

        binding.screenTitle.text = when (args.passwordAction) {
            PasswordAction.AddPassword -> getString(R.string.add_password)
            is PasswordAction.EditPassword -> getString(R.string.edit_password)
            is PasswordAction.SavePassword -> getString(R.string.save_password)
        }
    }

    private fun initClicks() {
        binding.saveButton.setOnClickListener {
            viewModel.onSaveButtonClicked()
        }
    }

    private fun respondToUiEvents() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.eventFlow.collect {
                    when (it) {
                        is SavePasswordUiEvent.ShowToast -> {
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