package com.quintype.oxygen.utils.widgets

import android.os.Handler
import android.support.v4.view.ViewPager
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit

class PagerScheduleProxy(internal var viewPager: ViewPager, interval: Long) {

    internal var mCurrentItem = 0
    internal var mInterval: Long = 0
    internal var mScheduledExecutorService: ScheduledExecutorService? = null

    private val mHandler = object : Handler() {
        override fun handleMessage(msg: android.os.Message) {
            viewPager.currentItem = mCurrentItem
        }
    }

    //            mScheduledExecutorService.schedule(new ScrollTask(), mInterval, TimeUnit.SECONDS);
    var onPageChangeListener: ViewPager.OnPageChangeListener = object : ViewPager.OnPageChangeListener {
        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

        }

        override fun onPageSelected(position: Int) {
            if (mScheduledExecutorService != null) mScheduledExecutorService?.shutdownNow()
            mScheduledExecutorService = Executors.newSingleThreadScheduledExecutor()
            mScheduledExecutorService?.scheduleWithFixedDelay(ScrollTask(), mInterval, mInterval, TimeUnit.SECONDS)
            mCurrentItem = position
        }

        override fun onPageScrollStateChanged(state: Int) {

        }
    }
        internal set

    init {
        this.mInterval = interval
    }

    /*
     * Wrap change task
     */
    private inner class ScrollTask : Runnable {

        override fun run() {
            mCurrentItem = (mCurrentItem + 1) % viewPager.adapter?.count as Int
            mHandler.obtainMessage().sendToTarget()
        }
    }


    fun onStart() {
        mScheduledExecutorService = Executors.newSingleThreadScheduledExecutor()
        //        mScheduledExecutorService.schedule(new ScrollTask(), mInterval, TimeUnit.SECONDS);
        mScheduledExecutorService?.scheduleWithFixedDelay(ScrollTask(), mInterval, mInterval, TimeUnit.SECONDS)
    }


    fun onStop() {
        if (mScheduledExecutorService != null) mScheduledExecutorService?.shutdownNow()
    }


}