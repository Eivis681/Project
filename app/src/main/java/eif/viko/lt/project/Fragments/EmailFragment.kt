package eif.viko.lt.project.Fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import eif.viko.lt.project.R
import kotlinx.android.synthetic.main.fragment_email.*


class EmailFragment : Fragment(R.layout.fragment_email) {

    private fun sendEmail(recipient: String, subject: String, message: String) {

        val mIntent = Intent(Intent.ACTION_SEND)
        mIntent.data = Uri.parse("mailto:")
        mIntent.type = "text/plain"
        mIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(recipient))
        mIntent.putExtra(Intent.EXTRA_SUBJECT, subject)
        mIntent.putExtra(Intent.EXTRA_TEXT, message)
        try {
            startActivity(Intent.createChooser(mIntent, "Choose Email Client..."))
        }
        catch (e: Exception){
        }

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sendEmailBtn.setOnClickListener {
            //val recipient = recipientEt.text.toString().trim()
            val recipient = "support@gmail.com"
            val subject = subjectEt.text.toString().trim()
            val message = messageEt.text.toString().trim()
            sendEmail(recipient, subject, message)
        }
    }

}