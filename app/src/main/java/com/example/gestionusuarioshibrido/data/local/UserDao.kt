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

    /**
     * Obtiene todos los usuarios almacenados en la base de datos.
     *
     * Este método devuelve un [Flow], por lo que cualquier cambio en la tabla `users`
     * será emitido automáticamente a los observadores.
     *
     * @return Un flujo que emite la lista completa de usuarios.
     */

    @Query("SELECT * FROM users")
    fun getAllUsers(): Flow<List<User>>

    @Query("SELECT id FROM users")
    suspend fun getUsersIds(): List<String>

    /**
     * Inserta un nuevo usuario en la base de datos.
     *
     * Si ya existe un usuario con el mismo `id`, será reemplazado debido a
     * la estrategia [OnConflictStrategy.Companion.REPLACE].
     *
     * @param user Usuario a insertar.
     */

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertUser(user: User)

    /**
     * Actualiza los datos de un usuario existente.
     *
     * Room realiza la actualización en base al `id` del usuario.
     *
     * @param user Usuario con los datos actualizados.
     */

    @Update
    suspend fun updateUser(user: User)

    /**
     * Elimina un usuario de la base de datos.
     *
     * El objeto usuario debe contener un `id` válido para que la operación
     * pueda completarse correctamente.
     *
     * @param user Usuario que se desea eliminar.
     */

    @Delete
    suspend fun deleteUser(user: User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(users: List<User>)

    @Update
    suspend fun updateUsers(users: List<User>)

    // ---- Para sincronización ----

    @Query("SELECT * FROM users WHERE pendingSync = 1 AND pendingDelete = 0")
    suspend fun getPendingUpdates(): List<User>

    @Query("SELECT * FROM users WHERE pendingDelete = 1")
    suspend fun getPendingDeletes(): List<User>

}