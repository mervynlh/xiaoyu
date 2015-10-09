package com.yizhilu.os.edu.entity.user;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.yizhilu.os.edu.constants.enums.AccountStatus;
import com.yizhilu.os.edu.constants.enums.AccountType;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserAccount implements Serializable {
    /**
	 * 
	 */
    private static final long serialVersionUID = 859063728570464654L;
    private Long id;
    private Long userId;// 用户id
    private java.util.Date createTime;// 创建时间
    private java.util.Date lastUpdateTime;// 最后更新时间
    private java.math.BigDecimal balance;// 账户余额
    private java.math.BigDecimal forzenAmount;// 冻结金额
    private java.math.BigDecimal cashAmount;// 银行入账
    private java.math.BigDecimal vmAmount;// 课程卡入账
    private String accountStatus;// 账户状态
    private Long version;// 乐观锁版本号

    /**
     * 入款
     * 
     * @param amount
     *            入款金额
     * @param accountType
     *            入款类型
     * @param amount
     */
    public void credit(BigDecimal amount, AccountType accountType) {
        amount = UserAccount.formatBigDecimal(amount);
        if (amount.doubleValue() < 0) {
            throw new IllegalArgumentException("amount must be > 0");
        }
        setBalance(UserAccount.formatBigDecimal(this.balance.add(amount)));
        // cash 和vm
        if (AccountType.CASH.toString().equals(accountType.toString())) {
            setCashAmount(UserAccount.formatBigDecimal(this.cashAmount.add(amount)));
        } else if (AccountType.VM.toString().equals(accountType.toString())) {
            setVmAmount(UserAccount.formatBigDecimal(this.vmAmount.add(amount)));
        }

    }

    /**
     * 出款
     * 
     * @param amount
     */
    public void debit(BigDecimal amount) {
        if (amount.doubleValue() < 0) {
            throw new IllegalArgumentException("amount must be > 0");
        }
        // 扣款时账户如果被冻结了。不准消费
        if (!AccountStatus.ACTIVE.toString().equals(getAccountStatus().toString())) {
            throw new IllegalStateException("account status is invalid");
        }
        setBalance(UserAccount.formatBigDecimal(this.balance.subtract(amount)));
        // 设置cashAmount和vmAmount,出款时先扣除vm,不购时再扣除cash
        if (getVmAmount().compareTo(amount) >= 0) {// vm余额大于等于扣款的金额
            setVmAmount(UserAccount.formatBigDecimal(getVmAmount().subtract(amount)));
        } else {// vm不够的时候 再扣除cash的余额
            BigDecimal didAmount = amount.subtract(getVmAmount());// 需要扣除的cash的金额
            setVmAmount(new BigDecimal(0));// vm设置为0
            setCashAmount(UserAccount.formatBigDecimal(this.cashAmount.subtract(didAmount)));
        }

    }

    public static BigDecimal formatBigDecimal(BigDecimal decimal) {
        return decimal.setScale(2, RoundingMode.HALF_UP);
    }

}
