package com.codebury.simfocus.chatmodule

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.codebury.simfocus.chatmodule.ChatFragmentActivity

class SimformUIAdpater(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 1
    }
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                ChatFragmentActivity()
            }
            else -> {
                Fragment()
            }
        }

    }
}
