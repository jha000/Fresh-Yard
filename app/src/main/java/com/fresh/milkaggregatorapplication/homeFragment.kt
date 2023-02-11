package com.fresh.milkaggregatorapplication

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso

class homeFragment : Fragment() {
    lateinit var intent: Intent
    lateinit var intent2: Intent
    lateinit var card1: CardView
    lateinit var card2: CardView
    lateinit var card3: CardView
    lateinit var card4: CardView
    lateinit var rImage: ImageView
    lateinit var content: TextView
    lateinit var no: TextView
    lateinit var rank: TextView
    lateinit var set: TextView
    lateinit var refer: Button
    lateinit var shimmerFrameLayout: ShimmerFrameLayout
    private lateinit var viewmodal: ViewModal
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        intent = Intent(activity, selection::class.java)
        intent2 = Intent(activity, selection2::class.java)
        shimmerFrameLayout = view.findViewById(R.id.shimmer_view_container)
        shimmerFrameLayout.startShimmer()
        rImage = view.findViewById(R.id.rImage)
        content = view.findViewById(R.id.contentTemplete)
        card1 = view.findViewById(R.id.card11)
        card2 = view.findViewById(R.id.card111)
        card3 = view.findViewById(R.id.card1)
        card4 = view.findViewById(R.id.int11)
        refer = view.findViewById(R.id.refer)
        rank = view.findViewById(R.id.rank)
        no = view.findViewById(R.id.no)

        val sharedPreferences =
            requireActivity().getSharedPreferences("myKey", Context.MODE_PRIVATE)
        val value2 = sharedPreferences.getString("saveCode", null)
        no.text = value2

        refer.setOnClickListener(View.OnClickListener { view1: View? ->
            try {
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.type = "text/plain"
                val shareMessage1 = no.text.toString()
                var shareMessage = "\n is your referral code\n\n"
                shareMessage = """
                ${shareMessage1}${shareMessage}https://play.google.com/store/apps/details?id=${BuildConfig.APPLICATION_ID}
                """.trimIndent()
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Fresh Yard")
                startActivity(Intent.createChooser(shareIntent, "choose one"))
            } catch (ignored: Exception) {
            }
        })
        val firebaseDatabase = FirebaseDatabase.getInstance()
        val databaseReference = firebaseDatabase.reference

        val getImage = databaseReference.child("Image")
        getImage.addListenerForSingleValueEvent(
            object : ValueEventListener {
                override fun onDataChange(
                    dataSnapshot: DataSnapshot
                ) {
                    val link = dataSnapshot.getValue(
                        String::class.java
                    )
                    Picasso.get().load(link).into(rImage)
                    shimmerFrameLayout.hideShimmer()
                }

                override fun onCancelled(
                    databaseError: DatabaseError
                ) {
                }
            })
        val getText = databaseReference.child("Content")
        getText.addListenerForSingleValueEvent(
            object : ValueEventListener {
                override fun onDataChange(
                    dataSnapshot: DataSnapshot
                ) {
                    val text = dataSnapshot.getValue(
                        String::class.java
                    )
                    content.setText(text)
                    shimmerFrameLayout.hideShimmer()
                }

                override fun onCancelled(
                    databaseError: DatabaseError
                ) {
                }
            })

        val keyName = no.text.toString()
        val databaseRef = firebaseDatabase.reference.child("top 5").child(keyName);

        databaseRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                if (dataSnapshot.exists()) {
                    val text = dataSnapshot.getValue(
                        String::class.java
                    )
                    rank.setText(text)
                }

            }

            override fun onCancelled(error: DatabaseError) {

            }
        })


        card1.setOnClickListener(View.OnClickListener {
            startActivityForResult(
                intent,
                ADD_COURSE_REQUEST
            )
        })
        card2.setOnClickListener(View.OnClickListener {
            startActivityForResult(
                intent2,
                ADD_COURSE_REQUEST
            )
        })
        card3.setOnClickListener(View.OnClickListener {
            Toast.makeText(
                requireActivity().applicationContext,
                "Thank You for the response",
                Toast.LENGTH_SHORT
            ).show()
        })
        card4.setOnClickListener(View.OnClickListener {
            Toast.makeText(
                requireActivity().applicationContext,
                "Thank You for the response",
                Toast.LENGTH_SHORT
            ).show()
        })
        val adapter = CourseRVAdapter()
        viewmodal = ViewModelProviders.of(this).get(ViewModal::class.java)

        viewmodal.allCourses.observe(requireActivity()) { models ->
            adapter.submitList(models)
        }
        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ADD_COURSE_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                assert(data != null)
                val courseName = data!!.getStringExtra(selection.EXTRA_COURSE_NAME)
                val courseDescription = data.getStringExtra(selection.EXTRA_DESCRIPTION)
                val courseDuration = data.getStringExtra(selection.EXTRA_DURATION)
                val model = CourseModal(courseName, courseDescription, courseDuration)
                viewmodal.insert(model)
            }
        }
    }

    companion object {
        private const val EDIT_COURSE_REQUEST = 2
        private const val ADD_COURSE_REQUEST = 1
    }
}