package com.example.poiutis.model;

/**
 * @ClassName InvoiceOrder
 * @Description TODO
 * @Author wushaopei
 * @Date 2019/7/16 19:35
 * @Version 1.0
 */
public class InvoiceOrder {

    private String invoiceOrder;
    private String companyName;
    private String taxNumber;
    private String accountBank;
    private String companyAddress;
    private String bankNumber;
    private String companyTelephone;
    private String accountName;

    public String getInvoiceOrder() {
        return invoiceOrder;
    }

    public void setInvoiceOrder(String invoiceOrder) {
        this.invoiceOrder = invoiceOrder;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }

    public String getAccountBank() {
        return accountBank;
    }

    public void setAccountBank(String accountBank) {
        this.accountBank = accountBank;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getBankNumber() {
        return bankNumber;
    }

    public void setBankNumber(String bankNumber) {
        this.bankNumber = bankNumber;
    }

    public String getCompanyTelephone() {
        return companyTelephone;
    }

    public void setCompanyTelephone(String companyTelephone) {
        this.companyTelephone = companyTelephone;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
}
