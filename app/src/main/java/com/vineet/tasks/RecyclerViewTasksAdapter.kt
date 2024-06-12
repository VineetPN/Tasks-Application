package com.vineet.tasks

import android.content.Context
import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.core.content.contentValuesOf
import androidx.recyclerview.widget.RecyclerView
import com.vineet.tasks.RecyclerViewTasksAdapter.ViewHolder

class RecyclerViewTasksAdapter(private val listTasks: List<Task>) :
    RecyclerView.Adapter<RecyclerViewTasksAdapter.ViewHolder>() {

    inner class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val taskName = ItemView.findViewById<TextView>(R.id.text_view_task_in_recyclerview)
        val taskDescription =
            ItemView.findViewById<TextView>(R.id.text_view_description_in_recyclerview)
        //val isStarred = ItemView.findViewById<ImageView>(R.id.image_view_is_starred)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
            .inflate(R.layout.single_task_for_recycler_view, parent, false)

        return ViewHolder(inflater)
    }

    override fun getItemCount(): Int {
        return listTasks.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.taskName.text = listTasks[position].title
        if(listTasks[position].description != ""){
            holder.taskDescription.visibility = View.VISIBLE
            holder.taskDescription.text = listTasks[position].description
        }
//        if (listTasks[position].isStarred) {
//            holder.isStarred.visibility = View.VISIBLE
//        } else {
//            holder.isStarred.visibility = View.GONE
//        }
    }

}