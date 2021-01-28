package com.entertainmentapp.view.ui.OtpVerification.ui

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.KeyEvent.KEYCODE_DEL
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.entertainmentapp.R
import com.entertainmentapp.utils.Helpers


class OtpVerificationActivity : AppCompatActivity() {
    var otpETs = arrayOfNulls<EditText>(6)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp_verification)
        otpETs[0] = findViewById(R.id.edt_1)
        otpETs[1] = findViewById(R.id.edt_2)
        otpETs[2] = findViewById(R.id.edt_3)
        otpETs[3] = findViewById(R.id.edt_4)
        otpETs[4] = findViewById(R.id.edt_5)
        otpETs[5] = findViewById(R.id.edt_6)

        setOtpEditTextHandler()
        val code = (Helpers.getText(otpETs[0]!!) + Helpers.getText(otpETs[1]!!) +
                Helpers.getText(otpETs[2]!!) + Helpers.getText(otpETs[3]!!) + Helpers.getText(otpETs[4]!!)
                + Helpers.getText(otpETs[5]!!))

    }


    override fun onResume() {
        LocalBroadcastManager.getInstance(this)
            .registerReceiver(receiver, IntentFilter("AddedItem"))
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver)
    }

    private val receiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent) {

            val message = intent.getStringExtra("message")
            Toast.makeText(this@OtpVerificationActivity,message,Toast.LENGTH_SHORT).show()
            //Do whatever you want with the code here

        }
    }

    private fun checkWhoHasFocus(): Int {
        for (i in 0 until otpETs.size) {
            val tempET: EditText = otpETs.get(i)!!
            if (tempET.hasFocus()) {
                return i
            }
        }
        return 123
    }


    private fun setOtpEditTextHandler() { //This is the function to be called
        for (i in 0..5) { //Its designed for 6 digit OTP
            otpETs.get(i)!!.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
                override fun afterTextChanged(s: Editable) {
                    if (i == 5 && !otpETs!!.get(i)!!.getText().toString().isEmpty()) {
                        otpETs!!.get(i)!!
                            .clearFocus() //Clears focus when you have entered the last digit of the OTP.
                    } else if (!otpETs!!.get(i)!!.getText().toString().isEmpty()) {
                        otpETs!!.get(i + 1)!!
                            .requestFocus() //focuses on the next edittext after a digit is entered.
                    }
                }
            })
            otpETs!!.get(i)!!.setOnKeyListener(object : View.OnKeyListener {
                override fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {
                    if (event.action != KeyEvent.ACTION_DOWN) {
                        return false //Dont get confused by this, it is because onKeyListener is called twice and this condition is to avoid it.
                    }
                    if (keyCode == KEYCODE_DEL &&
                        otpETs!!.get(i)!!.getText().toString().isEmpty() && i != 0
                    ) {
                        //this condition is to handel the delete input by users.
                        otpETs!!.get(i - 1)!!.setText("") //Deletes the digit of OTP
                        otpETs!!.get(i - 1)!!.requestFocus() //and sets the focus on previous digit
                    }
                    return false
                }
            })
        }
    }


}