package com.github.aayman93.passwordy.feature_password.presentation.save_password

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.github.aayman93.passwordy.databinding.FragmentSavePasswordBinding
import com.github.aayman93.passwordy.feature_password.presentation.utils.PasswordAction
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SavePasswordFragment : Fragment() {

    private var _binding: FragmentSavePasswordBinding? = null
    private val binding get() = _binding!!

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
        val passwordAction = args.passwordAction

        if (passwordAction is PasswordAction.SavePassword) {
            binding.passwordField.setText(passwordAction.data)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}