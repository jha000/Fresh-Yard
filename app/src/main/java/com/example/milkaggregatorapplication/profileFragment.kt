package com.example.milkaggregatorapplication

import com.github.dhaval2404.imagepicker.ImagePicker.Builder.crop
import com.github.dhaval2404.imagepicker.ImagePicker.Builder.compress
import com.github.dhaval2404.imagepicker.ImagePicker.Builder.maxResultSize
import com.github.dhaval2404.imagepicker.ImagePicker.Builder.start
import com.google.android.material.bottomsheet.BottomSheetDialog
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import com.example.milkaggregatorapplication.R
import android.content.SharedPreferences
import android.app.Activity
import android.content.Context
import android.graphics.drawable.ColorDrawable
import com.google.firebase.auth.FirebaseAuth
import android.content.Intent
import android.graphics.Color
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import com.example.milkaggregatorapplication.MainActivity
import androidx.fragment.app.Fragment
import com.github.dhaval2404.imagepicker.ImagePicker

class profileFragment() : Fragment() {
    var cover: ImageView? = null
    var nameEdit: ImageView? = null
    var phoneEdit: ImageView? = null
    var emailEdit: ImageView? = null
    var addEdit: ImageView? = null
    var fav: ImageView? = null
    var name: TextView? = null
    var phone: TextView? = null
    var address: TextView? = null
    var email: TextView? = null
    var logout: Button? = null
    var z: String? = null
    var myDialog: BottomSheetDialog? = null
    var bphoneDialog: BottomSheetDialog? = null
    var bmailDialog: BottomSheetDialog? = null
    var baddressDialog: BottomSheetDialog? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        myDialog = BottomSheetDialog((activity)!!)
        bphoneDialog = BottomSheetDialog((activity)!!)
        bmailDialog = BottomSheetDialog((activity)!!)
        baddressDialog = BottomSheetDialog((activity)!!)
        cover = view.findViewById<View>(R.id.profile_image) as ImageView
        fav = view.findViewById<View>(R.id.floatingActionButton) as ImageView
        name = view.findViewById<View>(R.id.name) as TextView
        phone = view.findViewById<View>(R.id.number) as TextView
        email = view.findViewById<View>(R.id.email) as TextView
        address = view.findViewById<View>(R.id.address) as TextView
        nameEdit = view.findViewById<View>(R.id.nameEdit) as ImageView
        phoneEdit = view.findViewById<View>(R.id.phoneEdit) as ImageView
        emailEdit = view.findViewById<View>(R.id.emailEdit) as ImageView
        addEdit = view.findViewById<View>(R.id.addEdit) as ImageView
        nameEdit!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                val one: EditText?
                val b1: Button?
                myDialog!!.setContentView(R.layout.bottom)
                one = myDialog!!.findViewById<View>(R.id.one) as EditText?
                b1 = myDialog!!.findViewById<View>(R.id.b1) as Button?
                one!!.requestFocus()
                val inputMethodManager =
                    activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
                b1!!.setOnClickListener(View.OnClickListener { view ->
                    val x = one.text.toString()
                    name!!.text = x
                    val value = one.text.toString().trim { it <= ' ' }
                    val sharedPref = activity!!.getSharedPreferences("myKey", Context.MODE_PRIVATE)
                    val editor = sharedPref.edit()
                    editor.putString("value", value)
                    editor.apply()
                    val imm =
                        activity!!.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(view.windowToken, 0)
                    myDialog!!.dismiss()
                })
                myDialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                myDialog!!.show()
            }
        })
        phoneEdit!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                val too: EditText?
                val b2: Button?
                bphoneDialog!!.setContentView(R.layout.bphone)
                too = bphoneDialog!!.findViewById<View>(R.id.too) as EditText?
                b2 = bphoneDialog!!.findViewById<View>(R.id.b2) as Button?
                too!!.requestFocus()
                val inputMethodManager =
                    activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
                b2!!.setOnClickListener(object : View.OnClickListener {
                    override fun onClick(view: View) {
                        val x = too.text.toString()
                        phone!!.text = x
                        val value = too.text.toString().trim { it <= ' ' }
                        val sharedPref =
                            activity!!.getSharedPreferences("myKey", Context.MODE_PRIVATE)
                        val editor = sharedPref.edit()
                        editor.putString("x", value)
                        editor.apply()
                        val imm =
                            activity!!.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                        imm.hideSoftInputFromWindow(view.windowToken, 0)
                        bphoneDialog!!.dismiss()
                    }
                })
                //
                bphoneDialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                bphoneDialog!!.show()
            }
        })
        emailEdit!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                val three: EditText?
                val b3: Button?
                bmailDialog!!.setContentView(R.layout.bemail)
                three = bmailDialog!!.findViewById<View>(R.id.three) as EditText?
                b3 = bmailDialog!!.findViewById<View>(R.id.b3) as Button?
                three!!.requestFocus()
                val inputMethodManager =
                    activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
                b3!!.setOnClickListener(object : View.OnClickListener {
                    override fun onClick(view: View) {
                        val x = three.text.toString()
                        email!!.text = x
                        val value = three.text.toString().trim { it <= ' ' }
                        val sharedPref =
                            activity!!.getSharedPreferences("myKey", Context.MODE_PRIVATE)
                        val editor = sharedPref.edit()
                        editor.putString("value2", value)
                        editor.apply()
                        val imm =
                            activity!!.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                        imm.hideSoftInputFromWindow(view.windowToken, 0)
                        bmailDialog!!.dismiss()
                    }
                })
                bmailDialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                bmailDialog!!.show()
            }
        })
        addEdit!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                val four: EditText?
                val b4: Button?
                baddressDialog!!.setContentView(R.layout.baddress)
                four = baddressDialog!!.findViewById<View>(R.id.four) as EditText?
                b4 = baddressDialog!!.findViewById<View>(R.id.b4) as Button?
                four!!.requestFocus()
                val inputMethodManager =
                    activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
                b4!!.setOnClickListener(object : View.OnClickListener {
                    override fun onClick(view: View) {
                        val x = four.text.toString()
                        address!!.text = x
                        val value = four.text.toString().trim { it <= ' ' }
                        val sharedPref =
                            activity!!.getSharedPreferences("myKey", Context.MODE_PRIVATE)
                        val editor = sharedPref.edit()
                        editor.putString("y", value)
                        editor.apply()
                        val imm =
                            activity!!.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                        imm.hideSoftInputFromWindow(view.windowToken, 0)
                        baddressDialog!!.dismiss()
                    }
                })
                baddressDialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                baddressDialog!!.show()
            }
        })
        val sharedPreferences = activity!!.getSharedPreferences("myKey", Context.MODE_PRIVATE)
        val value = sharedPreferences.getString("value", "")
        name!!.text = value
        val value1 = sharedPreferences.getString("x", "")
        phone!!.text = value1
        val value2 = sharedPreferences.getString("value2", "")
        email!!.text = value2
        val value3 = sharedPreferences.getString("y", "")
        address!!.text = value3
        logout = view.findViewById<View>(R.id.logout) as Button
        logout!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(arg0: View) {
                val user = FirebaseAuth.getInstance()
                user.signOut()
                val intent = Intent(activity, MainActivity::class.java)
                startActivity(intent)
                activity!!.finish()
                Toast.makeText(
                    activity!!.applicationContext,
                    "Logout Successful",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
        fav!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(arg0: View) {
                ImagePicker.with(this@profileFragment)
                    .crop() //Crop image(Optional), Check Customization for more option
                    .compress(1024) //Final image size will be less than 1 MB(Optional)
                    .maxResultSize(
                        1080,
                        1080
                    ) //Final image resolution will be less than 1080 x 1080(Optional)
                    .start()
            }
        })
        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val selectedImg = data!!.data
        cover!!.setImageURI(selectedImg)
    }
}