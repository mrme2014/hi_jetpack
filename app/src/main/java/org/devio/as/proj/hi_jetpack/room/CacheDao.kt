package org.devio.`as`.proj.hi_jetpack.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao  //data access object 数据访问对象 ，这里面会定义数据操作的增删改查的方法
interface CacheDao {

    @Query("select * from table_cache where `key`=:keyword limit 1")
    fun query(keyword: String): Cache  //list<cache>


    //可以通过livedata 以观察者的形式获取数据库数据，可以避免不必要的npe
    //更重要的是 他可以监听数据库表中的数据的比变化。一旦发生了 insert update delete。room会自动读取表中最新的数据
    //发送给UI层 刷新页面
    @Query("select * from table_cache")
    fun query2(): LiveData<List<Cache>>  //rxjava observer

    @Delete(entity = Cache::class)
    fun delete(cache: Cache)

    @Insert(entity = Cache::class, onConflict = OnConflictStrategy.REPLACE)
    fun save(cache: Cache) //cache_key

    @Update()
    fun update(cache: Cache)
}