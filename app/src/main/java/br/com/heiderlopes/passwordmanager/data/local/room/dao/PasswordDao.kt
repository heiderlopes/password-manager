package br.com.heiderlopes.passwordmanager.data.local.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import br.com.heiderlopes.passwordmanager.data.local.room.entity.PasswordEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PasswordDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(password: PasswordEntity): Long

    @Update
    suspend fun update(password: PasswordEntity)

    @Query("SELECT * FROM passwords WHERE id = :id")
    suspend fun findById(id: Long): PasswordEntity?

    @Query("SELECT * FROM passwords ORDER BY id DESC")
    suspend fun getAll(): List<PasswordEntity>

    @Delete
    suspend fun delete(password: PasswordEntity)

    @Query("DELETE FROM passwords WHERE id = :id")
    suspend fun deleteById(id: Long)

    @Query("SELECT COUNT(*) FROM passwords")
    fun getTotalPasswords(): Flow<Int>

    @Query("""
    SELECT COUNT(*) 
    FROM passwords
    WHERE password IN (
        SELECT password
        FROM passwords
        GROUP BY password
        HAVING COUNT(*) > 1
    )
""")
    fun getTotalReusedPasswords(): Flow<Int>
}