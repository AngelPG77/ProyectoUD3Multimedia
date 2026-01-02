package com.example.gestionusuarioshibrido.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
/**
 * Data Access Object (DAO) para la entidad [User].
 *
 * Esta interfaz define las operaciones de acceso y manipulación de datos
 * que Room implementará automáticamente en tiempo de compilación.
 *
 * Incluye consultas reactivas mediante [Flow], lo cual permite escuchar
 * actualizaciones en tiempo real de la base de datos.
 */
@Dao
interface UserDao {

   @Query("SELECT * FROM users WHERE pendingDelete = 0")
   fun getAllUsers(): Flow<List<User>>

   @Query("SELECT * FROM users WHERE id = :id")
   fun getUserById(id: String): Flow<User>

   @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun addUser(user: User)

   @Update
   suspend fun updateUser(user: User)

   @Delete
   suspend fun deleteUser(user: User)


   //Para sincronización\\

   @Query("SELECT * FROM users WHERE pendingSync = 1")
   suspend fun getUsersPendingSync(): List<User>

   @Query("SELECT * FROM users WHERE pendingDelete = 1")
   suspend fun getUsersPendingDelete(): List<User>

   @Query("SELECT id FROM users")
   suspend fun getAllIds(): List<String>

}