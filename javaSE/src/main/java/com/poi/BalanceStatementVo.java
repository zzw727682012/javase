package com.poi;

/**
 * @author : liuc
 * @date : 2019/3/27 20:09
 * @description : 集团六大往来余额明细
 */

public class BalanceStatementVo {

    /** 科目代码 */
    @ExcelDesc(name = "科目代码")
    private String accountNumber;
    /** 一级科目代码 */
    @SheetDesc
    private String accountLevelOneNumber;
    /** 科目名称 */
    @ExcelDesc(name = "科目名称")
    private String accountLongName;
    /** 公司名称 */
    @ExcelDesc(name = "公司名称")
    private String companyName;
    /** 往来单位 */
    @ExcelDesc(name = "往来单位")
    private String unitName;
    /** 经营品类 */
    @ExcelDesc(name = "经营品类")
    private String bizCate;
    /** 年初余额借方(与下一属性两者只存在一列) */
    @ExcelDesc(name = "年初余额借方",format = FormatEnum.DOUBLE)
    private String initBalanceDebit;
    /** 年初余额贷方 */
    @ExcelDesc(name = "年初余额贷方",format = FormatEnum.DOUBLE)
    private String initBalanceCredit;
    /** 期初余额借方(与下一属性两者只存在一列) */
    @ExcelDesc(name = "期初余额借方",format = FormatEnum.DOUBLE)
    private String lastPeriodBalanceDebit;
    /** 期初余额贷方 */
    @ExcelDesc(name = "期初余额贷方",format = FormatEnum.DOUBLE)
    private String lastPeriodBalanceCredit;
    /** 本期发生额借方 */
    @ExcelDesc(name = "本期发生额借方",format = FormatEnum.DOUBLE)
    private String debitLocal;
    /** 本期发生额贷方 */
    @ExcelDesc(name = "本期发生额贷方",format = FormatEnum.DOUBLE)
    private String creditLocal;
    /** 本年累计借方 */
    @ExcelDesc(name = "本年累计借方",format = FormatEnum.DOUBLE)
    private String yearDebitLocal;
    /** 本年累计贷方 */
    @ExcelDesc(name = "本年累计贷方",format = FormatEnum.DOUBLE)
    private String yearCreditLocal;
    /** 期末余额借方 */
    @ExcelDesc(name = "期末余额借方",format = FormatEnum.DOUBLE)
    private String endDebitBalanceLocal;
    /** 期末余额贷方 */
    @ExcelDesc(name = "期末余额贷方",format = FormatEnum.DOUBLE)
    private String endCreditBalanceLocal;
    /** 会计期间 XX年xx期 */
    @ExcelDesc(name = "会计期间")
    private String period;


}
