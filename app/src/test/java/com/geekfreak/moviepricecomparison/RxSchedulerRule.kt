package com.geekfreak.moviepricecomparison

import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

open class RxSchedulerRule: TestRule {
   override fun apply(base: Statement, description: Description) =
      object : Statement() {
         @Throws(Throwable::class)
         override fun evaluate() {
            RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
            RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
            RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }
            RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }

            try {
               base.evaluate()
            } finally {
               RxJavaPlugins.reset()
               RxAndroidPlugins.reset()
            }
         }
      }

   companion object {
      private val SCHEDULER_INSTANCE = Schedulers.trampoline()
   }
}