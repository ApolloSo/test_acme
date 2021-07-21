package com.example.test_amce.repository

import org.apache.ibatis.annotations.*
import com.example.test_amce.entity.*

@Mapper
interface ColStoreRepository {

    @Select("""
        select tb_store.* , tb_season.season, special_f1, special_f2
        from tb_store
        left join tb_season on tb_season.store_id = tb_store.id
        left join tb_svalue on tb_svalue.store_id = tb_store.id
        ORDER BY id 
        LIMIT #{limit} OFFSET #{offset}
    """)
    fun findAll(limit:Int, offset:Int): List<ColStore>

    @Select("""
        select tb_store.* , tb_season.season, special_f1, special_f2
        from tb_store
        left join tb_season on tb_season.store_id = tb_store.id
        left join tb_svalue on tb_svalue.store_id = tb_store.id
        WHERE id = #{id}
    """)
    fun findOne(@Param("id") id: Int): ColStore

    @Select("""
            select tb_store.* , tb_season.season, special_f1, special_f2
            from tb_store
            left join tb_season on tb_season.store_id = tb_store.id
            left join tb_svalue on tb_svalue.store_id = tb_store.id
            WHERE name LIKE #{name}
            ORDER BY id
    """)
    fun findByName(@Param("name") name: String): List<ColStore>

}