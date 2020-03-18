package me.apqx.demo.mvvm.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import me.apqx.demo.mvvm.data.Student
import me.apqx.demo.mvvm.data.StudentRepository
import me.apqx.libbase.util.LogUtil

class DemoViewModel : ViewModel() {
    private val repository = StudentRepository()
    private var job: Job? = null

    val loadingState: MutableLiveData<Boolean> = MutableLiveData()
    val errorInfo: MutableLiveData<String> = MutableLiveData()

    // 懒加载
    val studentList: MutableLiveData<List<Student>> by lazy {
        MutableLiveData<List<Student>>().also {
            // 这里是懒加载，不能在当前线程中，直接访问studentList对象，因为它还没有加载完成，会造成无限递归
            loadMoreStudent()
        }
    }

    fun loadMoreStudent() {
        // 在上一个任务完成后再执行新任务
        LogUtil.d("job.isComplete = ${job?.isCompleted}")
        if (job == null || job?.isCompleted == true) {
            job = GlobalScope.launch {
                loadingState.postValue(true)
                studentList.postValue(repository.loadStudent(10, studentList.value?.size ?: 0).apply {
                    val tempList = ArrayList<Student>(studentList.value ?: ArrayList<Student>(0))
                    tempList.addAll(this)
                    clear()
                    addAll(tempList)
                    wait()
                })
                loadingState.postValue(false)
            }
        } else {
            errorInfo.postValue("正在加载")
        }
    }

    /**
     * 耗时操作，只可以在协程中使用，避免阻塞主线程
     */
    private suspend fun wait() {
        delay(2000)
    }

}