# hi_jetpack
- 本仓库是Jetpack专栏课程源码，专栏详情请移步[慕课网](https://class.imooc.com/sale/mobilearchitect)

- 项目中的 .puml文件是组件的架构图示，详情请参考[AndroidStduio with plantUml](https://blog.csdn.net/u013831257/article/details/50118461)
```groovy
├── AppGlobals.kt
├── MainActivity.kt
├── lifecycle                      //lifecycle组件
│   ├── AppLifecycleOwner.kt
│   └── LocationLifecycleObserver.java
├── livedata                       //livedata组件
│   ├── HiDataBus.kt
│   ├── livedata_setvalue.puml
│   └── livedata_sticky.puml
├── navigation                     //navigation组件
│   ├── HiFragmentNavigator.java
│   ├── NavUtil.java
│   ├── model
│   │   ├── BottomBar.java
│   │   └── Destination.java
│   └── navigation.puml
├── paging                         //paging，databinding组件
│   ├── AbsPagedListAdapter.java
│   ├── AbsViewModel.java
│   ├── MutableItemKeyedDataSource.java
│   ├── MutablePageKeyedDataSource.java
│   ├── PagingActivity.java
│   ├── PagingAdapter.java
│   └── PagingViewModel.java
├── room                          //room组件
│   ├── Cache.kt
│   ├── CacheDao.kt
│   ├── CacheDatabase.kt
│   ├── HiStorage.kt
│   ├── TypeConvert.kt
│   ├── room_arch.puml
│   ├── room_database.puml
│   ├── room_livedata.puml
│   └── room_livedata_trigger.puml
├── ui
│   ├── dashboard
│   │   ├── DashboardFragment.kt
│   │   └── DashboardViewModel.kt
│   ├── home
│   │   ├── HomeFragment.kt
│   │   └── HomeViewModel.kt
│   └── notifications
│       ├── NotificationsFragment.kt
│       └── NotificationsViewModel.kt
├── viewmodel                     //viewmodel组件
│   ├── ViewModelActivity.kt
│   ├── savedStateRegistry.puml
│   ├── savedstate.puml
│   ├── savedstate_create.puml
│   ├── savedstate_restore.puml
│   ├── savedstate_save.puml
│   └── viewmodel_provider.puml
└── workmanager                 //workmanager组件
    ├── PublishActivity.kt
    └── UploadWorker.kt

```
