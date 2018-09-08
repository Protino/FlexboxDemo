package io.github.protino.flexboxdemo

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.flexbox.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val layoutManager = FlexboxLayoutManager(this, FlexDirection.ROW, FlexWrap.WRAP)
        layoutManager.justifyContent = JustifyContent.CENTER

        val itemDecoration = FlexboxItemDecoration(this)
        itemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.divider_vertical)!!)
        itemDecoration.setOrientation(FlexboxItemDecoration.VERTICAL)
        recycler.addItemDecoration(itemDecoration)

        recycler.layoutManager = layoutManager
        recycler.adapter = DemoAdapter()
    }

    class DemoAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        val data = (1..17).map { it.toString() }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            return DemoViewHolder(
                    LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
            )
        }

        override fun getItemCount(): Int {
            return data.size
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            holder.itemView.findViewById<TextView>(R.id.tv_text).text = data[position]
        }

        inner class DemoViewHolder(view: View) : RecyclerView.ViewHolder(view)
    }
}
