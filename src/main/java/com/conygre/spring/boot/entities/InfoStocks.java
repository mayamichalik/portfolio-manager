package com.conygre.spring.boot.entities;
import com.conygre.spring.boot.helpers.InfoStockId;
import com.google.gson.annotations.SerializedName;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
@Entity
@Table(name="info_stocks")
@IdClass(InfoStockId.class)
public class InfoStocks implements Serializable {
    @Id
    @Column(name = "ticker_symbol")
    private String ticker_symbol;
    @Id
    @Column(name = "date_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @SerializedName(value = "datetime")
    private Date dateTime;
    @Column(name = "open_price")
    @SerializedName(value = "open")
    private Double open_price;
    @Column(name = "close_price")
    @SerializedName(value = "close")
    private Double close_price;
    @Column(name = "low_price")
    @SerializedName(value = "low")
    private Double low_price;
    @Column(name = "high_price")
    @SerializedName(value = "high")
    private Double highPrice;

    @Column(name = "volume")
    private Integer volume;
    public InfoStocks() {
    }
    public InfoStocks(String ticker, Date dateTime, Double open, Double close, Double low, Double high) {
        this.ticker_symbol = ticker;
        this.dateTime = dateTime;
        this.open_price = open;
        this.close_price = close;
        this.low_price = low;
        this.highPrice = high;
    }
    public Date getDateTime() {
        return dateTime;
    }
    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }
    public String getTicker_symbol() {
        return ticker_symbol;
    }
    public void setTicker_symbol(String ticker_symbol) {
        this.ticker_symbol = ticker_symbol;
    }
    public Double getOpen_price() {
        return open_price;
    }
    public void setOpen_price(Double open_price) {
        this.open_price = open_price;
    }
    public Double getClose_price() {
        return close_price;
    }
    public void setClose_price(Double close_price) {
        this.close_price = close_price;
    }
    public Double getLow_price() {
        return low_price;
    }
    public void setLow_price(Double low_price) {
        this.low_price = low_price;
    }
    public Double getHighPrice() {
        return highPrice;
    }
    public void setHighPrice(Double highPrice) {
        this.highPrice = highPrice;
    }
    public Integer getVolume() {
        return volume;
    }
    public void setVolume(Integer volume) {
        this.volume = volume;
    }
}

