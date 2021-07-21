package com.example.test_amce.repository

import org.apache.ibatis.annotations.*
import com.example.test_amce.entity.*

@Mapper
interface StoreRepository {

    @Select("SELECT * FROM tb_store")
    fun findAll(): List<Store>

    @Select("SELECT * FROM tb_store WHERE id = #{id}")
    fun findOne(@Param("id") id: Int): Store

    @Insert("""
    INSERT INTO tb_store(id, code, description, name, opening_date, store_type) VALUES(#{id},#{code},#{description},#{name},#{openingDate},#{storeType})
    """)
    //@SelectKey(statement = arrayOf("call identity()"), keyProperty = "id", before = false, resultType = Int::class)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    fun insert(store: Store)

    @Update("""
    UPDATE tb_store SET
        code = #{code},
        description = #{description},
        name = #{name},
        opening_date = #{openingDate},
        store_type = #{storeType}
    WHERE id = #{id}
    """)
    fun update(store: Store)

    @Update(
            """
        UPDATE tb_store SET 
            name = #{name} 
        WHERE id = #{id}
        """
    )
    fun updateName(store: Store)


    @Delete("DELETE FROM tb_store WHERE id = #{id}")
    fun delete(@Param("id") id: Int)

    @Select("""
        SELECT
            *
        FROM
            tb_store
        WHERE
            name LIKE #{name}
        ORDER BY id
    """)
    fun findByName(@Param("name") name: String): List<Store>

    @Delete("DELETE FROM tb_store")
    fun deleteAll()
}