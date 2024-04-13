package com.github.aayman93.passwordy.feature_password.presentation.biometric_authentication

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.github.aayman93.passwordy.MainActivity
import com.github.aayman93.passwordy.R
import com.github.aayman93.passwordy.databinding.FragmentBiometricAuthenticationBinding
import com.github.aayman93.passwordy.utils.BiometricPromptManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BiometricAuthenticationFragment : Fragment() {

    private var _binding: FragmentBiometricAuthenticationBinding? = null
    private val binding get() = _binding!!

    private val promptManager by lazy {
        BiometricPromptManager(requireActivity() as MainActivity)
    }

    private val enrollLauncher = registerForActivityResult(StartActivityForResult()) { result ->
        println(result)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBiometricAuthenticationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClicks()
        checkAuthenticationResult()
    }

    private fun initClicks() {
        binding.continueButton.setOnClickListener {
            promptManager.showBiometricPrompt(
                title = getString(R.string.biometric_auth_title),
                description = getString(R.string.biometric_auth_description)
            )
        }
    }

    private fun checkAuthenticationResult() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                promptManager.promptResults.collect {
                    when (it) {
                        is BiometricPromptManager.BiometricResult.AuthenticationError -> {
                            showToast(it.error)
                        }

                        is BiometricPromptManager.BiometricResult.AuthenticationFailed -> {
                            showToast("Authentication failed")
                        }

                        is BiometricPromptManager.BiometricResult.AuthenticationNotSet -> {
                            showToast("Authentication not set")

                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                                val enrollIntent = Intent(Settings.ACTION_BIOMETRIC_ENROLL).apply {
                                    putExtra(
                                        Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED,
                                        BIOMETRIC_STRONG or DEVICE_CREDENTIAL
                                    )
                                }
                                enrollLauncher.launch(enrollIntent)
                            }
                        }

                        is BiometricPromptManager.BiometricResult.AuthenticationSuccess -> {
                            showToast("Authentication success")

                            findNavController().navigate(
                                BiometricAuthenticationFragmentDirections
                                    .actionBiometricAuthenticationFragmentToPasswordsFragment()
                            )
                        }

                        is BiometricPromptManager.BiometricResult.FeatureUnavailable -> {
                            showToast("Feature unavailable")
                        }

                        is BiometricPromptManager.BiometricResult.HardwareUnavailable -> {
                            showToast("Hardware unavailable")
                        }
                    }
                }
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}