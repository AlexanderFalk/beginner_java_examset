package com.examset;

public class Currency {
    private String currency;
    private String currencyLong;
    private int minConfirmation;
    private double txFree;
    private boolean isActive;
    private String coinType;
    private String baseAddress;
    private String notice;

    public Currency(String currency, String currencyLong, int minConfirmation, double txFree, boolean isActive, String coinType, String baseAddress, String notice) {
        this.currency = currency;
        this.currencyLong = currencyLong;
        this.minConfirmation = minConfirmation;
        this.txFree = txFree;
        this.isActive = isActive;
        this.coinType = coinType;
        this.baseAddress = baseAddress;
        this.notice = notice;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCurrencyLong() {
        return currencyLong;
    }

    public void setCurrencyLong(String currencyLong) {
        this.currencyLong = currencyLong;
    }

    public int getMinConfirmation() {
        return minConfirmation;
    }

    public void setMinConfirmation(int minConfirmation) {
        this.minConfirmation = minConfirmation;
    }

    public double getTxFree() {
        return txFree;
    }

    public void setTxFree(double txFree) {
        this.txFree = txFree;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getCoinType() {
        return coinType;
    }

    public void setCoinType(String coinType) {
        this.coinType = coinType;
    }

    public String getBaseAddress() {
        return baseAddress;
    }

    public void setBaseAddress(String baseAddress) {
        this.baseAddress = baseAddress;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }
}
