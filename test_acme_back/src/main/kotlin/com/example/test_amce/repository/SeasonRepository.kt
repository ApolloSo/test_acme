package com.example.test_amce.repository

import org.apache.ibatis.annotations.*
import com.example.test_amce.entity.*

@Mapper
interface SeasonRepository {

    @Select("SELECT * FROM tb_season")
    fun findAll(): List<Season>

    @Select("SELECT * FROM tb_season WHERE store_id = #{id}")
    fun findOne(@Param("id") id: Int): Season

    @Insert("INSERT INTO tb_season( store_id, season) VALUES(#{storeId},#{season})")
    //@SelectKey(statement = arrayOf("call identity()"), keyProperty = "id", before = false, resultType = Int::class)
    @Options(useGeneratedKeys = true, keyProperty = "storeId")
    fun insert(store: Season)

    @Update(
            """
        UPDATE tb_season SET
            season = #{season}
        WHERE store_id = #{storeId}
        """
    )
    fun update(season: Season)


    @Delete("DELETE FROM tb_season WHERE store_id = #{id}")
    fun delete(@Param("id") id: Int)

    @Delete("DELETE FROM tb_season")
    fun deleteAll()
}