package com.example.sqldata_creat

import android.app.Dialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Home_Adpter(val mainActivity: MainActivity, val l1: ArrayList<ModelData>) :
    RecyclerView.Adapter<Home_Adpter.ViewData>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewData {

        var view = LayoutInflater.from(mainActivity).inflate(R.layout.item, parent, false)

        return ViewData(view)
    }

    override fun onBindViewHolder(holder: ViewData, position: Int) {

        holder.txt_id.text = l1[position].id
        holder.txt_name.text = l1[position].name
        holder.txt_moblie.text = l1[position].mobile
        holder.txt_std.text = l1[position].std
        holder.edit_data_delet.setOnClickListener {

            DB_Helper(mainActivity).deletData(l1[position].id)

            var l2 = DB_Helper(mainActivity).readData()
            mainActivity.setUpRv(l2)
        }

        holder.edit_data_add.setOnClickListener {

            updatadilog(position)
        }

    }

    override fun getItemCount(): Int {
        return l1.size
    }


    class ViewData(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var txt_id = itemView.findViewById<TextView>(R.id.txt_id)
        var txt_name = itemView.findViewById<TextView>(R.id.txt_name)
        var txt_moblie = itemView.findViewById<TextView>(R.id.txt_moblie)
        var txt_std = itemView.findViewById<TextView>(R.id.txt_std)
        var edit_data_add = itemView.findViewById<ImageView>(R.id.edit_data_add)
        var edit_data_delet = itemView.findViewById<ImageView>(R.id.edit_data_delet)

    }

    fun updatadilog(position: Int)
    {
        var dialog=Dialog(mainActivity)
        dialog.setContentView(R.layout.dialog_item)
        dialog.show()
        var update_item_btn=dialog.findViewById<Button>(R.id.update_item_btn)
        var edit_std_item=dialog.findViewById<EditText>(R.id.edit_std_item)
        var edit_name_item=dialog.findViewById<EditText>(R.id.edit_name_item)
        var edit_mobile_item=dialog.findViewById<EditText>(R.id.edit_mobile_item)


        update_item_btn.setOnClickListener{


            DB_Helper(mainActivity).updateData(l1[position].id,edit_name_item.text.toString(),edit_mobile_item.text.toString(),edit_std_item.text.toString())
            var l1 = DB_Helper(mainActivity).readData()
            mainActivity.setUpRv(l1)
            dialog.dismiss()


        }

    }

}