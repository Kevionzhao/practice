package com.zhao.bank1.bank1.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhao.bank1.bank1.entity.AccountInfo;
import org.apache.ibatis.annotations.*;

/**
 * yewu
 * @author Admin
 */
@Mapper
public interface AccountInfoMapper extends BaseMapper<AccountInfo> {
    /**
     *
     * @param accountNo
     * @param amount
     * @return
     */
    @Update("update account_info set account_balance=account_balance - #{amount} where account_balance>#{amount} and account_no=#{accountNo} ")
    int subtractAccountBalance(@Param("accountNo") String accountNo, @Param("amount") Double amount);

    /**
     *
     * @param accountNo
     * @param amount
     * @return
     */
    @Update("update account_info set account_balance=account_balance + #{amount} where account_no=#{accountNo} ")
    int addAccountBalance(@Param("accountNo") String accountNo, @Param("amount") Double amount);


    /**
     * 增加某分支事务try执行记录
     * @param localTradeNo 本地事务编号
     * @return
     */
    @Insert("insert into local_try_log values(#{txNo},now());")
    int addTry(String localTradeNo);

    /**
     *
     * @param localTradeNo
     * @return
     */
    @Insert("insert into local_confirm_log values(#{txNo},now());")
    int addConfirm(String localTradeNo);

    /**
     *
     * @param localTradeNo
     * @return
     */
    @Insert("insert into local_cancel_log values(#{txNo},now());")
    int addCancel(String localTradeNo);

    /**
     * 查询分支事务try是否已执行
     * @param localTradeNo 本地事务编号
     * @return
     */
    @Select("select count(1) from local_try_log where tx_no = #{txNo} ")
    int isExistTry(String localTradeNo);
    /**
     * 查询分支事务confirm是否已执行
     * @param localTradeNo 本地事务编号
     * @return
     */
    @Select("select count(1) from local_confirm_log where tx_no = #{txNo} ")
    int isExistConfirm(String localTradeNo);

    /**
     * 查询分支事务cancel是否已执行
     * @param localTradeNo 本地事务编号
     * @return
     */
    @Select("select count(1) from local_cancel_log where tx_no = #{txNo} ")
    int isExistCancel(String localTradeNo);

}
