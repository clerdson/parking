package com.clerdsonjuca.drive


import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.clerdsonjuca.drive.model.Historico
import com.clerdsonjuca.drive.ui.MainActivity3
import kotlinx.android.synthetic.main.row.view.*
import kotlinx.coroutines.flow.Flow
import retrofit2.Response


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
        var pago:String = "-"
        holder.itemView.time2.text = myList[position].time.toString()
        if(myList[position].paid == true){
            pago="pago"
        }
        holder.itemView.time3.text = pago.toString()
        holder.itemView.cardViewRow.setOnClickListener {


            val intent = Intent(it.context , MainActivity3::class.java)
            intent.putExtra("pay",pago)
            intent.putExtra("time",myList[position].time)
            intent.putExtra("plate",myList[position].plate)
            it.context.startActivity(intent)
        }
    }

    fun setData(newList: List<Historico>){
        myList = newList
        notifyDataSetChanged()
    }
}