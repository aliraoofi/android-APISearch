package ir.solam.apisearch.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [Item::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    SerialConverter::class,
    RelDataConverter::class,
    BadgeConverter::class,
    PicConverter::class,
    CategoriesConverter::class,
    CountriesConverter::class,
    StringListConverter::class,
    DubbedConverter::class,
    SubtitleConverter::class,
    AudioConverter::class,
    LanguageInfoConverter::class
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun itemDao(): ItemDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext, AppDatabase::class.java, "item"
                ).build() // .createFromAsset("database/data.db")
                INSTANCE = instance

                instance
            }
        }
    }
}