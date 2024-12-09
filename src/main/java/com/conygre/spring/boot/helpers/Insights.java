package com.conygre.spring.boot.helpers;

import com.google.gson.annotations.SerializedName;

public class Insights {
    @SerializedName(value = "Symbol")
    private String symbol;
    @SerializedName(value = "MarketCapitalization")
    private Double marketCap;
    @SerializedName(value = "PERatio")
    private String peRatio;
    @SerializedName(value = "PEGRatio")
    private String pegRatio;
    @SerializedName(value = "DividendPerShare")
    private Double dividendPerShare;
    @SerializedName(value = "DividendYield")
    private Double dividedYield;
    @SerializedName(value = "EPS")
    private Double eps;
    @SerializedName(value = "52WeekHigh")
    private Double fiftyTwoWeekHigh;
    @SerializedName(value = "52WeekLow")
    private Double fiftyTwoWeekLow;
    @SerializedName(value = "50DayMovingAverage")
    private Double fiftyDayMovingAvg;
    @SerializedName(value = "200DayMovingAverage")
    private Double twoHundredDayMovingAvg;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Double getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(Double marketCap) {
        this.marketCap = marketCap;
    }

    public String getPeRatio() {
        return peRatio;
    }

    public void setPeRatio(String peRatio) {
        this.peRatio = peRatio;
    }

    public String getPegRatio() {
        return pegRatio;
    }

    public void setPegRatio(String pegRatio) {
        this.pegRatio = pegRatio;
    }

    public Double getDividendPerShare() {
        return dividendPerShare;
    }

    public void setDividendPerShare(Double dividendPerShare) {
        this.dividendPerShare = dividendPerShare;
    }

    public Double getDividedYield() {
        return dividedYield;
    }

    public void setDividedYield(Double dividedYield) {
        this.dividedYield = dividedYield;
    }

    public Double getEps() {
        return eps;
    }

    public void setEps(Double eps) {
        this.eps = eps;
    }

    public Double getFiftyTwoWeekHigh() {
        return fiftyTwoWeekHigh;
    }

    public void setFiftyTwoWeekHigh(Double fiftyTwoWeekHigh) {
        this.fiftyTwoWeekHigh = fiftyTwoWeekHigh;
    }

    public Double getFiftyTwoWeekLow() {
        return fiftyTwoWeekLow;
    }

    public void setFiftyTwoWeekLow(Double fiftyTwoWeekLow) {
        this.fiftyTwoWeekLow = fiftyTwoWeekLow;
    }

    public Double getFiftyDayMovingAvg() {
        return fiftyDayMovingAvg;
    }

    public void setFiftyDayMovingAvg(Double fiftyDayMovingAvg) {
        this.fiftyDayMovingAvg = fiftyDayMovingAvg;
    }

    public Double getTwoHundredDayMovingAvg() {
        return twoHundredDayMovingAvg;
    }

    public void setTwoHundredDayMovingAvg(Double twoHundredDayMovingAvg) {
        this.twoHundredDayMovingAvg = twoHundredDayMovingAvg;
    }
}
