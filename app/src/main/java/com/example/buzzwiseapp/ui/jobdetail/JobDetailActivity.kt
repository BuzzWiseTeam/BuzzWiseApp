package com.example.buzzwiseapp.ui.jobdetail

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.example.buzzwiseapp.data.response.DataItem
import com.example.buzzwiseapp.databinding.ActivityJobDetailBinding


class JobDetailActivity : AppCompatActivity() {

    companion object {
        @JvmStatic
        fun start(context: Context, photoUrl: String, userId: String, pair: Pair<View, String>) {
            val starter = Intent(context, JobDetailActivity::class.java)
                .putExtra(USER_ID, userId)
                .putExtra(PHOTO_URL, photoUrl)

            val optionsCompat =
                ActivityOptionsCompat.makeSceneTransitionAnimation(context as Activity, pair)
            context.startActivity(starter, optionsCompat.toBundle())
        }

        private const val USER_ID = "userId"
        private const val PHOTO_URL = "photo_url"
    }

    private lateinit var binding: ActivityJobDetailBinding
    private val detailStoryViewModel by viewModels<JobViewModel>()
    private val userId by lazy { intent.getStringExtra(USER_ID) }
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityJobDetailBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupView()
        setupViewModel()
        binding.btnApply.setOnClickListener {
            val intent = Intent(
                Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto", "email@email.com", null
                )
            )
            intent.putExtra(Intent.EXTRA_SUBJECT, binding.tvTitle.text)
            intent.putExtra(Intent.EXTRA_TEXT, binding.tvJobdescdet.text)
            startActivity(Intent.createChooser(intent, "Choose an Email client :"))
        }
        val toolbar: Toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        supportActionBar!!.setHomeButtonEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onResume() {
        super.onResume()
        detailStoryViewModel.getDetailStory(userId as String)
    }
    private fun setupView() {
        Glide.with(this@JobDetailActivity)
            .load(intent.getStringExtra(PHOTO_URL))
            .into(binding.ivDetail)
        detailStoryViewModel.getDetailStory(userId as String)
    }

    private fun setupViewModel() {
        detailStoryViewModel.detailStory.observe(this) {
            setDetailStory(it)
        }

        detailStoryViewModel.loadingScreen.observe(this) {
            showLoading(it)
        }
    }

    private fun showLoading(value: Boolean) {
        with(binding) {
            progressBar.isVisible = value
            tvCompany.isVisible = !value
            tvLocation.isVisible = !value
            tvEmail.isVisible = !value
            tvWork.isVisible = !value
            tvSkilljob.isVisible = !value
            tvJobdescdet.isVisible = !value
        }
    }

    private fun setDetailStory(job: DataItem) {
        with(binding) {
            tvTitle.text = job.title
            tvCompany.text = job.companyName
            tvLocation.text = job.location
            tvEmail.text = job.email
            tvWork.text = job.jobType
            tvSkilljob.text = job.requiredSkill
            tvJobdescdet.text = job.jobDescription
        }
    }
}