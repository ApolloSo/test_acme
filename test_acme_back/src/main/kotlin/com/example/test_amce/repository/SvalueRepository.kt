package com.example.test_amce.repository

import org.apache.ibatis.annotations.*
import com.example.test_amce.entity.*

@Mapper
interface SvalueRepository {

    @Select("SELECT * FROM tb_svalue")
    fun findAll(): List<Svalue>

    @Select("SELECT * FROM tb_svalue WHERE store_id = #{id}")
    fun findOne(@Param("id") id: Int): Svalue

    @Insert("INSERT INTO tb_svalue( store_id, special_f1, special_f2) VALUES(#{store_id},#{special_f1},#{special_f2})")
    //@SelectKey(statement = arrayOf("call identity()"), keyProperty = "id", before = false, resultType = Int::class)
    @Options(useGeneratedKeys = true, keyProperty = "store_id")
    fun insert(svalue: Svalue)

    @Update(
            """
            UPDATE tb_svalue SET
                special_f1 = #{special_f1},
                special_f2 = #{special_f2}
            WHERE store_id = #{store_id}
            """
    )
    fun update(svalue: Svalue)


    @Delete("DELETE FROM tb_svalue WHERE store_id = #{id}")
    fun delete(@Param("id") id: Int)

    @Delete("DELETE FROM tb_svalue")
    fun deleteAll()
}