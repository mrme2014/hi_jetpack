package org.devio.`as`.proj.hi_jetpack.room

import android.graphics.Bitmap
import androidx.annotation.NonNull
import androidx.room.*

@Entity(tableName = "table_cache")
class Cache {

    @PrimaryKey(autoGenerate = false)
    @NonNull
    var key: String = ""


    var data: ByteArray? = null

    //@PrimaryKey(autoGenerate = true)
    //@ColumnInfo(name = "cacheId", defaultValue = "1")
    //var cache_id: Long = 0

    //@Ignore
    //var temp: String? = null

    //@Embedded
    // 如果想让和内前对象中的字段也一同映射成 数据库表的字段，可以用这个注解
    //他又要求 User 对象必须也适用Entity注解标记，并且拥有一个不为空的主键
    //var user: User? = null
}

@Entity(tableName = "table_user")
class User {
    @PrimaryKey
    @NonNull
    var name: String = ""
    var age = 10
}