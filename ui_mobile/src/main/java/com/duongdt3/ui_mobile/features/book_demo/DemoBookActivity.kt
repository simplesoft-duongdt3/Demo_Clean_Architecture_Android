package com.duongdt3.ui_mobile.features.book_demo

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.duongdt3.domain.common.usecase.model.UseCaseFailModel
import com.duongdt3.presentation_mvp.features.demo_book.DemoBookContract
import com.duongdt3.presentation_mvp.features.demo_book.DemoBookPresentationModel
import com.duongdt3.ui_mobile.R
import com.duongdt3.ui_mobile.common.dialog_provider.CommonErrorDialogProvider
import kotlinx.android.synthetic.main.activity_demo_book.*
import org.koin.android.ext.android.inject

class DemoBookActivity : AppCompatActivity(), DemoBookContract.View {
    private val commonErrorDialogProvider = CommonErrorDialogProvider(this)

    private val presenter by inject<DemoBookContract.Presenter>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo_book)
        initView()
    }
    private fun initView() {
        this.vGetBookSuccess.setOnClickListener {
            presenter.onGetBookSuccessClicked()
        }
        this.vGetBookServerFail.setOnClickListener {
            presenter.onGetBookServerFailClicked()
        }
        this.vGetBookTimeout.setOnClickListener {
            presenter.onGetBookTimeoutClicked()
        }
        this.vGetBookSomethingWrong.setOnClickListener {
            presenter.onGetBookSomethingWrongClicked()
        }
        this.vGetNonWrappedResponse.setOnClickListener {
            presenter.onGetNonWrappedResponseClicked()
        }
    }

    override fun showLoading() {
        vLoading.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        vLoading.visibility = View.GONE
    }

    override fun onStart() {
        super.onStart()
        presenter.start(this)
    }

    override fun showError(failModel: UseCaseFailModel) {
        commonErrorDialogProvider.showErrorDialog(
                useCaseFailModel = failModel,
                handleFeatureError = null
        )
    }

    override fun showResult(demoBookPresentationModel: DemoBookPresentationModel) {
        Toast.makeText(this, "$demoBookPresentationModel", Toast.LENGTH_SHORT).show()
    }

    override fun onStop() {
        super.onStop()
        presenter.stop()
    }
}
