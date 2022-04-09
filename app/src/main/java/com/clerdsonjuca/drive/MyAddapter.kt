package com.clerdsonjuca.drive


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.clerdsonjuca.drive.model.Historico
import kotlinx.android.synthetic.main.row.view.*


class MyAdapter: RecyclerView.Adapter<MyAdapter.MyViewHolder>(){

    private var myList = emptyList<Historico>()

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row, parent, false))
    }

    override fun getItemCount(): Int {
        return myList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.itemView.time2.text = myList[position].time.toString()
        holder.itemView.time3.text = myList[position].paid.toString()
    }

    fun setData(newList: List<Historico>){
        myList = newList
        notifyDataSetChanged()
    }
}