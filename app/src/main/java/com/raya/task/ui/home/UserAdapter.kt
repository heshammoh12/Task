package com.raya.task.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.raya.task.R
import com.raya.task.data.model.UserData
import kotlinx.android.synthetic.main.user_list_item.view.*

class UserAdapter (val userList: List<UserData>,val listener: OnItemClickListener) : RecyclerView.Adapter<UserAdapter.UserViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.user_list_item, parent, false)
        return UserViewHolder(inflater);
    }

    override fun getItemCount(): Int = userList.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.firstName.text = userList[position].firstName
        holder.lastName.text = userList[position].lastName
        holder.userID.text = userList[position].id

        holder.userItem.setOnClickListener {
            //navigate
            listener.onTransactionClicked(userList[position].id)
        }
    }


    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val firstName = itemView.textview_first_name as TextView
        val lastName = itemView.textview_last_name as TextView
        val userID = itemView.textview_id as TextView
        val userItem = itemView.user_item_list_linearlayout as ViewGroup

    }

    public interface OnItemClickListener {
        fun onTransactionClicked(userId: String)
    }
}