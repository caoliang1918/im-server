package org.zhongweixian.live.web.mapper;


import java.util.List;
import java.util.Map;

/**
 * Created by caoliang on 2019-06-03
 */
public interface BaseMapper<T> {


    /**
     * 分页
     *
     * @param params
     * @return
     */
    Integer selectCountByMap(Map params);

    /**
     * 获取总数
     *
     * @param params
     * @return
     */
    List<T> selectListByMap(Map params);


    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_operate_log
     *
     * @mbg.generated Mon Jun 03 09:46:04 CST 2019
     */
    int insert(T record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_operate_log
     *
     * @mbg.generated Mon Jun 03 09:46:04 CST 2019
     */
    int insertSelective(T record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_operate_log
     *
     * @mbg.generated Mon Jun 03 09:46:04 CST 2019
     */
    T selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_operate_log
     *
     * @mbg.generated Mon Jun 03 09:46:04 CST 2019
     */
    int updateByPrimaryKeySelective(T record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_operate_log
     *
     * @mbg.generated Mon Jun 03 09:46:04 CST 2019
     */
    int updateByPrimaryKey(T record);

}
