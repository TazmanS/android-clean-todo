package com.tazmans_android.androidcleantodo.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.tazmans_android.androidcleantodo.R
import com.tazmans_android.androidcleantodo.data.models.Priority
import com.tazmans_android.androidcleantodo.data.models.ToDoData
import kotlinx.android.synthetic.main.row_layout.view.*

class ListAdapter : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    var dataList = emptyList<ToDoData>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_layout, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.title_text.text = dataList[position].title
        holder.itemView.description_txt.text = dataList[position].description
        holder.itemView.row_background.setOnClickListener{
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(dataList[position])
            holder.itemView.findNavController().navigate(action)
        }

        val priority = dataList[position].priority
        when(priority) {
            Priority.HIGH -> holder.itemView.priority_indicator.setCardBackgroundColor(ContextCompat.getColor(
                holder.itemView.context,
                R.color.red))
            Priority.MEDIUM -> holder.itemView.priority_indicator.setCardBackgroundColor(ContextCompat.getColor(
                holder.itemView.context,
                R.color.yellow))
            Priority.LOW -> holder.itemView.priority_indicator.setCardBackgroundColor(ContextCompat.getColor(
                holder.itemView.context,
                R.color.green))
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun setData(toDoData: List<ToDoData>) {
        this.dataList = toDoData
        notifyDataSetChanged()
    }
}