package com.raya.task.ui.home

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.raya.task.R
import com.raya.task.ui.HomeViewModel
import com.raya.task.ui.user_detailes.UserDetailesFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(), UserAdapter.OnItemClickListener {

    val usersViewModel: HomeViewModel by lazy {
        ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)
    }

    private lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val inflate = inflater.inflate(R.layout.fragment_home, container, false)
        CoroutineScope(Dispatchers.IO).launch {
            usersViewModel.getAllUsers()
        }

        handleObservers()

        handleOnPressedBackButton(inflate)

        return inflate
    }

    private fun handleObservers() {
        usersViewModel.users.observe(viewLifecycleOwner, Observer { usersList ->
            if (usersList != null) {
                adapter = UserAdapter(usersList, this)
                users_recyclerView.layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                users_recyclerView.adapter = adapter
            }
        })

        usersViewModel.isLoading.observe(viewLifecycleOwner, Observer {
            when (it) {
                true -> {
                    progress_bar.visibility = View.VISIBLE
                }
                false -> {
                    progress_bar.visibility = View.GONE
                }
            }
        })
    }

    companion object {
        val TAG = HomeFragment::class.java.simpleName

        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    override fun onTransactionClicked(userId: String) {

//        mainActivity.categoryClickedMultiLevel(simpleCategoryItem, title);

        val transaction = activity?.supportFragmentManager?.beginTransaction()

        transaction?.replace(
            R.id.activity_home_framelayout,
            UserDetailesFragment.newInstance(userId),
            UserDetailesFragment.TAG
        )
        transaction?.addToBackStack(null)
        transaction?.commit()
    }

    private fun handleOnPressedBackButton(inflate: View) {
        inflate.isFocusableInTouchMode = true
        inflate.requestFocus()
        inflate.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_BACK && event.action != KeyEvent.ACTION_DOWN) {
                //if there are no fragments lift in the stack so launch transaction list fragment
                if (requireActivity().supportFragmentManager
                        .backStackEntryCount == 1
                ) {
                    activity?.finish()
                }
                return@OnKeyListener true
            }
            false
        })
    }

    override fun onStop() {

        super.onStop()
    }
}