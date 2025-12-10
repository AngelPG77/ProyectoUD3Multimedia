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

   /*   A IMPREMENTAR POR EL ESTUDIANTE  */

}