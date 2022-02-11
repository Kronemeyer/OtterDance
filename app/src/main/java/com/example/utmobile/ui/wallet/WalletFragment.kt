package com.example.utmobile.ui.wallet

import android.app.ActionBar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.constraintlayout.widget.ConstraintSet
import androidx.constraintlayout.widget.Constraints
import androidx.core.view.children
import androidx.core.view.isVisible
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.utmobile.databinding.FragmentWalletBinding

class WalletFragment : Fragment() {

    private var _binding: FragmentWalletBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val walletViewModel =
            ViewModelProvider(this).get(WalletViewModel::class.java)

        _binding = FragmentWalletBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val addAccount = binding.walletButton
        val linearLayout = binding.walletLinearLayout

        addAccount.setOnClickListener {
            addAccount(linearLayout)
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun addAccount(linearLayout: LinearLayoutCompat) {
        val accountFrame = LinearLayout(requireContext())
        accountFrame.layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
        val testAccount = OtterWallet(0.0f)
        val newTextView = TextView(requireContext())
        newTextView.text = "Account: ${testAccount.otterId}\nBalance: ${testAccount.getBalance()}"
        accountFrame.addView(newTextView)
        accountFrame.setOnClickListener {
            Toast.makeText(requireContext(), "Added 10 Otters to account:${testAccount.otterId}", Toast.LENGTH_SHORT).show()
            testAccount.addOtters(10.toFloat())
            newTextView.text = "Account: ${testAccount.otterId}\nBalance: ${testAccount.getBalance()}"        }
        linearLayout.addView(accountFrame)
    }
}