package com.raya.task.ui.user_detailes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.raya.task.R
import com.raya.task.data.model.UserData
import com.raya.task.data.model.UserDetails
import com.raya.task.ui.HomeViewModel
import com.raya.task.ui.home.HomeFragment
import com.raya.task.ui.home.UserAdapter
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_user_detailes.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class UserDetailesFragment : Fragment() {
    private var userid: String? = null

    val usersViewModel: HomeViewModel by lazy {
        ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            userid = it.getString("userid")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val inflate = inflater.inflate(R.layout.fragment_user_detailes, container, false)

        CoroutineScope(Dispatchers.IO).launch {
            userid?.let { usersViewModel.getUserDetailes(it) }
        }

        handleObservers()

        return inflate
    }

    private fun handleObservers() {
        usersViewModel.usersDetailes.observe(viewLifecycleOwner, Observer { userDetails ->
            if (userDetails != null) {
                bindView(userDetails)
            }
        })

        usersViewModel.isLoading.observe(viewLifecycleOwner, Observer {
            when (it) {
                true -> {
                    progress_bar_user_detailes.visibility = View.VISIBLE
                }
                false -> {
                    progress_bar_user_detailes.visibility = View.GONE
                }
            }
        })
    }

    private fun bindView(userDetails: UserDetails) {
        textView_user_id.text = userDetails.id
        textView_user_first_name.text = userDetails.first_name
        textView_user_last_name.text = userDetails.last_name
        textView_email.text = userDetails.email
    }

    companion object {
        val TAG = UserDetailesFragment::class.java.simpleName

        @JvmStatic
        fun newInstance(userId: String) =
            UserDetailesFragment().apply {
                arguments = Bundle().apply {
                    putString("userid", userId)
                }
            }
    }

    override fun onStop() {
        progress_bar_user_detailes.visibility = View.GONE
        super.onStop()
    }
}