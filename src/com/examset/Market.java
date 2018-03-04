package com.examset;

import java.util.Date;

public class Market {

    private String marketCurrency;
    private String baseCurrecy;
    private String marketCurrencyLong;
    private String baseCurrencyLong;
    private double minTradeSize;
    private String marketName;
    private boolean isActive;
    private Date created;
    private String notice;
    private boolean isSponsered;
    private String logoUrl;

    public Market(String marketCurrency, String baseCurrecy, String marketCurrencyLong, String baseCurrencyLong,
                  double minTradeSize, String marketName, boolean isActive, Date created, String notice,
                  boolean isSponsered, String logoUrl) {
        this.marketCurrency = marketCurrency;
        this.baseCurrecy = baseCurrecy;
        this.marketCurrencyLong = marketCurrencyLong;
        this.baseCurrencyLong = baseCurrencyLong;
        this.minTradeSize = minTradeSize;
        this.marketName = marketName;
        this.isActive = isActive;
        this.created = created;
        this.notice = notice;
        this.isSponsered = isSponsered;
        this.logoUrl = logoUrl;
    }

    public String getMarketCurrency() {
        return marketCurrency;
    }

    public void setMarketCurrency(String marketCurrency) {
        this.marketCurrency = marketCurrency;
    }

    public String getBaseCurrecy() {
        return baseCurrecy;
    }

    public void setBaseCurrecy(String baseCurrecy) {
        this.baseCurrecy = baseCurrecy;
    }

    public String getMarketCurrencyLong() {
        return marketCurrencyLong;
    }

    public void setMarketCurrencyLong(String marketCurrencyLong) {
        this.marketCurrencyLong = marketCurrencyLong;
    }

    public String getBaseCurrencyLong() {
        return baseCurrencyLong;
    }

    public void setBaseCurrencyLong(String baseCurrencyLong) {
        this.baseCurrencyLong = baseCurrencyLong;
    }

    public double getMinTradeSize() {
        return minTradeSize;
    }

    public void setMinTradeSize(double minTradeSize) {
        this.minTradeSize = minTradeSize;
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public boolean isSponsered() {
        return isSponsered;
    }

    public void setSponsered(boolean sponsered) {
        isSponsered = sponsered;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }
}
