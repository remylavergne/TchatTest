package com.example.tchattest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tchattest.Manager.TchatMessageAdapter
import com.example.tchattest.dataClasses.TchatMessage
import com.example.tchattest.dataClasses.User
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var marc: User
    private lateinit var zoe: User
    private lateinit var you: User
    private lateinit var itemAdapter: TchatMessageAdapter

    private var messages = emptyList<TchatMessage>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        marc = User("Marc", resources.getColor(R.color.gray))
        zoe = User("Zoé", resources.getColor(R.color.colorAccent))
        you = User("you", resources.getColor(R.color.colorPrimaryDark))

        messages = listOf(
                TchatMessage("Holla !", you),
                TchatMessage("Hey ! :)", marc),
                TchatMessage("Coucou les zigottos :D", zoe),
                TchatMessage(":o", you),
                TchatMessage("Quoiiii ? :D", zoe)
        )

        setRecyclerView()
        populateTchat()
    }

    private fun setRecyclerView() {
        message_recycler_view.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        itemAdapter = TchatMessageAdapter()
        message_recycler_view.adapter = itemAdapter
    }

    private fun populateTchat() {

        // Add some messages
        itemAdapter.addItems(messages)

        // Add a message every second
        val countDown = object : CountDownTimer(10_000, 1_000) {
            override fun onFinish() {
                Log.d("Tchat insertion", "Done")
            }

            override fun onTick(millisUntilFinished: Long) {
                addMessage()
            }
        }.start()
    }

    private fun addMessage() {
        // J'insère toujours le premier pour l'exemple
        itemAdapter.addItem(messages.first())
    }
}
